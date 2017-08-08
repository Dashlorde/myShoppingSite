<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>my wishlist</title>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>

<style>
body {
	background-color: #edeff2
}
.container{
	background-color:white;
	margin-top:80px;
	padding-top:40px;
}

.wish{
	color: #b24276;
}

th, td{
	height:60px;	
	text-align:center;
	font-family:Lucida Grande;
	font-size: 18px;
}

.pic{
	width: 300px;
}

td a:hover {
	color: #b24276;
	text-decoration: none;
}

td a{
	color:black
}

@media only screen and (max-width:768px){
.colhidden{display:none}

}
</style>
</head>
<body>
<jsp:include page="menu2.jsp" />
<main>
<div class="container table-responsive">
<div class="row">
<div class="col-md-10 col-md-offset-1">
	<table class="table">
		<c:forEach var="product" items="${requestScope.productList}">
			<tr>
			
				<td class="pic colhidden"><a href="showProductInfo.htm?id=${product.productID }&action=showProductInfo">
				<img src="${product.imageName}" width="200px" /></a></td>
				
				<td><a href="showProductInfo.htm?id=${product.productID }&action=showProductInfo">
				${product.productName}</a><br>
				$${product.productPrice}</td>
				<td>
					<form class="form-inline" action="addtocart.htm?id=${product.productID}" method="POST">
							<input type="number" name="quantity" min="1">
							
							<button type="submit" value="Add to Cart" class="btn btn-default"><span class="glyphicon glyphicon-shopping-cart"></span></button>
							<div class="input-group" style="color: red">${requestScope.quantityError}</div>
					</form>
				</td>
				<td>
					<a type="button" class="btn btn-default" href="removeFromWish.htm?id=${product.productID}"><span class="glyphicon glyphicon-heart wish"></span></a>
				</td>
			</tr>
			
		</c:forEach>
	</table>
	</div>
	</div>
</div>
</main>
</body>
</html>