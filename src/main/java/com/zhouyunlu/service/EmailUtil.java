package com.zhouyunlu.service;

import java.util.List;
import java.util.Set;

import com.zhouyunlu.pojo.CartProduct;
import com.zhouyunlu.pojo.Item;
import com.zhouyunlu.pojo.Order;
import com.zhouyunlu.pojo.Product;

public interface EmailUtil {
	public abstract void sendEmail(String emailAddress, String username, Set<CartProduct> items);
	public abstract void sendEmailAsync(String emailAddress, String username, Set<CartProduct> items);
}
