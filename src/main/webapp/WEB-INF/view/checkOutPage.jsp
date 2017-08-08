<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>check out</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js" >
</script>
<style>
.address{
	margin-top:80px;
	width:60%
}
</style>
</head>
<body>

<jsp:include page="menu2.jsp"/>
<main>
<div class="container address">
<div class="panel panel-default">
<div class="panel-heading">
		<h3 class="panel-title">
			User Address
		</h3>
	</div>
 <div  class="panel-body">
 	<ul class="list-group">
 		<li class="list-group-item">Name: ${sessionScope.user.firstName} ${sessionScope.user.lastName}</li>
 		<li class="list-group-item">Email: ${requestScope.email.emailId}</li>
 		<li class="list-group-item">Phone: ${requestScope.address.phone}</li>
 		<li class="list-group-item">Address: ${requestScope.address.address}</li>
 		<li class="list-group-item"><a href="editAddress.htm">edit address</a></li>
 	</ul>
 </div>
 </div>
 
 <div>
 	<a href="placeOrder.htm" data-paypal-button="true" class="btn btn-default"><input type="image" src="https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif" alt="Check out with PayPal"/></a>
 	
 </div>
 </div>
 </main>
 <footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a><br> <br>
		<p>&copy;Yunlu Zhou</p>
	</footer>
</body>
</html>