<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>view cart</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<script
	src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js">
	
</script>
<!-- link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/contents.css" /-->
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
	
<style type="text/css">
body {
	background-color: #edeff2
}
.container{
	background-color:white;
	margin-top:80px;
}
table{
	border-collapse: collapse;
    width: 100%;
   	z-index: -1;
}

th, td{
	height:60px;
	padding: 10px;
	text-align:center;
	border-bottom: 1px solid  #d7d9dd;
	font-family:Lucida Grande;
}

td.pic{
	text-align:center;
	width:40%;
}

h2.cartHead{
	position:absolute;
	top:55px;
	font-family:Lucida Grande;
}

div.checkout{
	position:fixed;
	bottom:20px;
	
}

a.checkout{
	font-family:Lucida Grande;
	text-decoration:none;
	display:block;
	font-size:16px;
	padding: 10px 20px;
	border: 1px solid #4CAF50;
	border-radius: 8px;
	
	
}

div.total{
	position:fixed;
	bottom:80px;
}

#total{
	font-size: 20px;
	font-family:Lucida Grande;
	
}
@media only screen and (max-width:768px){
.colhidden{display:none}

}
	
	</style>
</head>
<body>
	<c:choose>
		<c:when test="${not empty sessionScope.username }">
			<jsp:include page="menu2.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="menu1.jsp" />
		</c:otherwise>
	</c:choose>
	<main> 
	<div class="container table-responsive">
	<c:choose>
		<c:when test="${!empty sessionScope.cart}">

			<table>

				<%
					int i = 0;
				%>
				<c:forEach var="cproduct" items="${sessionScope.cart}">
					<tr>
						<td class="pic colhidden"><a href="showProductInfo.htm?id=${cproduct.product.productID }&action=showProductInfo"><img src="${cproduct.product.imageName}"
							width="30%" /></a></td>
						<td>${cproduct.product.productName}</td>
						<td>${cproduct.quantity}
							<form action="changeQuantity.htm?id=${cproduct.product.productID}" method="post">
								<input type="number" name="quantity" min="1"> 
								<input type="submit" value="change" class="btn btn-default">
								<div style="color: red">${requestScope.quantityError}</div>
							</form>
						</td>
						<td>$${cproduct.product.productPrice}</td>
						<td><a
							href="deleteCart.htm?id=${cproduct.product.productID}&action=deleteCart"><button class="btn btn-danger">
									<span class="glyphicon glyphicon-trash"></span>
								</button></a></td>

					</tr>
				</c:forEach>
			</table>
			<div class="total">
				<p id="total">Total ${sessionScope.total}</p>
			</div>
		</c:when>

		<c:otherwise>
			<h2 class="cartHead">Your cart is empty</h2>
		</c:otherwise>
	</c:choose>






	<div class="checkout">
		<a href="checkout.htm?action=checkout" class="checkout"> Checkout</a>
	</div>
</div>
</main>

</body>
</html>