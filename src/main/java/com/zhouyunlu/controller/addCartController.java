package com.zhouyunlu.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.pojo.Product;

@Controller
@RequestMapping("/addtocart.htm")
public class addCartController {
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView addCart(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		ProductDao productDao=new ProductDao();
		ModelAndView mv=new ModelAndView();
		
		String action=request.getParameter("action");
		if(action.equals("addtocart")){
			ArrayList<Product> cart;
			
			if(session.getAttribute("cart")!=null){
				cart=(ArrayList<Product>) session.getAttribute("cart");
			} else{
				cart=new ArrayList<Product>();
				
			}
			
			long productID=Long.parseLong(request.getParameter("id").toString());
			Product product=productDao.getProductByID(productID);
			
			cart.add(product);
			if(!cart.contains(product)){
				cart.add(product);
			}
			
			float total=0;
			for(Product p:cart){
				total+=p.getProductPrice();
			}
			
			session.setAttribute("total", total);
			session.setAttribute("cart", cart);
			mv.addObject("total", total);
			mv.setViewName("viewCart");
			
		}
		
		
		return mv;
		
	}

}
