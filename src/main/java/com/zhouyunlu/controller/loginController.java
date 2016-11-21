package com.zhouyunlu.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.Exception.shoppingSiteException;
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
			return "redirect:/showAllProducts.htm";
			
		}
		else{
			model.addAttribute("error","Invalid username or password!");
			return "login";
		}
	}
	
	
	
}
