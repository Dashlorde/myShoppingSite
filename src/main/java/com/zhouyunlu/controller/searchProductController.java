package com.zhouyunlu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller
public class searchProductController {

	@Autowired
	ProductDao productDao=new ProductDao();
	
	@Autowired
	UserDAO userDao=new UserDAO();
	
	
	@RequestMapping(value="/search.htm", method=RequestMethod.GET)
	protected ModelAndView searchProduct(HttpServletRequest request) throws Exception{
		ArrayList<Product> productList=new ArrayList<Product>();
		HttpSession session=request.getSession();
		List<Product> allProducts=null;
		ModelAndView mv=new ModelAndView();
		User user=null;
		String username=(String) session.getAttribute("username");
		
		String key=request.getParameter("searchProduct").toString();
		
		if(username!=null){
			user=userDao.get(username);
			 allProducts=productDao.getAllProducts(user);
		}
		else{
			allProducts=productDao.getAllProducts();
		}
		
		try{
			for(Product p: allProducts){
				if(Pattern.compile(Pattern.quote(key), Pattern.CASE_INSENSITIVE).matcher(p.getProductName()).find()){
					
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
