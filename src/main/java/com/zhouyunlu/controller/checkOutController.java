package com.zhouyunlu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.pojo.User;

@Controller
@RequestMapping("/checkout.htm")
public class checkOutController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView checkOut(HttpServletRequest request,@ModelAttribute("user")User user){
		
		ModelAndView mv=new ModelAndView();
		
		String action=request.getParameter("action");
		HttpSession session=request.getSession();
		
		if(action.equals("checkout")){
			if(session.getAttribute("username")!=null){
				mv.setViewName("checkOutPage");
			}
			
			else mv.setViewName("login");
		}
		
		return mv;
	}
}
