<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Buyers' Orders</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
<!-- link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" /-->
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js" >
</script>
<style>
body { background-color: #edeff2 }
.order{	
	margin-top:60px;
	background-color:white;
	padding:20px;
	}
table{
	border-collapse: collapse;
   	z-index: -1;
   	width:100%
}

th, td{
	height:60px;
	padding: 10px;
	text-align:center;
	border-bottom: 1px solid  #d7d9dd;
	font-family:Lucida Grande;
}


th{font-size:18px}

</style>
</head>
<body>
	<jsp:include page="menu2.jsp" />
	
	<main>
	<div class="container order table-responsive">
	<div class="sort">
		<form action="sellerSortOrder.htm" method="get">
		Sorted Order BY:
			<select name="sort" >
				<option value="all">all</option>
				<option value="threeMonths">3 months</option>
				<option value="oneYear">1 year</option>
			</select> <input type="submit" value="sort" class="btn btn-primary btn-sm">
		</form>
	</div>
	
	
	<table class="table">
		<tr>
			<th>order id</th>
			<th>address</th>
			<th>email</th>
			<th>name</th>
			<th>phone</th>
			<th>date</th>
			<th>price</th>
			<th>status</th>
			<th></th>
		</tr>
		<c:forEach var="order" items="${requestScope.orderList}">
			<tr>
				<td>${order.orderId}</td>
				<td>${order.address}</td>
				<td>${order.email}</td>
				<td>${order.firstName}${order.lastName}</td>
				<td>${order.phone}</td>
				<td>${order.date}</td>
				<td>$${order.price}</td>
				<td>${order.status}</td>
				<td><a class="btn btn-primary" href="orderDetail.htm?id=${order.orderId}">order
						detail</a></td>

			</tr>
		</c:forEach>
	</table>
	</div>
	</main>
	
	<footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br> <br>
		<p>&copy;Yunlu Zhou</p>
</footer>	
</body>
</html>