<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>check out</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.min.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>
<style>
.address{
	position: absolute;
	top: 55px;
}
</style>
</head>
<body>
<jsp:include page="menu2.jsp"/>

<div class="address">
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
 	<a href="placeOrder.htm" class="btn btn-default">Place Order</a>
 </div>
 </div>
</body>
</html>