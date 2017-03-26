<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Informatino</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/showProductInfo.css" />
<script
	src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js">
	
</script>
<style>
@CHARSET "UTF-8";
.container-fluid{margin-right:20px}


.productInfo {
	background-color: white;
	box-shadow: 3px 3px 3px #d7d9dd;
	margin-top: 80px;
}

body {
	background-color: #edeff2
}

.left {
	float: left
}

.div-divider {
	padding:20px;
	border-bottom: solid 1px #d7d9dd;
}

#info {
	margin-top: 50px;
	margin-bottom: 50px;
}

#sidebar {
	background-color: #f5f5f5;
	margin-top: 80px;
	width: 13%
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

	<main>

	<div class="container-fluid row">

		<div class="col-md-2 ">
			<div class="sidebar-nav-fixed affix" id="sidebar">

				<div class="sidebar-offcanvas">
					<ul class="nav nav-pills nav-stacked">
						<li><a href="showAllProducts.htm" target="content">All
								Products</a>
						<li><a href="showElectronics.htm" target="content">Electronics</a>
						<li><a href="showComputers.htm" target="content">Computers</a>
					</ul>
				</div>

			</div>
		</div>

		<div
			class="jumbotron col-sm-offset-2 col-md-10 col-md-offset-2  productInfo">
			<div class="container" id="info">
				<div class="col-md-5 left">
					<img src="${requestScope.product.imageName}" width="100%" />
				</div>
				<div class="col-md-5 col-md-offset-1">
					<div class="div-divider">
						<strong><span style="color: #b24276; font-size: 20px">${requestScope.product.productName}</span></strong> sell by
						${requestScope.product.username}<br/>
						$${requestScope.product.productPrice}<br/><br/>
						<form class="form-inline"
							action="addtocart.htm?id=${product.productID}" method="POST">
							<input type="number" name="quantity" min="1" class="form-control">
							<div class="input-group" style="color: red">${requestScope.quantityError}</div>
							<button type="submit" value="Add to Cart" class="btn btn-default"><span class="glyphicon glyphicon-shopping-cart"></span></button>
						</form>
					</div>
					<div class="div-divider">
					<h3><span class="glyphicon glyphicon-leaf"></span>About Product</h3>
						<p>${requestScope.product.description}</p>
					</div>
				</div>
			</div>

			<div class="panel panel-warning">
				<div class="panel-heading">
					<h2 class="panel-title">User Comments <span class="glyphicon glyphicon-comment"></span></h2>
				</div>
				<div class="panel-body">
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

		</div>
	</div>

	</main>
	
	<footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a><br> <br>
		<p>&copy;Yunlu Zhou</p>
	</footer>
</body>
</html>