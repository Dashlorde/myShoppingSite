package com.zhouyunlu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhouyunlu.DAO.OrderDao;
import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller
public class ManageOrderController {

	@Autowired
	OrderDao orderDao=new OrderDao();
	
	@Autowired
	ProductDao productDao=new ProductDao();
	
	@RequestMapping(value="/viewOrder.htm", method=RequestMethod.GET)
	protected String sellerViewOrder(HttpServletRequest request, HttpServletResponse response, Model model){
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Order> orderList=new ArrayList<Order>();
		
		try {
			orderList=orderDao.SellerViewAllOrder(user);
			model.addAttribute("orderList", orderList);
			
		} catch (shoppingSiteException e) {
			e.printStackTrace();
		}
		
		return "viewOrder";
	}
	
	@RequestMapping(value="/sellerSortOrder.htm", method=RequestMethod.GET)
	protected String sellerSortOrder(HttpServletRequest request, Model model) throws shoppingSiteException{
		String sort=request.getParameter("sort");
		List<Order> orderList=new ArrayList<Order>();
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		if(sort.equals("threeMonths")){
			orderList=orderDao.SellerViewAllOrderInThreeMonths(user);
		}
		
		else if(sort.equals("oneYear")){
			orderList=orderDao.SellerViewAllOrderInOneYear(user);
		}
		
		else if(sort.equals("all")){
			orderList=orderDao.SellerViewAllOrder(user);
		}
		
		model.addAttribute("orderList", orderList);
		return "viewOrder";
	}
	
	@RequestMapping(value="/orderHistory.htm", method=RequestMethod.GET)
	protected String buyerViewOrder(HttpServletRequest request, Model model){
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Order> orderList=new ArrayList<Order>();
		
		try {
			orderList=orderDao.BuyerViewAllOrder(user);
			model.addAttribute("orderList", orderList);
			
		} catch (shoppingSiteException e) {
			e.printStackTrace();
		}
		
		return "orderHistory";
	}
	
	
	@RequestMapping(value="/buyerSortOrder.htm", method=RequestMethod.GET)
	protected String buyerSortOrder(HttpServletRequest request, Model model) throws shoppingSiteException{
		String sort=request.getParameter("sort");
		List<Order> orderList=new ArrayList<Order>();
		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		if(sort.equals("threeMonths")){
			orderList=orderDao.BuyerViewAllOrderInThreeMonths(user);
		}
		
		else if(sort.equals("oneYear")){
			orderList=orderDao.BuyerViewAllOrderInOneYear(user);
		}
		
		else if(sort.equals("all")){
			orderList=orderDao.BuyerViewAllOrder(user);
		}
		
		model.addAttribute("orderList", orderList);
		return "orderHistory";
	}
	
	
	
	@RequestMapping(value="/orderDetail.htm", method=RequestMethod.GET)
	protected String viewOrderDetail(HttpServletRequest request, Model model) throws shoppingSiteException{
		String idString=request.getParameter("id");
		long id=Long.parseLong(idString);
		List<Long> productIdList=orderDao.orderDetail(id);
		Map<Product, Integer> productList=new HashMap<Product, Integer>();
		Iterator<Long> it=productIdList.iterator();
		Order order=orderDao.getOrderById(id);
		long buyerId=order.getBuyerId();
		
		while(it.hasNext()){
			long productId=(long) it.next();
			Product product=productDao.getProductByID(productId);
			int quantity=orderDao.getItemQuantity(id, productId);
			System.out.println("quantity: "+quantity);
			productList.put(product, quantity);
		}
		model.addAttribute("productList", productList);
		model.addAttribute("buyerId", buyerId);
		
		return "orderDetail";
	}
}
