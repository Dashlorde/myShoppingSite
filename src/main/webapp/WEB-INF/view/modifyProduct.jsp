<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modify product</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>

<style>

.container{margin-top:80px; background-color:#f9f9e0}

ul.info{
	text-decoration:none;
	width: 100%
}

li.info{
	text-align:left;
	font-family:Lucida Grande;
	text-decoration:none;
	list-style-type: none;
	padding:10px 30px;
	font-size:20px;
}

li.info a{
	color: black;
	display:block;
	border-radius: 8px;
	padding: 10px, 15px;
	border: 2px solid black;
	text-decoration: none;
	margin-left:35%;
	margin-right:35%;
	font-size:15px;

}

li.info a:hover{
	background-color: black;
	color:white;
}

#submit{
	diplay:block;
	width: 30%;
	font-size: 15px;
	font-family:Lucida Grande;
	padding:10px;
	background-color :black;
	color: white;
	border-radius:8px;
	list-style-type:none;
	
}

#textarea{
	width: 50%;
	height: 50px;

}


</style>

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

<main>
<div class="container info">
	<form:form action="modifyProduct.htm?id=${sessionScope.product.productID}&action=doModify" commandName="product" method="post" enctype="multipart/form-data" >
	<ul class="info">
		<li class="info"><img src="${sessionScope.product.imageName}" width="40%"/><input type="file" name="image" size="50" id="file"/>
		
		<li class="info"><font color="#871262">product name:</font> ${sessionScope.product.productName}</li>
		<li class="info" ><font color="#871262">product stock:</font> ${sessionScope.product.stock}
		<form:input path="stock" name="stock" htmlEscape="true"/><font color="red"><form:errors path="stock"/></font></li>
		<li class="info" id="textarea"><font color="#871262">product description:</font></li>
		<li class="info"><form:textarea path="description" name="description" size="30" id="exampleTextarea" rows="3" htmlEscape="true"/><font color="red"><form:errors path="description"/></font></li>
		<li class="info"><input type="submit" value="Modify Product" id="submit"></li>
		
	</ul>
	
	</form:form>	
</div>
</main>

<footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br> <br>
		<p>&copy;Yunlu Zhou</p>
</footer>	
</body>


</html>