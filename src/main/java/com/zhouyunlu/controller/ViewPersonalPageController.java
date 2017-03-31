package com.zhouyunlu.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.zhouyunlu.DAO.AddressDao;
import com.zhouyunlu.DAO.OrderDao;
import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.WishlistDao;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Address;
import com.zhouyunlu.pojo.Email;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;
import com.zhouyunlu.pojo.WishList;

@Controller
public class ViewPersonalPageController {

	@Autowired
	WishlistDao wishlistDao = new WishlistDao();

	@Autowired
	OrderDao orderDao = new OrderDao();

	@Autowired
	ProductDao productDao = new ProductDao();

	@Autowired
	AddressDao addressDao = new AddressDao();

	@RequestMapping(value = "/personalPage.htm", method = RequestMethod.GET)
	String viewPersonalPage(HttpServletRequest request, HttpServletResponse response, Model model) throws shoppingSiteException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		long id = user.getId();

		List<Order> orderlist = new ArrayList();
		orderlist = orderDao.getSomeOrders(id);
		List<Long> userwish = new ArrayList();
		List<WishList> wishlist = wishlistDao.getWishlistByUser(id);
		Address address=addressDao.getByUserId(user.getId());
		Email email=user.getEmail();
		
		session.setAttribute("currentURL", "redirect:/personalPage.htm");

		for (WishList w : wishlist) {
			long productId = w.getProductId();
			userwish.add(productId);
		}

		List<Long> wish = new ArrayList();
		wish = wishlistDao.getSomeProducts(id);

		List<Product> productlist = new ArrayList();

		for (Long w : wish) {
			Product p = productDao.getProductByID(w);
			productlist.add(p);
		}

		model.addAttribute("orderlist", orderlist);
		model.addAttribute("productlist", productlist);
		model.addAttribute("wish", userwish);
		model.addAttribute("address", address);
		model.addAttribute("email", email);

		return "personalPage";
	}

	

}
