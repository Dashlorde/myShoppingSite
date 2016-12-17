package com.zhouyunlu.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhouyunlu.DAO.OrderDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Address;
import com.zhouyunlu.pojo.Email;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller
@RequestMapping("/placeOrder.htm")
public class PlaceOrderController {

	@Autowired
	OrderDao orderDao=new OrderDao();
	
	@Autowired
	UserDAO userDao=new UserDAO();
	
	@RequestMapping(method=RequestMethod.GET)
	public String placeOrder(HttpServletRequest request) throws ParseException, shoppingSiteException{
		HttpSession session=request.getSession();
		List<Product> cart=(List<Product>) session.getAttribute("cart");
		User user=(User) session.getAttribute("user");
		String firstName=user.getFirstName();
		String lastName=user.getLastName();
		Email email=user.getEmail();
		String emailAddress=email.getEmailId();
		
		Address address=(Address) session.getAttribute("address");
		String buyerAddress=address.getAddress();
		String phone=address.getPhone();
		
		long buyerId=user.getId();
		float price=0;
		
		long sellerId=-1;
		
		DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date=new Date();
		Date currentDate=dateFormat.parse(dateFormat.format(date));
		
	// Separate cart list by seller id. 
		Iterator<Product> it=cart.iterator();
		TreeMap<String, List<Product>> map=new TreeMap<String, List<Product>>();
		while(it.hasNext()){
			Product product=it.next();
			String username=product.getUsername();
			if(map.containsKey(username)){
				ArrayList<Product> templist=(ArrayList<Product>) map.get(username);
				templist.add(product);
			}
			
			else{
				ArrayList<Product> templist=new ArrayList<Product>();
				templist.add(product);
				map.put(username, templist);
			}
		}
	
	// place each list into one order record	
		Set set=map.keySet();
		Iterator iterator=set.iterator();
		while(iterator.hasNext()){
			String username=(String) iterator.next();
			User seller=userDao.get(username);
			sellerId=seller.getId();
			ArrayList<Product> list=(ArrayList<Product>) map.get(username);
			List<Long> idList=new ArrayList();
			Iterator<Product> itId=list.iterator();
			while(itId.hasNext()){
				Product product=itId.next();
				long productId=product.getProductID();
				idList.add(productId);
			}
					
					
			for(Product p: list){
				price+=p.getProductPrice();
			}
			
			orderDao.create(buyerId, sellerId, firstName, lastName, buyerAddress, emailAddress, phone, date, idList, price);
		}
		
		return "checkoutSuccess";
	}
}
