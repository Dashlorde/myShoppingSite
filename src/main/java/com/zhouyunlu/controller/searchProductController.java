package com.zhouyunlu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.pojo.Product;

@Controller
public class searchProductController {

	@Autowired
	ProductDao productDao=new ProductDao();
	
	
	
	@RequestMapping(value="/search.htm", method=RequestMethod.GET)
	protected ModelAndView searchProduct(HttpServletRequest request) throws Exception{
		ArrayList<Product> productList=new ArrayList<Product>();
		List<Product> allProducts=productDao.getAllProducts();
		
		ModelAndView mv=new ModelAndView();
		
		String key=request.getParameter("searchProduct").toString();
		
		try{
			for(Product p: allProducts){
				if(p.getProductName().contains(key)){
					
					System.out.println("key is "+key+" -------------- product name is "+p.getProductName());
					productList.add(p);
					
				}
			}
			
		}catch(Exception e){
			return new ModelAndView("redirect:/showAllProducts.htm");
		}
		
		
		mv.addObject(productList);
		mv.setViewName("welcomePage");
		
		return mv;
	}
}
