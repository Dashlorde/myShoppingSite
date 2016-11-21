package com.zhouyunlu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller
public class showProductController {
	@Autowired
	ProductDao productDao=new ProductDao();
	
	UserDAO userDao=new UserDAO();
	
	@RequestMapping(value="/showAllProducts.htm", method=RequestMethod.GET)
	protected ModelAndView showAllProduct(HttpServletRequest request) throws Exception{
		List<Product> productList=null;
		ModelAndView mv=new ModelAndView();
		User user=null;
		HttpSession session=request.getSession();
		if(session.getAttribute("username")!=null){
			String username=session.getAttribute("username").toString();
			user=userDao.get(username);
			session.setAttribute("user", user);
			productList=productDao.getAllProducts(user);
		}
		else{
			 productList=productDao.getAllProducts();
		}

		
		mv.addObject(productList);
		
		mv.setViewName("welcomePage");
		return mv;
	}
	
	@RequestMapping(value="/showElectronics.htm", method=RequestMethod.GET)
	protected ModelAndView showElectronics(HttpServletRequest request) throws Exception{
		List<Product> productList=null;
		ModelAndView mv=new ModelAndView();
		User user=null;
		HttpSession session=request.getSession();
		
		if(session.getAttribute("username")!=null){
			String username=session.getAttribute("username").toString();
			user=userDao.get(username);
			session.setAttribute("user", user);
			productList=productDao.getProductByCategory("Electronics", user);
		}
		else{
			productList=productDao.getProductByCategory("Electronics");
		}
		
		mv.addObject(productList);
		mv.setViewName("welcomePage");
		return mv;
	}
	
	@RequestMapping(value="/showComputers.htm", method=RequestMethod.GET)
	protected ModelAndView showComputers(HttpServletRequest request) throws Exception{
		List<Product> productList=null;
		ModelAndView mv=new ModelAndView();
		User user=null;
		HttpSession session=request.getSession();
		if(session.getAttribute("username")!=null){
			String username=session.getAttribute("username").toString();
			 user=userDao.get(username);
			session.setAttribute("user", user);
			productList=productDao.getProductByCategory("Computers", user);
		}
		else{
			 productList=productDao.getProductByCategory("Computers");
		}

		mv.addObject(productList);
		mv.setViewName("welcomePage");
		return mv;
	}
	
	@RequestMapping(value="/showProductInfo.htm", method=RequestMethod.GET)
	protected ModelAndView showProductInfo(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		String action=request.getParameter("action").toString();
		
		if(action.equals("showProductInfo")){
			String i=request.getParameter("id").toString();
			long id=Long.parseLong(i);
			
			Product product=productDao.getProductByID(id);
			mv.addObject(product);
			mv.setViewName("showProductInfo");
		}
		
		
		return mv;
	}
}
