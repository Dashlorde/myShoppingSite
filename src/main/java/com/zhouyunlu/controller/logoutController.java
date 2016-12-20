package com.zhouyunlu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/logout.htm")
public class logoutController {
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView userLogout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		String action=request.getParameter("action");
		ModelAndView mv=new ModelAndView();
		if(action.equals("logout")){
			session.invalidate();
			mv=new ModelAndView("redirect:/showAllProducts.htm");
		}
		
		return mv;
	}

}
