<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/showProductInfo.css" />
 <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
 <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
</head>
<body>

	<c:choose>
		<c:when test="${not empty user.name }">
			<jsp:include page="menu2.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="menu1.jsp" />
		</c:otherwise>
	</c:choose>

<div class="panel">
	
	<ul class="info">
		<li ><img src="${requestScope.product.imageName}" width="40%"/></li>
		<li class="brief">${requestScope.product.productName}</li>
		<li class="brief" id="username"> sell by ${requestScope.product.username}</li>
		<li>$${requestScope.product.productPrice}</li>		
		<li>${requestScope.product.description}</li>
		<li><a href="addtocart.htm?id=${product.productID}&action=addtocart" >Add to Cart</a>
	</ul> 
</div>

</body>
</html>