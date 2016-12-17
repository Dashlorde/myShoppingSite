package com.zhouyunlu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhouyunlu.DAO.AddressDao;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Address;
import com.zhouyunlu.pojo.User;

@Controller
public class EditAddressController {
	
	@Autowired
	AddressDao addressDao=new AddressDao();

	@RequestMapping(value="/editAddress.htm", method=RequestMethod.POST)
	protected String editAddress(HttpServletRequest request) throws shoppingSiteException{
		HttpSession session=request.getSession();
		
		User user=(User) session.getAttribute("user");
		
		String address=request.getParameter("address").toString();
		String phone=request.getParameter("phone").toString();
		
		if(addressDao.getByUserId(user.getId())!=null){
			//Address uAddress=user.getAdddress();
			Address uAddress=addressDao.getByUserId(user.getId());
			addressDao.editAddress(address, phone, uAddress);
		}
		
		else{
			long userId=user.getId();
			addressDao.create(userId, phone, address);
		}
		
		return "redirect:/checkout.htm?action=checkout";
	}
	
	@RequestMapping(value="/editAddress.htm", method=RequestMethod.GET)
	protected String getEditAddressPage(HttpServletRequest request){
		return "editAddress";
	}
}
