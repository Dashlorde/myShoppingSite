<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Product</title>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js" >
</script>
<!--link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" /-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
	
<style>
body {
	background-color: #edeff2
}
.container{
	background-color:white;
	margin-top:80px;
}
table{
	border-collapse: collapse;
   	z-index: -1;
   	width:100%;
}

th, td{
	height:60px;
	padding: 10px;
	text-align:center;
	border-bottom: 1px solid  #d7d9dd;
	font-family:Lucida Grande;
}

@media only screen and (max-width:768px){
.colhidden{display:none}

}


th{font-size:18px}
</style>
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
<div class="container table-responsive">
	<table class="table">
		<tr>
			<th class="colhidden">Picture</th>
			<th>Name</th>
			<th class="colhidden">Description</th>
			<th>Price</th>
			<th>Stock</th>
			<th></th>
			<th><a href="addProduct.htm" class="btn btn-danger"><span class="glyphicon glyphicon-plus"></span> new</a></th>
		</tr>
		<c:forEach var="product" items="${requestScope.productList}">
			<tr>
				<td class="pic colhidden"><img src="${product.imageName}" width="200px" /></td>
				<td>${product.productName}</td>
				<td class="colhidden">${product.description}</td>
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
</div>
</main>
	
<footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br> <br>
		<p>&copy;Yunlu Zhou</p>
</footer>	
</body>
</html>