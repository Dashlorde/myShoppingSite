package com.zhouyunlu.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.AddressDao;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Address;
import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Email;
import com.zhouyunlu.pojo.User;

@Controller
@RequestMapping("/checkout.htm")
public class CheckOutController {
	
	@Autowired
	AddressDao addressDao=new AddressDao();

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView checkOut(HttpServletRequest request, @ModelAttribute("user")User user) throws shoppingSiteException{
		
		ModelAndView mv=new ModelAndView();
		
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		Address address=null;
		if(action.equals("checkout")){
			if(session.getAttribute("user")!=null){
				User u=(User) session.getAttribute("user");
				
				long userId=u.getId();
				if(addressDao.getByUserId(userId)!=null){
					address=addressDao.getByUserId(u.getId());
					System.out.println(address.getAddress()); 
				}
				
				Email email=u.getEmail();
				mv.addObject("address", address);
				mv.addObject("email", email);
				session.setAttribute("address", address);
				
				//check if cart is empty
				if(session.getAttribute("cart")==null){
					mv.setViewName("viewCart");
					return mv;
				}else{
					Set<CartProduct> cart=(Set<CartProduct>) session.getAttribute("cart");
					if(cart.isEmpty()){
						mv.setViewName("viewCart");
						return mv;
					}
				}
				mv.setViewName("checkOutPage");
			}
			
			else mv.setViewName("login");
			session.setAttribute("currentURL", "viewCart");
			
		}
		
		return mv;
	}
}
