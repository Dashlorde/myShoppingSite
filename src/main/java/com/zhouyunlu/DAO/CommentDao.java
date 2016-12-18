package com.zhouyunlu.DAO;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import org.hibernate.HibernateException;

import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Comment;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

public class CommentDao extends DAO{

	public Comment create(User user, Product product, String comment, Date commentTime) throws shoppingSiteException{
		try{
			begin();
			Comment c=new Comment();
			c.setComment(comment);
			c.setCommentTime(commentTime);
			c.setProduct(product);
			c.setUser(user);
			getSession().save(c);
			commit();
			return c;
		} catch (HibernateException e) {
            rollback();
            throw new shoppingSiteException("Exception while creating comment: "+e.getMessage());
		}	
	}
	
	public List<Comment> getCommentByProduct(Product product) throws shoppingSiteException{
		List<Comment> list=null;
		try{
			begin();
			Query q=getSession().createQuery("from Comment where product= :product");
			q.setParameter("product", product);
			list=q.list();
			commit();
			return list;
			
		} catch (HibernateException e) {
            rollback();
            throw new shoppingSiteException("Exception while get comment by product: "+e.getMessage());
		}	
	}
}
