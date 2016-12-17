<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>check out</title>
</head>
<body>
 <div>
 	<ul>
 		<li>Name: ${sessionScope.user.firstName} ${sessionScope.user.lastName}</li>
 		<li>Email: ${requestScope.email.emailId}</li>
 		<li>Phone: ${requestScope.address.phone}</li>
 		<li>Address: ${requestScope.address.address}</li>
 		<li><a href="editAddress.htm">edit address</a></li>
 	</ul>
 </div>
 
 <div>
 	<a href="placeOrder.htm">Place Order</a>
 </div>
</body>
</html>