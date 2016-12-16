package com.zhouyunlu.controller;

import java.util.ArrayList;
import java.util.Iterator;
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

@Controller
public class ManageCartController {

	@RequestMapping(value = "/addtocart.htm", method = RequestMethod.GET)
	public String addCart(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		ProductDao productDao = new ProductDao();
		float total = 0;

		String action = request.getParameter("action");
		if (action.equals("addtocart")) {
			ArrayList<Product> cart;

			if (session.getAttribute("cart") != null) {
				cart = (ArrayList<Product>) session.getAttribute("cart");
			} else {
				cart = new ArrayList<Product>();

			}

			long productID = Long.parseLong(request.getParameter("id").toString());
			Product product = productDao.getProductByID(productID);

			cart.add(product);
			if (!cart.contains(product)) {
				cart.add(product);
			}

			for (Product p : cart) {
				total += p.getProductPrice();
			}
			session.setAttribute("total", total);
			session.setAttribute("cart", cart);

		}
		return "viewCart";
	}

	@RequestMapping(value = "/viewCart.htm", method = RequestMethod.GET)
	protected String listCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "viewCart";
	}

}
