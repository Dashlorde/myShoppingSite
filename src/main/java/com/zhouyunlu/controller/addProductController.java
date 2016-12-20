package com.zhouyunlu.controller;


import java.io.File;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	protected String doSubmitProduct(@ModelAttribute("product")@Valid Product product, BindingResult result, HttpServletRequest request ) throws Exception{
		HttpSession session=request.getSession();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request; 
		MultipartFile image=multipartRequest.getFile("image");
		
		
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
			
			imagePath=request.getServletContext().getRealPath("");
			imagePath+="/";
			UserDAO userDao=new UserDAO();
			String username=session.getAttribute("username").toString();
			User user= userDao.get(username);
			session.setAttribute("user", user);
			
			if(!image.isEmpty()){
			    fileName=System.currentTimeMillis()+image.getOriginalFilename();
				file=new File(imagePath+fileName);
				
				if(!file.exists()){
					file.mkdir();
				}
				context=request.getServletContext().getContextPath();
		
				System.out.println("context is: "+context+"     file name is: "+fileName);
				image.transferTo(file);
				System.out.println(file.getAbsolutePath());
			}
			else{
				
				return "addProductForm";
			}
						
			productDao.create(productName, productPrice, description, category, username, stock, context+"/"+fileName);
			
			
		} catch(Exception e){
				System.out.println(e.getMessage());
			}
		
		
		return "redirect:/viewMyProduct.htm?action=viewMyProduct";
		
	}
	
}
