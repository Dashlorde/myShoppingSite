package com.zhouyunlu.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zhouyunlu.DAO.CommentDao;
import com.zhouyunlu.DAO.OrderDao;
import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.DAO.WishlistDao;
import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Comment;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;
import com.zhouyunlu.pojo.WishList;
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
	
	@Autowired
	UserDAO userDao=new UserDAO();
	
	@Autowired
	WishlistDao wishlistDao=new WishlistDao();
	
	
	@RequestMapping(value="/showAllProducts.htm", method=RequestMethod.GET)
	protected ModelAndView showAllProduct(HttpServletRequest request) throws Exception{
		List<Product> productList=null;
		ModelAndView mv=new ModelAndView();
		User user=null;
		String action=null;
		HttpSession session=request.getSession();
		List<Long> wish=new ArrayList();
		//list products which are not belong to the user
		if(session.getAttribute("username")!=null){
			String username=session.getAttribute("username").toString();
			user=userDao.get(username);
			session.setAttribute("user", user);
			productList=productDao.getAllProducts(user);
			long userId=user.getId();
			List<WishList> wishlist=wishlistDao.getWishlistByUser(userId);
			
			for(WishList w:wishlist){
				long productId=w.getProductId();
				wish.add(productId);
			}
		
			
			
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

					//emailUtil.sendEmail(userEmail, user.getName(),  cartProductSet);
					emailUtil.sendEmailAsync(userEmail, user.getName(), cartProductSet);
					session.removeAttribute("cart");
				}
			}
			
		}
		else{
			 productList=productDao.getAllProducts();
		}

		
		mv.addObject("productList", productList);
		mv.addObject("wish", wish);
		
		mv.setViewName("welcomePage");
		return mv;
	}
	
	@RequestMapping(value="/showElectronics.htm", method=RequestMethod.GET)
	protected ModelAndView showElectronics(HttpServletRequest request) throws Exception{
		List<Product> productList=null;
		ModelAndView mv=new ModelAndView();
		User user=null;
		HttpSession session=request.getSession();
		List<Long> wish=new ArrayList();
		if(session.getAttribute("username")!=null){
			String username=session.getAttribute("username").toString();
			user=userDao.get(username);
			session.setAttribute("user", user);
			productList=productDao.getProductByCategory("Electronics", user);
			long userId=user.getId();
			List<WishList> wishlist=wishlistDao.getWishlistByUser(userId);
			
			for(WishList w:wishlist){
				long productId=w.getProductId();
				wish.add(productId);
			}
		
		}
		else{
			productList=productDao.getProductByCategory("Electronics");
		}
		
		mv.addObject("productList", productList);
		mv.addObject("wish", wish);
		mv.setViewName("welcomePage");
		return mv;
	}
	
	@RequestMapping(value="/showComputers.htm", method=RequestMethod.GET)
	protected ModelAndView showComputers(HttpServletRequest request) throws Exception{
		List<Product> productList=null;
		ModelAndView mv=new ModelAndView();
		User user=null;
		HttpSession session=request.getSession();
		List<Long> wish=new ArrayList();
		
		if(session.getAttribute("username")!=null){
			String username=session.getAttribute("username").toString();
			 user=userDao.get(username);
			 
			 long userId=user.getId();
				List<WishList> wishlist=wishlistDao.getWishlistByUser(userId);
				
				for(WishList w:wishlist){
					long productId=w.getProductId();
					wish.add(productId);
				}
			session.setAttribute("user", user);
			productList=productDao.getProductByCategory("Computers", user);
		}
		else{
			 productList=productDao.getProductByCategory("Computers");
		}

		mv.addObject("productList",productList);
		mv.addObject("wish", wish);
		mv.setViewName("welcomePage");
		return mv;
	}
	
	@RequestMapping(value="/showProductInfo.htm", method=RequestMethod.GET)
	protected ModelAndView showProductInfo(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView();
		String action=request.getParameter("action").toString();
		HttpSession session=request.getSession();
		String quantityError;
		User user=null;
		long userId=-1;
		List<Comment> commentList=null;
		List<Long> wish=new ArrayList();
		if(session.getAttribute("username")!=null){
			String username=session.getAttribute("username").toString();
			 user=userDao.get(username);
			 userId=user.getId();
			 
			 List<WishList> wishlist=wishlistDao.getWishlistByUser(userId);
				for(WishList w:wishlist){
					long productId=w.getProductId();
					wish.add(productId);
				}
			mv.addObject("wish", wish);
		}
		if(action.equals("showProductInfo")){
			String i=request.getParameter("id").toString();
			long id=Long.parseLong(i);
			
			Product product=productDao.getProductByID(id);
			commentList=commentDao.getCommentByProduct(product);
			
			User seller=userDao.get(product.getUsername());
		
			String sellerEmail=seller.getEmail().getEmailId();
			
			quantityError=(String) session.getAttribute("quantityError");
			session.setAttribute("quantityError", "");
			mv.addObject("quantityError", quantityError);
			mv.addObject("product", product);
			mv.addObject("sellerEmail", sellerEmail);
			mv.addObject("commentList", commentList);
			
			mv.setViewName("showProductInfo");
		}
		
		
		return mv;
	}
}
