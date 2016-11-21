package com.zhouyunlu.controller;



import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.DAO.ProductDao;

@Controller
@RequestMapping("/deleteCart.htm")
public class deleteCartController {
	
	@Autowired
	ProductDao productDao=new ProductDao();
	

	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView deleteCart(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mv=new ModelAndView();
		HttpSession session=request.getSession();
		String action=request.getParameter("action");
		
		if(action.equals("deleteCart")){
			String productID=request.getParameter("id").toString();
			long productId=Long.parseLong(productID);
			
			
			ArrayList<Product> cart= (ArrayList<Product>) session.getAttribute("cart");
			Product product=productDao.getProductByID(productId);
	
			for(Product p:cart){
				
				System.out.println(p.getProductName());
				
			}
			
			System.out.println("------------------------deleting--------------------");
			
			Iterator<Product> it=cart.iterator();
			while(it.hasNext()){
				if(it.next().getProductID()==product.getProductID()){
					it.remove();
					System.out.println("delete product in cart: "+productID+" "+product.getProductName());
				}
			}
			
			session.setAttribute("cart", cart);
			
			
			if(cart!=null){
				
				float total=0;
				for(Product p:cart){
					total+=p.getProductPrice();
					System.out.println(p.getProductName());
					
				}
				session.setAttribute("total", total);
				mv.addObject("total", total);
			}
			
			
			
			//mv.addObject("cart",cart);
			
			
			
			
		}
		mv.setViewName("viewCart");
		return mv;
	}
	
	
}
