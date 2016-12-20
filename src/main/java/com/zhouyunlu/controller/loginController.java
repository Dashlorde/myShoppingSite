package com.zhouyunlu.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.User;

@Controller
@RequestMapping("/login.htm")
public class loginController {
	@Autowired
	UserDAO userDao=new UserDAO();
	
	@RequestMapping(method=RequestMethod.GET)
	protected String getLoginPage(@ModelAttribute("user")User user, BindingResult result ){
		return "login";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected String login( Model model, @ModelAttribute("user")User user,HttpServletRequest request, HttpServletResponse response) throws shoppingSiteException, IOException{
		HttpSession session=request.getSession();
		if(userDao.login(user.getName(), user.getPassword())){
			session.setAttribute("username", user.getName());
			
			if(session.getAttribute("total")!=null &&(session.getAttribute("cart")!=null)){
				float total=0;
				Set<CartProduct> cart=(Set<CartProduct>) session.getAttribute("cart");
				Iterator<CartProduct> it=cart.iterator();
				while(it.hasNext()){
					CartProduct pro=it.next();
					if(pro.getProduct().getUsername().equals(user.getName())){
						it.remove();
					}
				}
				for(CartProduct p:cart){
					total+=p.getProduct().getProductPrice()*p.getQuantity();
				}
				session.setAttribute("total",total);
				session.setAttribute("cart", cart);
				
			}
			
			return "redirect:/showAllProducts.htm";
			
		}
		else{
			model.addAttribute("error","Invalid username or password!");
			return "login";
		}
	}
	
	
	
}
