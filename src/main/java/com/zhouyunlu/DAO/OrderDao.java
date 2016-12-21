package com.zhouyunlu.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

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
			Query q=getSession().createQuery("from Order where sellerId= :sellerId order by date DESC");
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
			
			SQLQuery q= getSession().createSQLQuery("select * from orderTable where seller_id= :sellerId and date>date_sub(now(), interval 3 MONTH) order by date DESC"); 
			q.setParameter("sellerId", user.getId());
			//q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			q.addEntity(Order.class);
			orderList=q.list();
			
			commit();
			return orderList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the order in 3 months "+ e.getMessage());
		}
	}
	
	public List<Order> SellerViewAllOrderInOneYear(User user) throws shoppingSiteException{
		List<Order> orderList=null;
		try{
			begin();
			SQLQuery q= getSession().createSQLQuery("select * from orderTable where seller_id= :sellerId and date>date_sub(now(), interval 1 YEAR) order by date DESC"); 
			q.setParameter("sellerId", user.getId());
			//q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			q.addEntity(Order.class);
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
			Query q=getSession().createQuery("from Order where buyerId= :buyerId order by date DESC");
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
			SQLQuery q= getSession().createSQLQuery("select * from orderTable where buyer_id= :buyerId and date>date_sub(now(), interval 3 MONTH) order by date DESC"); 
			q.setParameter("buyerId", user.getId());
			//q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			q.addEntity(Order.class);
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
			SQLQuery q= getSession().createSQLQuery("select * from orderTable where buyer_id= :buyerId and date>date_sub(now(), interval 1 YEAR) order by date DESC"); 
			q.setParameter("buyerId", user.getId());
			//q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			q.addEntity(Order.class);
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
	
	public Order getOrderById(long id) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from Order where orderId= :orderId");
			q.setLong("orderId", id);
			Order order=(Order) q.uniqueResult();
			commit();
			return order;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get order by order id "+ e.getMessage());
		}
	}
	
	public void addQuantityOfOrderItem(int quantity, long orderId, long productId) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("update Item set quantity= :quantity where orderId= :orderId and productId= :productId");
			q.setInteger("quantity", quantity);
			q.setLong("orderId", orderId);
			q.setLong("productId", productId);
			q.executeUpdate();
			commit();
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not update quantity "+ e.getMessage());
		}
	}
	
	public int getItemQuantity(long orderId, long productId) throws shoppingSiteException{
		int quantity;
		try{
			begin();
			Query q=getSession().createQuery("select quantity from Item where orderId= :orderId and productId= :productId");
			q.setLong("orderId", orderId);
			q.setLong("productId", productId);
			quantity=(int) q.uniqueResult();
			commit();
			return quantity;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get quantity "+ e.getMessage());
		}
	}
	
}
