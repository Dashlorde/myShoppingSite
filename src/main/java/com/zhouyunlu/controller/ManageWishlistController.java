package com.zhouyunlu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.WishlistDao;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;
import com.zhouyunlu.pojo.WishList;

@Controller
public class ManageWishlistController {

	@Autowired
	WishlistDao wishlistDao = new WishlistDao();
	
	@Autowired
	ProductDao productDao=new ProductDao();

	@RequestMapping(value = "/addToWish.htm", method = RequestMethod.GET)
	public String addToWishlist(HttpServletRequest request, HttpServletResponse response) throws shoppingSiteException {

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");

		long userId = u.getId();
		String idString = request.getParameter("id");
		long productId = Long.parseLong(idString);

		wishlistDao.create(userId, productId);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "removeFromWish.htm", method = RequestMethod.GET)
	public String removeFromWishlist(HttpServletRequest request, HttpServletResponse response) throws shoppingSiteException {
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		long userId = u.getId();
		String idString = request.getParameter("id");
		long productId = Long.parseLong(idString);
		WishList wish = wishlistDao.getWishList(userId, productId);
		wishlistDao.delete(wish);

		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@RequestMapping(value = "/productAddToWish.htm", method = RequestMethod.GET)
	public String productAddToWishlist(HttpServletRequest request, HttpServletResponse response) throws shoppingSiteException {

		HttpSession session = request.getSession();
		String returnPage=null;
		if (session.getAttribute("user") != null) {
			User u = (User) session.getAttribute("user");

			long userId = u.getId();
			String idString = request.getParameter("id");
			long productId = Long.parseLong(idString);

			wishlistDao.create(userId, productId);

			returnPage = request.getHeader("Referer");
		}
		else returnPage="/login.htm";
		
		return "redirect:" + returnPage;
	}
	
	@RequestMapping(value="/wishlist.htm", method=RequestMethod.GET)
	public String viewWishlist(HttpServletRequest request, HttpServletResponse response, Model model) throws shoppingSiteException{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		long userId=user.getId();
		List<WishList> wish=new ArrayList();		
		wish=wishlistDao.getWishlistByUser(userId);
		
		List<Product> plist=new ArrayList();
		for(WishList w: wish){
			long pid=w.getProductId();
			Product p=productDao.getProductByID(pid);
			plist.add(p);
		}
		model.addAttribute("productList", plist);
		return "wishlist";
	}


}
