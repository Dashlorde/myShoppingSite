package com.zhouyunlu.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Email;
import com.zhouyunlu.pojo.User;




public class UserDAO extends DAO{
	ResultSet rs;
	PreparedStatement ps;
	
	public UserDAO(){
		
	}
	
	public User create(String username, String password,String emailId, String firstName, String lastName)
            throws shoppingSiteException {
        try {
            begin();
            System.out.println("inside DAO");
            
            Email email=new Email(emailId);
            User user=new User(username,password);
            
            user.setFirstName(firstName);
            user.setLastName(lastName);
            
            user.setEmail(email);
            
            email.setUser(user);
            
            getSession().save(user);
            
            commit();
            return user;
        } catch (HibernateException e) {
            rollback();
            
            throw new shoppingSiteException("Exception while creating user: " + e.getMessage());
        }
        
        
    }
	
	public User get(String username) throws HibernateException{
		
			begin();
			Query q=getSession().createQuery("from User where name= :username");
			q.setString("username", username);
			User user=(User) q.uniqueResult();
			commit();
			return user;
		
	}
	
	public boolean login(String name, String password) {
		if(name==null){
			name="";
		}
		
		if(password==null){
			password="";
		}
		
		List<User> list=null;
		String query="select user.name, user.password from User as user where name='"+name+"'and password='"+password+"'";
		
		try{
			begin();
			Query q=getSession().createQuery(query);
			list=q.list();
			commit();
		} catch(DataAccessException e){
			return false;
		}
		
		if(list.isEmpty()){
			return false;
		}
		
		return true;
	}
	

	
}
