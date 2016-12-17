<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
</head>
<body>
	<jsp:include page="menu2.jsp" />

	<table>
		<tr>
			<th>order id</th>
			<th>address</th>
			<th>email</th>
			<th>name</th>
			<th>phone</th>
			<th>date</th>
			<th>price</th>
			<th></th>
		</tr>
		<c:forEach var="order" items="${requestScope.orderList}">
			<tr>
				<td>${order.orderId}</td>
				<td>${order.address}</td>
				<td>${order.email}</td>
				<td>${order.firstName} ${order.lastName}</td>
				<td>${order.phone}</td>
				<td>${order.date}</td>
				<td>$${order.price}</td>
				<td><a href="orderDetail.htm?id=${order.orderId}">order detail</a></td>
				
			</tr>
		</c:forEach>
	</table>
</body>
</html>