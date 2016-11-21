package com.zhouyunlu.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller
@MultipartConfig
public class changeProductController {
	
	@Autowired
	ProductDao productDao=new ProductDao();
	

	@RequestMapping(value="/deleteProduct.htm", method=RequestMethod.GET)
	protected ModelAndView deleteMyProduct(HttpServletRequest request) throws HibernateException, shoppingSiteException{
		ModelAndView mv=new ModelAndView();
		
		String productID=request.getParameter("id").toString();
		long id=Long.parseLong(productID);
		String action=request.getParameter("action");
		
		HttpSession session=request.getSession();
		UserDAO userDao=new UserDAO();
		String username=session.getAttribute("username").toString();
		User user=userDao.get(username);
		session.setAttribute("user", user);
		
		try{
			
			ProductDao productDao=new ProductDao();
			Product product=productDao.getProductByID(id);
			
			
			if(action.equals("deleteProduct")){
				productDao.delete(product);
				System.out.println("delete product id: "+id);
				List<Product> productList=productDao.getProductByName(username);
				mv.addObject(productList);
				mv.setViewName("viewMyProduct");
				
			}
			
		} catch(shoppingSiteException e){
			System.out.println(e.getMessage());
		} catch(HibernateException e){
			System.out.println(e.getMessage());
		}
		
		return mv;
	}
	
	@RequestMapping(value="/modify.htm", method=RequestMethod.GET)
	protected ModelAndView goModifyPage( HttpServletRequest request) throws shoppingSiteException{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		long productId=Long.parseLong(request.getParameter("id"));
		
		ProductDao productDao=new ProductDao();
		Product product=productDao.getProductByID(productId);
		session.setAttribute("product", product);
		mv.addObject(product);
		mv.setViewName("modifyProduct");
		return mv;
		
	}
	
	@RequestMapping(value="/modifyProduct.htm", method=RequestMethod.POST)
	protected String modifyMyProduct( HttpServletRequest request)throws Exception{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		
		//long productId=Long.parseLong(request.getParameter("id").toString());
		Product product=(Product) session.getAttribute("product");
		String description=request.getParameter("description").toString();
		
		try{
			
			//Product product=productDao.getProductByID(productId);
			String imagePath=null;
			String fileName=null;
			imagePath=request.getServletContext().getRealPath("");
			imagePath+="/";
			String context=null;
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
			MultipartFile image=multipartRequest.getFile("image");
			 
			if(!description.isEmpty()){
				System.out.println("product name is: "+product.getProductName());
				System.out.println("change description to: "+description);
				
				productDao.modifyDescription(description, product);
				
			}
			
				if(!image.isEmpty()){
				    fileName=System.currentTimeMillis()+image.getOriginalFilename();
					File file=new File(imagePath+fileName);
					
					if(!file.exists()){
						file.mkdir();
					}
					context=request.getServletContext().getContextPath();
			
					System.out.println("context is: "+context+"     file name is: "+fileName);
					image.transferTo(file);
					System.out.println(file.getAbsolutePath());
					productDao.modifyImage(context+"/"+fileName, product);
				}
				
			mv.addObject(product);
			//mv.setViewName("redirect:/modify");
			
		}catch(shoppingSiteException e){
			System.out.println(e.getMessage());
		} catch(HibernateException e){
			System.out.println(e.getMessage());
		}
		
		//return "redirect:/viewMyProduct.htm?action=viewMyProduct";
		return "redirect:/modify.htm?id="+product.getProductID();
	}
}
