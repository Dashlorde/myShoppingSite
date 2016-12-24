package com.zhouyunlu.controller;

import java.io.IOException;
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
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.xml.sax.SAXException;

import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.zhouyunlu.DAO.OrderDao;
import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.DAO.UserDAO;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Address;
import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Email;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.User;
import com.zhouyunlu.service.EmailUtil;
import com.zhouyunlu.service.EmailUtilImpl;
import com.zhouyunlu.service.PaypalService;

@Controller
public class PlaceOrderController {

	@Autowired
	OrderDao orderDao = new OrderDao();

	@Autowired
	UserDAO userDao = new UserDAO();

	@Autowired
	ProductDao productDao = new ProductDao();

	@RequestMapping(value= "/placeOrder.htm", method = RequestMethod.GET)
	public String placeOrder(HttpServletRequest request) throws ParseException, shoppingSiteException {
		HttpSession session = request.getSession();
		Set<CartProduct> cartSet = (Set<CartProduct>) session.getAttribute("cart");
		float amount=(float) session.getAttribute("total");
		User user = (User) session.getAttribute("user");
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		Email email = user.getEmail();
		String emailAddress = email.getEmailId();

		List<Order> orderList=new ArrayList<Order>();
		Address address = (Address) session.getAttribute("address");
		String buyerAddress = address.getAddress();
		String phone = address.getPhone();

		long buyerId = user.getId();
		

		long sellerId = -1;

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Date currentDate = dateFormat.parse(dateFormat.format(date));

		String redirectURL = null;

		// Separate cart list by seller id.
		Iterator<CartProduct> it = cartSet.iterator();
		TreeMap<String, List<CartProduct>> map = new TreeMap<String, List<CartProduct>>();
		while (it.hasNext()) {
			CartProduct cProduct = it.next();
			String username = cProduct.getProduct().getUsername();
			if (map.containsKey(username)) {
				ArrayList<CartProduct> templist = (ArrayList<CartProduct>) map.get(username);
				templist.add(cProduct);
			}

			else {
				ArrayList<CartProduct> templist = new ArrayList<CartProduct>();
				templist.add(cProduct);
				map.put(username, templist);
			}
		}

		// place each list into one order record
		Set<String> set = map.keySet();
		
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			float price = 0;
			String username = (String) iterator.next();
			User seller = userDao.get(username);
			//sellerSet.add(seller);
			sellerId = seller.getId();
			ArrayList<CartProduct> list = (ArrayList<CartProduct>) map.get(username);

			// add order id into a new list and insert into table
			List<Long> idList = new ArrayList<Long>();
			Iterator<CartProduct> itId = list.iterator();
			while (itId.hasNext()) {
				CartProduct cProduct = itId.next();
				long productId = cProduct.getProduct().getProductID();
				idList.add(productId);
				int quantity = cProduct.getQuantity();
				int currentStock = cProduct.getProduct().getStock() - quantity;
				productDao.updateStockAfterPlaceOrder(currentStock, productId);
			}

			for (CartProduct cp : list) {
				price += cp.getProduct().getProductPrice() * cp.getQuantity();
			}

			Order order = orderDao.create(buyerId, sellerId, firstName, lastName, buyerAddress, emailAddress, phone,
					date, idList, price, "not paid");

			long orderId = order.getOrderId();
			orderList.add(order);
			for (CartProduct cp : list) {
				long productId = cp.getProduct().getProductID();
				int quantity = cp.getQuantity();
				orderDao.addQuantityOfOrderItem(quantity, orderId, productId);
			}
		}
		session.setAttribute("orderList", orderList);
		PaypalService pay = new PaypalService();
		
		try {
			redirectURL=pay.paypalCheckOut(user, amount, cartSet);

		} catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException
				| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException
				| OAuthException | IOException | InterruptedException | ParserConfigurationException
				| SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			/*String userEmail = user.getEmail().getEmailId();
			EmailUtil emailUtil = new EmailUtilImpl();
			
			emailUtil.sendEmail(userEmail, user.getName(),  cartSet);
			session.removeAttribute("cart");
			*/
		
		
		return redirectURL;

	}
}
