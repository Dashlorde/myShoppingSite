package com.zhouyunlu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller
public class viewMyProductController {

	@RequestMapping(value="/viewMyProduct.htm", method=RequestMethod.GET)
	protected ModelAndView viewMyProduct(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mv=new ModelAndView();
		ProductDao productDao=new ProductDao();
		
		HttpSession session=request.getSession();
		UserDAO userDao=new UserDAO();
		String username=session.getAttribute("username").toString();
		User user=userDao.get(username);
		session.setAttribute("user", user);
		
		String action=request.getParameter("action");
		
		if(action.equals("viewMyProduct")){
			List<Product> productList=productDao.getProductByName(username);
			mv.addObject(productList);
		}
		mv.setViewName("viewMyProduct");
		return mv;
	}
}
