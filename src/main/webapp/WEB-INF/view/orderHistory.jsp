<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>

</head>
<body>
	<jsp:include page="menu2.jsp" />
	<div class="sort">
		<form action="buyerSortOrder.htm" method="get">
		Sorted Order BY:
			<select name="sort">
				<option value="all">view all</option>
				<option value="threeMonths">3 months</option>
				<option value="oneYear">1 year</option>
				
			</select> <input type="submit" value="sort">
		</form>
	</div>
	<table>
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
</body>
</html>