package com.zhouyunlu.controller;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Product;

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
			
			
			Set<CartProduct> cart= (Set<CartProduct>) session.getAttribute("cart");
			Product product=productDao.getProductByID(productId);
	
			for(CartProduct cp:cart){
				
				System.out.println(cp.getProduct().getProductName());
				
			}
			
			System.out.println("------------------------deleting--------------------");
			
			Iterator<CartProduct> it=cart.iterator();
			while(it.hasNext()){
				if(it.next().getProduct().getProductID()==product.getProductID()){
					it.remove();
					System.out.println("delete product in cart: "+productID+" "+product.getProductName());
				}
			}
			
			session.setAttribute("cart", cart);
			
			
			if(cart!=null){
				
				float total=0;
				for(CartProduct p:cart){
					total+=p.getProduct().getProductPrice();

				}
				session.setAttribute("total", total);
				mv.addObject("total", total);
			}

			
		}
		mv.setViewName("viewCart");
		return mv;
	}
	
	
}
