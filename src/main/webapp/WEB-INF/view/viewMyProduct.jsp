<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Product</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
</head>
<body>

	<c:choose>
		<c:when test="${not empty user.name }">
			<jsp:include page="menu2.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="menu1.jsp" />
		</c:otherwise>
	</c:choose>
<main>

	<table>
		<tr>
			<th>Picture</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
			<th>Stock</th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach var="product" items="${requestScope.productList}">
			<tr>
				<td class="pic"><img src="${product.imageName}" width="30%" /></td>
				<td>${product.productName}</td>
				<td>${product.description}</td>
				<td>$${product.productPrice}</td>
				<td>${product.stock}</td>
				<td><a
					href="modify.htm?id=${product.productID }&action=goModifyPage"><button class="btn btn-primary">modify</button></a></td>
				<td><a
					href="deleteProduct.htm?id=${product.productID}&action=deleteProduct"><button
									class="btn btn-danger">
									<span class="glyphicon glyphicon-trash"></span>
								</button></a></td>
			</tr>
		</c:forEach>
	</table>

	</main>
	

</body>
</html>