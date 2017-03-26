<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Detail</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>

</head>
<body>


	<jsp:include page="menu2.jsp" />


	<table class="productTable">
		<tr>
			<th>Picture</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Quantity</th>
			<th></th>
		</tr>
		<c:forEach var="product" items="${requestScope.productList}">
			<tr>
				<td class="pic"><img src="${product.key.imageName}" width="30%" /></td>
				<td>${product.key.productName}</td>
				<td>${product.key.description}</td>
				<td>$${product.key.productPrice}</td>
				<td>${product.value}</td>
				<td><c:choose>
					<c:when test="${sessionScope.user.id==requestScope.buyerId}">
					<a class="btn btn-primary" href="buyerComment.htm?id=${product.key.productID}">comment</a>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>

</body>
</html>