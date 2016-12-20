package com.zhouyunlu.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Product;

@Controller
public class ManageCartController {

	@RequestMapping(value = "/addtocart.htm", method = RequestMethod.POST)
	public String addCart(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		ProductDao productDao = new ProductDao();
		float total = 0;

		Set<CartProduct> cart;

		if (session.getAttribute("cart") != null) {
			cart = (Set<CartProduct>) session.getAttribute("cart");
		} else {
			cart = new HashSet<CartProduct>();
		}

		long productID = Long.parseLong(request.getParameter("id").toString());
		Product product = productDao.getProductByID(productID);

		int quantity = 0;

		if (request.getParameter("quantity") != null && !request.getParameter("quantity").equals("")) {
			int num = 0;
			String quantityString = request.getParameter("quantity");
			quantity = Integer.parseInt(quantityString) + num;
			if (getCartProduct(productID, cart) != null) {
				CartProduct cp = getCartProduct(productID, cart);
				num = cp.getQuantity();
				cart.remove(cp);
			}
			
		} 
		
		else {
			int num = 0;
			if (getCartProduct(productID, cart) != null) {
				CartProduct cp = getCartProduct(productID, cart);
				num = cp.getQuantity();
				cart.remove(cp);
			}
			quantity = 1 + num;
		}

		CartProduct cProduct = new CartProduct();
		cProduct.setProduct(product);
		cProduct.setQuantity(quantity);
		cart.add(cProduct);

		for (CartProduct cp : cart) {
			total += cp.getProduct().getProductPrice() * cp.getQuantity();
		}
		
		session.setAttribute("total", total);
		session.setAttribute("cart", cart);

		return "viewCart";
	}

	@RequestMapping(value = "/viewCart.htm", method = RequestMethod.GET)
	protected String listCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "viewCart";
	}

	@RequestMapping(value = "/addOneToCart.htm", method = RequestMethod.GET)
	protected String addOneProduct(HttpServletRequest request) throws shoppingSiteException {
		HttpSession session = request.getSession();
		ProductDao productDao = new ProductDao();
		CartProduct cProduct;
		float total = 0;
		Set<CartProduct> cart;
		String idString = request.getParameter("id");
		long id = Long.parseLong(idString);
		if (session.getAttribute("cart") != null) {
			cart = (Set<CartProduct>) session.getAttribute("cart");
		} else {
			cart = new HashSet<CartProduct>();
		}

		Product product = productDao.getProductByID(id);
		if (getCartProduct(id, cart) != null) {
			cProduct = getCartProduct(id, cart);
			cart.remove(cProduct);
			int quantity = cProduct.getQuantity();
			cProduct.setQuantity(quantity + 1);
		} else {
			cProduct = new CartProduct();
			cProduct.setProduct(product);
			cProduct.setQuantity(1);
		}
		cart.add(cProduct);

		for (CartProduct cp : cart) {
			total += cp.getProduct().getProductPrice() * cp.getQuantity();
		}
		session.setAttribute("total", total);
		session.setAttribute("cart", cart);

		return "viewCart";

	}
	
	@RequestMapping(value="/changeQuantity.htm", method=RequestMethod.POST)
	protected String changeQuantity(HttpServletRequest request){
		HttpSession session=request.getSession();
		Set<CartProduct> cart=(Set<CartProduct>) session.getAttribute("cart");
		String quantityString=request.getParameter("quantity");
		int quantity=Integer.parseInt(quantityString);
		String idString=request.getParameter("id");
		long id=Long.parseLong(idString);
		float total=0;
		CartProduct cProduct=getCartProduct(id, cart);
		cart.remove(cProduct);
		if(quantity>0){
			cProduct.setQuantity(quantity);
			cart.add(cProduct);
		}
		
		for (CartProduct cp : cart) {
			total += cp.getProduct().getProductPrice() * cp.getQuantity();
		}
		session.setAttribute("total", total);
		session.setAttribute("cart", cart);
		
		return "viewCart";
	}

	protected CartProduct getCartProduct(long id, Set<CartProduct> cart) {
		for (CartProduct cp : cart) {
			if (cp.getProduct().getProductID() == id) {
				return cp;
			}
		}
		return null;
	}

}
