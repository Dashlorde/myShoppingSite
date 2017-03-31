package com.zhouyunlu.controller;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller
@MultipartConfig
public class changeProductController {

	@Autowired
	ProductDao productDao = new ProductDao();

	@RequestMapping(value = "/deleteProduct.htm", method = RequestMethod.GET)
	protected ModelAndView deleteMyProduct(HttpServletRequest request, HttpServletResponse response)
			throws HibernateException, shoppingSiteException {
		ModelAndView mv = new ModelAndView();

		String productID = request.getParameter("id").toString();
		long id = Long.parseLong(productID);
		String action = request.getParameter("action");

		HttpSession session = request.getSession();
		UserDAO userDao = new UserDAO();
		String username = session.getAttribute("username").toString();
		User user = userDao.get(username);
		session.setAttribute("user", user);

		try {

			ProductDao productDao = new ProductDao();
			Product product = productDao.getProductByID(id);

			if (action.equals("deleteProduct")) {
				productDao.delete(product);
				System.out.println("delete product id: " + id);
				List<Product> productList = productDao.getProductByName(username);
				mv.addObject(productList);
				mv.setViewName("viewMyProduct");

			}

		} catch (shoppingSiteException e) {
			System.out.println(e.getMessage());
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}

		return mv;
	}

	@RequestMapping(value = "/modify.htm", method = RequestMethod.GET)
	protected ModelAndView goModifyPage(HttpServletRequest request, HttpServletResponse response) throws shoppingSiteException {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		long productId = Long.parseLong(request.getParameter("id"));

		ProductDao productDao = new ProductDao();
		Product product = productDao.getProductByID(productId);
		session.setAttribute("product", product);
		mv.addObject(product);
		mv.setViewName("modifyProduct");
		return mv;

	}

	@RequestMapping(value = "/modifyProduct.htm", method = RequestMethod.POST)
	protected String modifyMyProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		Product product = (Product) session.getAttribute("product");
		String description = request.getParameter("description").toString();
		String stockString = request.getParameter("stock").toString();
		int stock = 0;
		
		//aws account key
		AWSCredentials credentials = new BasicAWSCredentials("AWS-ID", "AWS-KEY");
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		//aws s3 bucket name
		String bucketName = "elasticbeanstalk-us-west-2-481664616485";
		String awsPath="https://s3-us-west-2.amazonaws.com/elasticbeanstalk-us-west-2-481664616485/img";

		try {
			String imagePath = null;
			String fileName = null;
			imagePath = request.getServletContext().getRealPath("");
			imagePath += "/";
			String context = null;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile image = multipartRequest.getFile("image");

			if (!description.isEmpty()) {
				productDao.modifyDescription(description, product);
			}

			if (!image.isEmpty()) {
				fileName = System.currentTimeMillis() + image.getOriginalFilename();
				File file = new File(imagePath + fileName);

				/*if (!file.exists()) {
					file.mkdir();
				}
				*/
				context = request.getServletContext().getContextPath();
				image.transferTo(file);
				s3client.putObject(new PutObjectRequest(bucketName, "img/"+fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));

				System.out.println("context is: " + context + "     file name is: " + fileName);
				
				//System.out.println(file.getAbsolutePath());
				
				//productDao.modifyImage(context + "/" + fileName, product);
				productDao.modifyImage(awsPath + "/" + fileName, product);
			}
			
			//check stock string has input number
			if (!stockString.isEmpty() && isInteger(stockString)) {
				stock = Integer.parseInt(stockString);
				productDao.modifyStock(stock, product);
			}

			mv.addObject(product);

		} catch (shoppingSiteException e) {
			System.out.println(e.getMessage());
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		} catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
                    "means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
                    "means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

		return "redirect:/modify.htm?id=" + product.getProductID();
	}
	
	// check if input stock string is integer
	protected boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	  }  

}