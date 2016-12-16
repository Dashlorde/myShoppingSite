<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view cart</title>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/contents.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
</head>
<body>
 <c:choose>
		<c:when test="${not empty sessionScope.username }">
			<jsp:include page="menu2.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="menu1.jsp" />
		</c:otherwise>
	</c:choose>
	
<c:choose>
	<c:when test="${!empty sessionScope.cart}">
	
<table>
<tr>
	<th>Picture</th>
	<th>product name</th>
	<th>price</th>
	<th></th>
</tr>
<%int i=0; %>
<c:forEach var="product" items="${sessionScope.cart}">
	<tr>
		<td class="pic"><img src="${product.imageName}" width="30%"/></td>
		<td>${product.productName}</td>
		<td>${product.productPrice}</td>
		<td><a href="deleteCart.htm?id=${product.productID}&action=deleteCart">delete</a></td>
		
	</tr>
</c:forEach>
</table>
<div class="total"><p id="total">Total   ${sessionScope.total}</p></div>	
 </c:when>
	
	<c:otherwise>
		<h2 class="cartHead">Your cart is empty</h2>
	</c:otherwise>
</c:choose>






<div class="checkout"><a href="checkout.htm?action=checkout" class="checkout"> Checkout</a></div>
</body>
</html>