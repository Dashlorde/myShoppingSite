package com.zhouyunlu.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Address;

public class AddressDao extends DAO{

	public Address create(long userId, String phone, String address) throws shoppingSiteException{
		try{
			begin();
			Address uAddress=new Address();
			uAddress.setAddress(address);
			uAddress.setId(userId);
			uAddress.setPhone(phone);
			getSession().save(uAddress);
			commit();
			
			return uAddress;
		}catch (HibernateException e) {
            rollback();
            throw new shoppingSiteException("Exception while creating address: "+e.getMessage());
		}	
	}
	
	public void editAddress(String address, String phone, Address uAddress) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("update Address set address= :address, phone= :phone where id= :userId");
			q.setString("address", address);
			q.setString("phone", phone);
			q.setLong("userId", uAddress.getId());
			q.executeUpdate();
			commit();
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not update user address"+ e.getMessage());
		}
	}
	
	public Address getByUserId(long userId) throws shoppingSiteException{
		try{
			begin();
			Query q=getSession().createQuery("from Address where id= :userId");
			q.setLong("userId", userId);
			Address address=(Address)q.uniqueResult();
			commit();
			return address;
		} catch(HibernateException e){
			rollback();
			throw new shoppingSiteException("could not get address by userId: "+userId+" "+e.getMessage());
		}
	}
}