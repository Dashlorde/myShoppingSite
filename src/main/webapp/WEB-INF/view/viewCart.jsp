<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view cart</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/contents.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
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
	<th>quantity</th>
	<th>price</th>
	<th></th>
</tr>
<%int i=0; %>
<c:forEach var="cproduct" items="${sessionScope.cart}">
	<tr>
		<td class="pic"><img src="${cproduct.product.imageName}" width="30%"/></td>
		<td>${cproduct.product.productName}</td>
		<td>${cproduct.quantity}
		<form action="changeQuantity.htm?id=${cproduct.product.productID}" method="post">
			<input type="number" name="quantity" min="1">
			<input type="submit" value="change" class="btn btn-default">
			<div style="color:red">${requestScope.quantityError} </div>
		</form> 
		</td>
		<td>${cproduct.product.productPrice}</td>
		<td><a href="deleteCart.htm?id=${cproduct.product.productID}&action=deleteCart">delete</a></td>
		
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