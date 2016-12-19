package com.zhouyunlu.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zhouyunlu.DAO.CommentDao;
import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.Exception.shoppingSiteException;
import com.zhouyunlu.pojo.Product;
import com.zhouyunlu.pojo.User;

@Controller
public class ManageCommentController {

	@Autowired 
	CommentDao commentDao=new CommentDao();
	
	@Autowired
	ProductDao productDao=new ProductDao();
	
	@RequestMapping(value="/buyerComment.htm", method=RequestMethod.GET)
	protected String getCommentPage(HttpServletRequest request) throws shoppingSiteException{
		HttpSession session=request.getSession();
		String idString=request.getParameter("id").toString();
		long id=Long.parseLong(idString);
		session.setAttribute("id", id);
		
		return "addCommentForm";
	}
	
	@RequestMapping(value="/buyerComment.htm", method=RequestMethod.POST)
	protected String addComment(HttpServletRequest request) throws shoppingSiteException{
		HttpSession session=request.getSession();
		long id=(long) session.getAttribute("id");
		Product product=productDao.getProductByID(id);
		
		User user=(User) session.getAttribute("user");
		String comment=request.getParameter("comment");
		Date commentTime=new Date();
		commentDao.create(user, product, comment, commentTime);
		
		return "redirect:/orderHistory.htm";
	
	}
}