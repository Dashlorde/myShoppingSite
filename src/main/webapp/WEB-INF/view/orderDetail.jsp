<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome to my shopping website</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />

</head>
<body>


	<jsp:include page="menu2.jsp" />


	<table>
		<tr>
			<th>Picture</th>
			<th>Name</th>
			<th>Description</th>
			<th>Price</th>
		</tr>
		<c:forEach var="product" items="${requestScope.productList}">
			<tr>
				<td class="pic"><img src="${product.imageName}" width="30%" /></td>
				<td>${product.productName}</td>
				<td>${product.description}</td>
				<td>$${product.productPrice}</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>