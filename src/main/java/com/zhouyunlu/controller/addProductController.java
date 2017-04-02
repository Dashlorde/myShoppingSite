package com.zhouyunlu.controller;


import java.io.File;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.type.TextType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller 
@MultipartConfig
public class addProductController {
	
	
	@Autowired
	ProductDao productDao=new ProductDao();
	
	
	
	@RequestMapping(value="/addProduct.htm", method=RequestMethod.GET)
	protected String getAddProductPage(@ModelAttribute("product")Product product, BindingResult result){
		return "addProductForm";
	}
	
	
	
	@RequestMapping(value="/addProduct.htm", method=RequestMethod.POST)
	protected String doSubmitProduct(@ModelAttribute("product")@Valid Product product, BindingResult result, HttpServletRequest request, HttpServletResponse response ) throws Exception{
		HttpSession session=request.getSession();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		MultipartFile image=multipartRequest.getFile("image");
		
		//aws account key
		AWSCredentials credentials = new BasicAWSCredentials("id", "key");
		
		AmazonS3 s3client = new AmazonS3Client(credentials);
		
		//aws s3 bucket name
		String bucketName = "elasticbeanstalk-us-west-2-481664616485";
		
		if(result.hasErrors()){
			return "addProductForm";
		}
		
		String productName=product.getProductName();
		float productPrice=product.getProductPrice();
		String description=product.getDescription();
		String category=product.getCategory();
		int stock=product.getStock();
		String imagePath=null;
		
		File file;
		String fileName=null;
		String context=null;
		
		
		try{
			//imagePath in localhost
			imagePath=request.getServletContext().getRealPath("");
			imagePath+="/";
			
			//path upload to aws s3
			String awsPath="https://s3-us-west-2.amazonaws.com/elasticbeanstalk-us-west-2-481664616485/img";
			UserDAO userDao=new UserDAO();
			String username=session.getAttribute("username").toString();
			User user= userDao.get(username);
			session.setAttribute("user", user);
			
			if(!image.isEmpty()){
			    fileName=System.currentTimeMillis()+image.getOriginalFilename();
				file=new File(imagePath+fileName);
				
				/*if(!file.exists()){
					file.mkdir();
				}
				*/
				context=request.getServletContext().getContextPath();
		
				System.out.println("context is: "+context+"     file name is: "+fileName);
				image.transferTo(file);
				s3client.putObject(new PutObjectRequest(bucketName, "img/"+fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
				//System.out.println(file.getAbsolutePath());
			}
			else{
				
				return "addProductForm";
			}
			
			//if change to local tomcat server, image path is context+"/"+fileName
			productDao.create(productName, productPrice, description, category, username, stock, awsPath+"/"+fileName);
			
			
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
		
		
		return "redirect:/viewMyProduct.htm?action=viewMyProduct";
		
	}
	
}
