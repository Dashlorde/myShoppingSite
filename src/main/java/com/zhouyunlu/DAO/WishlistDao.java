package com.zhouyunlu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.WishList;

public class WishlistDao extends DAO{
	
	public WishList create(long userId, long productId) throws shoppingSiteException{
		try{
			begin();
			WishList wish=new WishList();
			wish.setUserId(userId);
			wish.setProductId(productId);
			getSession().save(wish);
			commit();
			return wish;
		}catch (HibernateException e) {
            rollback();
            throw new shoppingSiteException("Exception while creating wishlist: "+e.getMessage());
		}	
	}
	
	public void delete(WishList wishlist) throws shoppingSiteException{
		try{
			begin();
			getSession().delete(wishlist);
			commit();
			
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not delete wishlist: "+e.getMessage());
		}
	}
	
	public List<WishList> getWishlistByUser(long userId) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from WishList where userId= :userId");
			q.setLong("userId", userId);
			
			List<WishList> wishlist=q.list();
			commit();
			return wishlist;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get wishlist by userId: "+userId+" "+e.getMessage());
		}
	}
	
	public WishList getWishList(long userId, long productId) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from WishList where userId= :userId and productId= :productId");
			q.setLong("userId", userId);
			q.setLong("productId", productId);
			WishList wishlist=(WishList)q.uniqueResult();
			commit();
			return wishlist;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get wishlist: "+e.getMessage());
		}
	}
	
	public List<Long> getSomeProducts(long id) throws shoppingSiteException{
		
		try{
			begin();
			Query q=getSession().createQuery("select productId from WishList where userId= :userId");
			q.setMaxResults(3);
			q.setLong("userId", id);
			List<Long> productlist=q.list();
			commit();
			return productlist;
			
		}catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list wishlist: "+ e.getMessage());
		}
	}

}
