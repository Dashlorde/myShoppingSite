package com.zhouyunlu.service;

import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.Product;

@Component("emailUtil")
public class EmailUtilImpl implements EmailUtil {
	
	 private Executor executor = Executors.newFixedThreadPool(10);  

	public void sendEmail(String emailAddress, String username,Set<CartProduct> items) {
		Properties prop = new Properties();
		Session session = null;
		Message message = null;
		Transport transport = null;
		float total=0;
		StringBuilder b = new StringBuilder();
		for (CartProduct item : items) {
			b.append("product name:  " + item.getProduct().getProductName() + "   product price:  " + item.getProduct().getProductPrice()+"   quantity:  "+item.getQuantity())
					.append("<br/>");
			total+=item.getQuantity()*item.getProduct().getProductPrice();
		}

		String itemString = b.toString();

		try {
			prop.setProperty("mail.transport.protocol", "smtp");
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.port", "587");
			prop.put("mail.smtp.starttls.enable", "true");
			
			session = Session.getDefaultInstance(prop);
			
			message = new MimeMessage(session);
			message.setSubject("Order Information");

			message.setContent("Welcome " + username+" <br/><br/>" + " Your order is completed!<br/><br/>"
					+"total amount:  "+total+"<br/>"+ "order detail: <br/>" + itemString, "text/html;charset=utf-8");
			message.setFrom(new InternetAddress("myshoppingsite.test@gmail.com"));
			transport = session.getTransport();
			transport.connect("smtp.gmail.com", "myshoppingsite.test@gmail.com", "myshoppingsite");

			transport.sendMessage(message, new InternetAddress[] { new InternetAddress(emailAddress) });

			transport.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}
	
	public void sendEmailAsync(final String emailAddress, final String username,final Set<CartProduct> items){
		Runnable task=new Runnable(){

			@Override
			public void run() {
				try{
					sendEmail(emailAddress, username, items);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		executor.execute(task);
	}
}
