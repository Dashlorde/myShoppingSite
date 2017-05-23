package com.zhouyunlu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	protected String editAddress(HttpServletRequest request,HttpServletResponse response, @ModelAttribute("userAddress")@Valid Address addr, BindingResult result, Model model) 
			throws shoppingSiteException{
		HttpSession session=request.getSession();
		
		User user=(User) session.getAttribute("user");
		
		String address=addr.getAddress();
		String phone=addr.getPhone();
		String pcode=addr.getPcode();
		String country=addr.getCountry();
		String state=addr.getState();
		String city=addr.getCity();
		
		if(result.hasErrors()){
			System.out.println("error here");
			return "editAddress";		
		}
		
		if(addressDao.getByUserId(user.getId())!=null){
			Address uAddress=addressDao.getByUserId(user.getId());
			addressDao.editAddress(address, phone, uAddress, city, state, country, pcode);
		}
		
		else{
			long userId=user.getId();
			addressDao.create(userId, phone, address, city, state, country, pcode);
		}
		
		if(session.getAttribute("currentURL")!=null){
			String currentURL=(String) session.getAttribute("currentURL");
			return currentURL;
		}else{
			return "redirect:/checkout.htm?action=checkout";
			
		}
		
	}
	
	@RequestMapping(value="/editAddress.htm", method=RequestMethod.GET)
	protected String getEditAddressPage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("userAddress")Address address, BindingResult result){
		return "editAddress";
	}
}
