package com.zhouyunlu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhouyunlu.DAO.CommentDao;
import com.zhouyunlu.DAO.ProductDao;
import com.zhouyunlu.pojo.Comment;
import com.zhouyunlu.pojo.Product;

@Controller
@RequestMapping("/getComment.htm")
public class GetCommentController {
	
	@Autowired
	CommentDao commentDao=new CommentDao();
	
	@Autowired
	ProductDao productDao=new ProductDao();
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Comment> getComment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String i=request.getParameter("id").toString();
		long id=Long.parseLong(i);
		
		Product product=productDao.getProductByID(id);
		List<Comment> commentList=commentDao.getCommentByProduct(product);
		
		return commentList;
	}

}
