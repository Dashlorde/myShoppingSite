<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Product Form</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/userForm.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" ></script>

</head>
<body>
	<h2>Add a Product Here</h2>
	<div id="form">
	
	<form:form action="addProduct.htm" commandName="product" method="post" enctype="multipart/form-data" >
	<spring:htmlEscape defaultHtmlEscape="true" />
	<table >
		<tr>
			<td>Product Name:</td>
			<td><form:input path="productName" size="30" htmlEscape="true"/><font color="red"><form:errors path="productName"/></font> </td>
		</tr>
		
		<tr>
			<td>Product Price:</td>
			<td><form:input path="productPrice" size="30" htmlEscape="true"/><font color="red"><form:errors path="productPrice"/></font> </td>
		</tr>
		
		<tr>
			<td>Product Stock:</td>
			<td><form:input path="stock" size="30" htmlEscape="true"/><font color="red"><form:errors path="stock"/></font> </td>
		</tr>
		
		<tr>
			<td>Product Description:</td>
			<td><form:textarea path="description"  htmlEscape="true" id="exampleTextarea" rows="3"/><font color="red"><form:errors path="description"/></font> </td>
		</tr>
		
		<tr>
			<td>Product Category:</td>
			<td>
				<form:select path="category" id="select">
					<form:option value="electronics">electronics</form:option>
					<form:option value="computers">computers</form:option>
				</form:select>
			</td>
		</tr>
		
		<tr>
			<td>Product Image</td>
			<td><input type="file" name="image" size="50" id="file"/><font color="red"></font></td>
			
		</tr>
		
		
	</table>
	<input type="submit" value="Add Product" id="submit">
	</form:form>
	</div>

</body>
</html>