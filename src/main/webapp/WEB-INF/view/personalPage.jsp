<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>My Account</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Barrio">
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js"></script>

<style>
body { background-color: #edeff2;}
#container{
	margin-top: 80px;
	background-color: white;
	padding: 20px;
	font-family:Lucida Grande;
	box-shadow: 3px 3px 3px #d7d9dd;
}
.upright{
	  transition: .3s ease;
	  opacity: 0;
	  position: absolute;
	  top: 10%;
	  right: 5%;
	  transform: translate(-50%, -50%);
	  -ms-transform: translate(-50%, -50%)
}
.image{
	backface-visibility: hidden;
}
.thumbnail {
	padding: 10px;
	border-radius: 10px;
	border: 1px solid darkgrey;
	
}

.thumbnail p {
	margin-top: 15px;
	color: #555;
}

.thumbnail a:hover {
	color: #b24276;
	text-decoration: none;
}

.thumbnail a{
	color: black;
}

.thumbnail:hover .image {
  opacity: 0.7;
}

.thumbnail:hover .upright{
	opacity: 1;
}

.title{		
  	font-family: Barrio;
  	margin-bottom: 20px;
}  

.wish{
	color:#b24276;
}

.div-divider{
	border-bottom: 1px solid darkgrey;	
	padding-bottom: 30px;
	padding-top: 30px;
	width:80%
}

.more{
		float: right;
}

.more a{
	color: #cc5751;
	font-family: Barrio;
}

.more a:hover {
     color: #777;
     text-decoration: none;
 	}  
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

<div class="container" id="container">

<div class="container text-center div-divider">
<h2 class="title">WISHLIST</h2>
<div class="row">
			<c:forEach var="product" items="${requestScope.productlist}">
				<div class="col-sm-offset-0 col-sm-6 col-md-4">

					<div class="thumbnail">
						<a href="showProductInfo.htm?id=${product.productID }&action=showProductInfo"><img src="${product.imageName}" class="image"/></a>
						<c:choose>
							<c:when test="${not empty user.name }">
							<c:choose>
								<c:when test="${requestScope.wish.contains(product.productID)}">
									<div class="upright">
										<a type="button" class="btn btn-default" href="removeFromWish.htm?id=${product.productID}"><span class="glyphicon glyphicon-heart wish"></span></a>
									</div>
								</c:when>
								<c:otherwise>
									<div class="upright">
										<a type="button" class="btn btn-default" href="addToWish.htm?id=${product.productID}"><span class="glyphicon glyphicon-heart"></span></a>
									</div>
								</c:otherwise>
							</c:choose>
							</c:when>
							
						</c:choose>
						<div class="caption">
							<p>

								<a class="product" href="showProductInfo.htm?id=${product.productID }&action=showProductInfo"><strong>${product.productName}</strong></a>

							</p>
							$${product.productPrice } <a class="product" href="addOneToCart.htm?id=${product.productID}&action=addtocart">
							<i class="glyphicon glyphicon-shopping-cart"></i></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="more"><a href="wishlist.htm"><b>View More<span class="glyphicon glyphicon-hand-right"></span></b></a></div>
	</div>
	
	<div class="container text-center div-divider ">
	<h2 class="title">RECENT ORDERS</h2>
		<div class="row">
		<div class="table-responsive">
		<table class="table table-striped">
		
		<c:forEach var="order" items="${requestScope.orderlist}">
			<tr>
				<td>${order.orderId}</td>
				<td class="colhidden">${order.email}</td>
				<td>${order.date}</td>
				<td>$${order.price}</td>
				<td><a class="btn btn-primary" href="orderDetail.htm?id=${order.orderId}">order detail</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
	</div>
	<div class="more"><a href="orderHistory.htm"><b>View More<span class="glyphicon glyphicon-hand-right"></span></b></a></div>
	</div>	
	
	
	<div class="container text-center div-divider ">
	<h2 class="title">ADDRESS</h2>
	<div class="row">
		
		<div class="col-md-8 col-md-offset-2">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h3 class="panel-title">Edit Address</h3>
			</div>
			
		<div  class="panel-body">
		
		 <ul class="list-group">
 		<li class="list-group-item">Name: ${sessionScope.user.firstName} ${sessionScope.user.lastName}</li>
 		<li class="list-group-item">Email: ${requestScope.email.emailId}</li>
 		<li class="list-group-item">Phone: ${requestScope.address.phone}</li>
 		<li class="list-group-item">Address: ${requestScope.address.address}, ${requestScope.address.city} ${requestScope.address.state} ${requestScope.address.country} ${requestScope.address.pcode}</li>
 		<li class="list-group-item"><a href="editAddress.htm">edit address</a></li>
 	</ul>
		 
		 
		 </div>
	</div>
	</div>
	
	</div>
	</div>
	
</div>

</body>
</html>