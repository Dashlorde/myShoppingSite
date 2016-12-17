package com.zhouyunlu.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.Product;

public class OrderDao extends DAO{

	public Order create(long buyerId, long sellerId, String firstName, String lastName, String address, String email, String phone, Date date, List<Long> item, float price) 
			throws shoppingSiteException{
		try{
			begin();
			Order order=new Order();
			order.setAddress(address);
			order.setBuyerId(buyerId);
			order.setSellerId(sellerId);
			order.setPhone(phone);
			order.setEmail(email);
			order.setFirstName(firstName);
			order.setLastName(lastName);
			order.setDate(date);
			order.setItem(item);
			order.setPrice(price);
			getSession().save(order);
			commit();
			return order;
		} catch (HibernateException e) {
            rollback();
            throw new shoppingSiteException("Exception while creating order: "+e.getMessage());
		}	
	}
}
