package com.zhouyunlu.controller;

import org.apache.commons.fileupload.FileUpload;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.zhouyunlu.pojo.Product;

@Component
public class productValidator implements Validator{
	
	public boolean supports(Class aClass){
		return aClass.equals(Product.class);
	}
	
	public void validate(Object obj, Errors errors){
		Product product=(Product) obj;
		
		
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "error.invalid.productName", "product name Required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productPrice", "error.invalid.productPrice", "product price Required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.invalid.description", "product description Required");
	        //ValidationUtils.rejectIfEmpty(errors, "imageName", "error.invalid.image","image needed");
		
		
        
	}

}
