<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
 <link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/showProductInfo.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>
<style>
	.productInfo{
	position:absolute;
	top:55px;
}

#username{
	font-size: 12px;
}


</style>

</head>
<body>
<div class="container-fluid">
	<c:choose>
		<c:when test="${not empty user.name }">
			<jsp:include page="menu2.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="menu1.jsp" />
		</c:otherwise>
	</c:choose>
</div>

<div class="productInfo">
<div class="container">
	
	<ul class="info">
		<li ><img src="${requestScope.product.imageName}" width="40%"/></li>
		<li>${requestScope.product.productName}</li>
		<li id="username"> sell by ${requestScope.product.username}</li>
		<li>$${requestScope.product.productPrice}</li>		
		<li>${requestScope.product.description}</li>
		<li>
		<form action="addtocart.htm?id=${product.productID}" method="POST">
		add quantity<input type="number" name="quantity" min="1">
		<input type="submit" value="Add to Cart">
		</form>
		</li>
	</ul> 

</div>

<div class="container">
<h2> User Comments</h2>
<table class="table table-sm table-striped table-inverse">

<c:forEach var="comment" items="${requestScope.commentList}">
	<tr>
		<td>${comment.user.name}</td>
		<td>${comment.comment}</td>
		<td>${comment.commentTime}</td>
	<tr>
</c:forEach>
</table>

</div>
</div>
</body>
</html>