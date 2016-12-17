package com.zhouyunlu.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

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
	
	public List<Order> SellerViewAllOrder(User user) throws shoppingSiteException{
		List<Order> orderList=null;
		try{
			begin();
			Query q=getSession().createQuery("from Order where sellerId= :sellerId");
			q.setLong("sellerId", user.getId());
			orderList=q.list();
			commit();
			return orderList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the order"+ e.getMessage());
		}
	}
	
	public List<Order> SellerViewAllOrderInThreeMonths(User user) throws shoppingSiteException{
		List<Order> orderList=null;
		try{
			begin();
			Query q=getSession().createQuery("from Order where sellerId= :sellerId and date>DATE_SUB(curdate(), INTERVAL 3 MONTH");
			q.setLong("sellerId", user.getId());
			orderList=q.list();
			commit();
			return orderList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the order in 3 months"+ e.getMessage());
		}
	}
	
	public List<Order> SellerViewAllOrderInOneYear(User user) throws shoppingSiteException{
		List<Order> orderList=null;
		try{
			begin();
			Query q=getSession().createQuery("from Order where sellerId= :sellerId and date>DATE_SUB(curdate(), INTERVAL 1 YEAR");
			q.setLong("sellerId", user.getId());
			orderList=q.list();
			commit();
			return orderList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the order in 1 year"+ e.getMessage());
		}
	}
	
	public List<Order> BuyerViewAllOrder(User user) throws shoppingSiteException{
		List<Order> orderList=null;
		try{
			begin();
			Query q=getSession().createQuery("from Order where buyerId= :buyerId");
			q.setLong("buyerId", user.getId());
			orderList=q.list();
			commit();
			return orderList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the order"+ e.getMessage());
		}
	}
	
	public List<Order> BuyerViewAllOrderInThreeMonths(User user) throws shoppingSiteException{
		List<Order> orderList=null;
		try{
			begin();
			Query q=getSession().createQuery("from Order where buyerId= :buyerId and date>DATE_SUB(curdate(), INTERVAL 3 MONTH");
			q.setLong("buyerId", user.getId());
			orderList=q.list();
			commit();
			return orderList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the order in 3 months"+ e.getMessage());
		}
	}
	
	public List<Order> BuyerViewAllOrderInOneYear(User user) throws shoppingSiteException{
		List<Order> orderList=null;
		try{
			begin();
			Query q=getSession().createQuery("from Order where buyerId= :buyerId and date>DATE_SUB(curdate(), INTERVAL 1 YEAR");
			q.setLong("buyerId", user.getId());
			orderList=q.list();
			commit();
			return orderList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the order in 1 year"+ e.getMessage());
		}
	}
	
	public List<Long> orderDetail(long id) throws shoppingSiteException{
		List<Long> list=null;
		try{
			begin();
			Query q=getSession().createQuery("select productId from Item where orderId= :orderId");
			q.setLong("orderId", id);
			list=q.list();
			commit();
			return list;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get order detail"+ e.getMessage());
		}
			
	}
}
