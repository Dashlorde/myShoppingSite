package com.zhouyunlu.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.pojo.User;

@Controller
@RequestMapping("/adduser.htm")

public class addUserFormController {
	
	@Autowired
	UserDAO userDao = new UserDAO();

	@RequestMapping(method = RequestMethod.GET)
	protected String getRegisterPage(@ModelAttribute("user") User user, BindingResult result) {
		return "addUserForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user")@Valid User user, BindingResult result, Model model) throws Exception {

		

		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();


		if (result.hasErrors()) {
			mv.setViewName("addUserForm");

		}

		String action = request.getParameter("action");
		if (action.equals("ajaxCheck")) {
			PrintWriter out = response.getWriter();
			if (userDao.get(request.getParameter("username")) != null) {
				out.println("username already exists");		
			} else if(request.getParameter("username")==""){
				out.println("username cannot be empty");
			} else {
				out.println("username is available");	
			}
			return null;
		}

		else if (action.equals("adduser")) {
			try{
				if (userDao.get(user.getName()) == null) {
					userDao.create(user.getName(), user.getPassword(), user.getEmail().getEmailId(),
							user.getFirstName(), user.getLastName());
					session.setAttribute("username", user.getName());

					mv.setViewName("redirect:/showAllProducts.htm");
					return mv;
				} 

				else {
					mv.setViewName("addUserForm");
					return mv;
				}
				
			} catch(javax.validation.ConstraintViolationException e){
				e.printStackTrace();
				String error="error email format";
				model.addAttribute("emailError", error);
				mv.setViewName("addUserForm");
				return mv;
			}

			
		}

		return mv;
	}

}
