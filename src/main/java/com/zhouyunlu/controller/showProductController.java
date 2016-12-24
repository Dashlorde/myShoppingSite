package com.zhouyunlu.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.CommentDao;
import com.zhouyunlu.DAO.OrderDao;
import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Comment;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;
import com.zhouyunlu.service.EmailUtil;
import com.zhouyunlu.service.EmailUtilImpl;

@Controller
public class showProductController {
	@Autowired
	ProductDao productDao=new ProductDao();
	
	@Autowired
	CommentDao commentDao=new CommentDao();
	
	@Autowired
	OrderDao orderDao=new OrderDao();
	
	UserDAO userDao=new UserDAO();
	
	
	@RequestMapping(value="/showAllProducts.htm", method=RequestMethod.GET)
	protected ModelAndView showAllProduct(HttpServletRequest request) throws Exception{
		List<Product> productList=null;
		ModelAndView mv=new ModelAndView();
		User user=null;
		String action=null;
		HttpSession session=request.getSession();
		
		//list products which are not belong to the user
		if(session.getAttribute("username")!=null){
			String username=session.getAttribute("username").toString();
			user=userDao.get(username);
			session.setAttribute("user", user);
			productList=productDao.getAllProducts(user);
			
			/*
			 * Check out success will redirect to showAllProducts.htm
			 * After paid, a confirmed email will send to buyer and order status will change to paid.
			 */
			if(request.getParameter("action")!=null){
				action=request.getParameter("action");
				if(action.equals("checkoutSuccess")){
					String userEmail = user.getEmail().getEmailId();
					EmailUtil emailUtil = new EmailUtilImpl();
					Set<CartProduct> cartProductSet=(Set) session.getAttribute("cart");
					List<Order> orderList=(List<Order>) session.getAttribute("orderList");
					for(Order order: orderList){
						orderDao.changeOrderStatus("paid", order.getOrderId());
					}

					emailUtil.sendEmail(userEmail, user.getName(),  cartProductSet);
					session.removeAttribute("cart");
				}
			}
			
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
		HttpSession session=request.getSession();
		String quantityError;
		List<Comment> commentList=null;
		if(action.equals("showProductInfo")){
			String i=request.getParameter("id").toString();
			long id=Long.parseLong(i);
			
			Product product=productDao.getProductByID(id);
			commentList=commentDao.getCommentByProduct(product);
			
			quantityError=(String) session.getAttribute("quantityError");
			session.setAttribute("quantityError", "");
			mv.addObject("quantityError", quantityError);
			mv.addObject("product", product);
			mv.addObject("commentList", commentList);
			mv.setViewName("showProductInfo");
		}
		
		
		return mv;
	}
}
