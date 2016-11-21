package com.zhouyunlu.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.User;

@Controller
@RequestMapping("/adduser.htm")

public class addUserFormController {
  @Autowired
  @Qualifier("userValidator")
  userValidator validator;
  
  @Autowired
  UserDAO userDao=new UserDAO();
 
  
  @InitBinder
  private void initBinder(WebDataBinder binder){
	  binder.setValidator(validator);
	  
  }
  
  @RequestMapping(method=RequestMethod.GET)
  protected String getRegisterPage(@ModelAttribute("user")User user, BindingResult result){
	 return "addUserForm"; 
  }
  
  @RequestMapping(method=RequestMethod.POST)
  protected ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("user")User user, BindingResult result, Model model) throws Exception{
	  
	  HttpSession session=request.getSession();
	  ModelAndView mv=new ModelAndView();
	  
	 
	  validator.validate(user, result);
	  
	  
	  if(result.hasErrors()){
		  //return "addUserForm";
		  mv.setViewName("addUserForm");
		 
	  }
	  
	 /* else if(userDao.get(user.getName())!=null){
		  model.addAttribute("error", "username already exists!");
		  return "addUserForm";
	  }
	  */
	  
	  String action=request.getParameter("action");
	  if(action.equals("ajaxCheck")){
		  
		  PrintWriter out=response.getWriter();
		  if(userDao.get(request.getParameter("username"))!=null){
			 out.println("username already exists");
		  }
		  else{
			  out.println("username is available");
		  }
		  
		  return null;
		  
	  }
	  
	  else if(action.equals("adduser")){
		  if(userDao.get(user.getName())!=null){
			 mv.setViewName("addUserForm");
			 return mv;
		  }
		  
		  else{
			  try{
				  System.out.println("test");
				  
				  System.out.println("test1");
				
				  userDao.create(user.getName(), user.getPassword(), user.getEmail().getEmailId(),user.getFirstName(), user.getLastName());
				  session.setAttribute("username", user.getName());
				  
			  } catch(shoppingSiteException e){
				  System.out.println("Exception: "+e.getMessage());
			  }
			  
			  mv.setViewName("welcomePage");
		  }
		  
	  }
		  
		  
	  
	  
	  
	  return mv;
  }	  
	  
  }	 
	  
	  
  
	
	

