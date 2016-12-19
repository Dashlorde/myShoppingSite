package com.zhouyunlu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;


public class ProductDao extends DAO{
	
	public Product create(  String productName, float productPrice,String description, String category, String username, int stock, String imageName) throws shoppingSiteException{
		try{
			begin();
			Product product=new Product();
			
			product.setCategory(category);
			product.setDescription(description);
			
			product.setProductName(productName);
			product.setProductPrice(productPrice);
			product.setUsername(username);
			product.setImageName(imageName);
			product.setStock(stock);
			
			System.out.println(product.getImageName());
			getSession().save(product);
			
			commit();
			return product;
		} catch (HibernateException e) {
            rollback();
            throw new shoppingSiteException("Exception while creating product: "+e.getMessage());
		}	
	}
	
	
	
	public void delete(Product product) throws shoppingSiteException{
		try{
			begin();
			getSession().delete(product);
			commit();
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not delete product: "+e.getMessage());
		}
	}
	
	public void modifyDescription(String description, Product product) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("update Product set description= :description where productID= :productID");
			q.setString("description", description);
			q.setLong("productID", product.getProductID());
			q.executeUpdate();
			commit();
		}catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not update product description"+ e.getMessage());
		}
	}
	
	public void modifyStock(int stock, Product product) throws shoppingSiteException{
		try{
			
			begin();
			Query q=getSession().createQuery("update Product set stock= :stock where productID= :productID");
			q.setInteger("stock", stock);
			q.setLong("productID", product.getProductID());
			q.executeUpdate();
			commit();
		}catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not update product stock"+ e.getMessage());
		}
	}
	
	public void modifyImage(String imageName, Product product) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("update Product set imageName= :imageName where productID= :productID");
			q.setString("imageName", imageName);
			q.setLong("productID", product.getProductID());
			q.executeUpdate();
			commit();
		}catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not update product description"+ e.getMessage());
		}
	}
	
	public List<Product> getAllProducts(User user) throws shoppingSiteException{
		List<Product> productList=null;
		try{
			begin();
			Query q=getSession().createQuery("from Product where username<> :username");
			q.setString("username", user.getName());
			productList=q.list();
			commit();
			return productList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the product"+ e.getMessage());
		}
	}
	
	public List<Product> getAllProducts() throws shoppingSiteException{
		List<Product> productList=null;
		try{
			begin();
			Query q=getSession().createQuery("from Product ");
			
			productList=q.list();
			commit();
			return productList;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not list all the product"+ e.getMessage());
		}
	}
	
	public List<Product> getProductByCategory(String category, User user) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from Product where category= :category and username<> :username");
			q.setString("category", category);
			q.setString("username", user.getName());
			List<Product> list=q.list();
			commit();
			return list;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get product by category"+category+" "+e.getMessage());
		}
	}
	
	public List<Product> getProductByCategory(String category) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from Product where category= :category ");
			q.setString("category", category);
			
			List<Product> list=q.list();
			commit();
			return list;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get product by category"+category+" "+e.getMessage());
		}
	}
	
	public List<Product> getProductByName(String username) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from Product where username= :username ");
			q.setString("username", username);
			List<Product> list=q.list();
			commit();
			return list;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get product by username"+username+" "+e.getMessage());
		}
	}
	
	public Product getProductByID(long productID) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from Product where productID= :productID");
			q.setLong("productID", productID);
			
			Product product=(Product)q.uniqueResult();
			commit();
			return product;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get product by productID: "+productID+" "+e.getMessage());
		}
	}
	
	public List<Product> getProductByProductName(String productName) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from Product where productName= :productName");
			q.setString("productName", productName);
			
			List<Product> list=q.list();
			commit();
			return list;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get product by productName: "+productName+" "+e.getMessage());
		}
	}
	
	public void changeStock(int quantity) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("update Product set quantity= :quantity");
			q.setInteger("quantity", quantity);
			q.executeUpdate();
			commit();
		}catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not update product stock "+ e.getMessage());
		}
	}
}
