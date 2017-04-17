<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order History</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
<!--  link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" /-->
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>

<style type="text/css">

body {
	background-color: #edeff2
}
.order{	
	margin-top:60px;
	background-color:white;
	padding:20px;
}

td.pic{
	text-align:center;
	width:35%;
}

th{font-size:18px}

@media only screen and (max-width:768px){
.colhidden{display:none}

}


</style>
</head>
<body>
	<jsp:include page="menu2.jsp" />
	<main>
	<div class="container order table-responsive">
	
	<div class="sort">
		<form action="buyerSortOrder.htm" method="get">
		Sorted Order BY:
			<select name="sort">
				<option value="all">all</option>
				<option value="threeMonths">3 months</option>
				<option value="oneYear">1 year</option>
				
			</select> <input type="submit" value="sort" class="btn btn-primary btn-sm">
		</form>
	</div>
	<table class="table table-striped">
		<tr>
			<th>order id</th>
			<th class="colhidden">email</th>
			<th>date</th>
			<th>price</th>
			<th>status</th>
			<th></th>
		</tr>
		<c:forEach var="order" items="${requestScope.orderList}">
			<tr>
				<td>${order.orderId}</td>
				<td class="colhidden">${order.email}</td>
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