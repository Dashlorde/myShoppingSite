package com.zhouyunlu.controller;

import java.io.PrintWriter;
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
	public String addCart(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {
		HttpSession session = request.getSession();
		ProductDao productDao = new ProductDao();
		float total = 0;
		String action=request.getParameter("action");
		Set<CartProduct> cart;

		if (session.getAttribute("cart") != null) {
			cart = (Set<CartProduct>) session.getAttribute("cart");
		} else {
			cart = new HashSet<CartProduct>();
		}

		long productID = Long.parseLong(request.getParameter("id").toString());
		Product product = productDao.getProductByID(productID);

		int quantity = 0;

		// add product into cart in product information page and get quantity from information page
		if (request.getParameter("quantity") != null && !request.getParameter("quantity").equals("")) {
			int num = 0;
			String quantityString = request.getParameter("quantity");
			
			//when cart already had this product, when cart has same product, replace it with the new one
			if (getCartProduct(productID, cart) != null) {
				CartProduct cp = getCartProduct(productID, cart);
				num = cp.getQuantity();
				
				//estimate storage and cart product quantity, if insufficient storage, aborting add cart action
				if((Integer.parseInt(quantityString) + num) >=product.getStock()){
					session.setAttribute("quantityError", "insufficient storage");
					return "redirect:/showProductInfo.htm?action=showProductInfo&id="+productID;
				}
				cart.remove(cp);
			}
			quantity = Integer.parseInt(quantityString) + num;
			
			//when cart does not have this product, estimate storage and cart product quantity, if insufficient storage, aborting add cart action
			if(quantity >=product.getStock()){
				session.setAttribute("quantityError", "insufficient storage");
				return "redirect:/showProductInfo.htm?action=showProductInfo&id="+productID;
			}
		} 
		
		else {
			int num = 0;
			if (getCartProduct(productID, cart) != null) {
				CartProduct cp = getCartProduct(productID, cart);
				num = cp.getQuantity();
				//when cart already had this product, estimate storage and cart product quantity, if insufficient storage, aborting add cart action
				if((num+1) >=product.getStock()){
					session.setAttribute("quantityError", "insufficient storage");
					return "redirect:/showProductInfo.htm?action=showProductInfo&id="+productID;
				}
				
				
				cart.remove(cp);
				
			}
			quantity = 1 + num;
			//when cart does not have this product, estimate storage and cart product quantity, if insufficient storage, aborting add cart action
			if(quantity >=product.getStock()){
				session.setAttribute("quantityError", "insufficient storage");
				return "redirect:/showProductInfo.htm?action=showProductInfo&id="+productID;
			}
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
	protected String addOneProduct(HttpServletRequest request, HttpServletResponse response, Model model) throws shoppingSiteException {
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
			int quantity = cProduct.getQuantity();
			
			
			if((quantity+1)>=product.getStock()){
				System.out.println(quantity+1);
				System.out.println(product.getStock());
				model.addAttribute("quantityError", "insufficient storage");
				return "viewCart";
			}
			cart.remove(cProduct);
			cProduct.setQuantity(quantity + 1);
			
		} else {
			cProduct = new CartProduct();
			cProduct.setProduct(product);
			cProduct.setQuantity(1);
			
			if(1 >=product.getStock()){
				session.setAttribute("quantityError", "insufficient storage");
				return "viewCart";
			}
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
	protected String changeQuantity(HttpServletRequest request, HttpServletResponse response, Model model){
		HttpSession session=request.getSession();
		Set<CartProduct> cart=(Set<CartProduct>) session.getAttribute("cart");
		String quantityString=request.getParameter("quantity");
		int quantity=Integer.parseInt(quantityString);
		String idString=request.getParameter("id");
		long id=Long.parseLong(idString);
		float total=0;
		
		CartProduct cProduct=getCartProduct(id, cart);
		if((quantity) >=cProduct.getProduct().getStock()){
			model.addAttribute("quantityError", "insufficient storage");
			return "viewCart";
		}
		
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

	//check if cart has this product
	protected CartProduct getCartProduct(long id, Set<CartProduct> cart) {
		for (CartProduct cp : cart) {
			if (cp.getProduct().getProductID() == id) {
				return cp;
			}
		}
		return null;
	}

}
