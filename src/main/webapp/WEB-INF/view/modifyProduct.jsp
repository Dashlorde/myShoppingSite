<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modify product</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/modifyProduct.css" />
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

<div class="info">
	<form:form action="modifyProduct.htm?id=${sessionScope.product.productID}&action=doModify" commandName="product" method="post" enctype="multipart/form-data" >
	<ul class="info">
		<li class="info"><img src="${sessionScope.product.imageName}" width="40%"/><input type="file" name="image" size="50" id="file"/>
		
		<li class="info"><font color="#871262">product name:</font> ${sessionScope.product.productName}</li>
		<li class="info" ><font color="#871262">product stock:</font> ${sessionScope.product.stock}
		<form:input path="stock" name="stock" htmlEscape="true"/><font color="red"><form:errors path="stock"/></font></li>
		<li class="info" id="textarea"><font color="#871262">product description:</font> ${sessionScope.product.description}</li>
		<li class="info"><form:input path="description" name="description" size="30" id="textarea" htmlEscape="true"/><font color="red"><form:errors path="description"/></font></li>
		<li class="info"><input type="submit" value="Modify Product" id="submit"></li>
		
	</ul>
	
	</form:form>	
</div>

</body>
</html>