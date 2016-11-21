package com.zhouyunlu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/viewCart.htm")
public class listCartController {

		@RequestMapping(method=RequestMethod.GET)
		protected ModelAndView listCart(HttpServletRequest request, HttpServletResponse response)throws Exception{
			ModelAndView mv=new ModelAndView();
			HttpSession session=request.getSession();
			String action=request.getParameter("action");
			
			if(action.equals("viewcart")){
				
				if(session.getAttribute("total")!=null &&(session.getAttribute("cart")!=null)){
					float total=(Float) session.getAttribute("total");
					mv.addObject("total",total);
				}
				
				mv.setViewName("viewCart");
			}
			
			return mv;
		}
}
