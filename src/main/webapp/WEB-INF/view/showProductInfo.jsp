<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Information</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/showProductInfo.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js"></script-->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>

.container-fluid{margin-right:20px;}

.info{margin-bottom: 20px}
.productInfo {
	background-color: white;
	box-shadow: 3px 3px 3px #d7d9dd;
	margin-top: 80px;
}
#user{ 
	font-size: 16px;
}
body {
	background-color: #edeff2
}

.nav-tabs a{ 
	color:black;
	font-family:Papyrus
}

.panel-heading, h3{font-family:Papyrus}
.panel-body{
	font-family:Lucida Grande;
}

.left {
	float: left
}

.div-divider {
	padding:20px;
	border-top: solid 1px #d7d9dd;
	margin-bottom: 40px;
	margin-top:40px;
}

.div-divider-lg{
	margin-top:40px;
	margin-bottom: 80px;
}

.div-divider-sm{
	margin-top:40px;
}

.wish{
	color: #b24276;
}

.center{
	text-align:center;
}

@media only screen and (max-width: 988px) {
  .sidebar-offcanvas {
   display:none;
}
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
			class="jumbotron col-xs-offset-1 col-md-10 col-md-offset-0  productInfo">
			<div class="container-fluid info">
			<div>
				<div class="col-md-6  left">
					<img src="${requestScope.product.imageName}" width="90%" />
				</div>
				<div class="col-md-6 ">
					<div>
						<strong><span style="color: #b24276; font-size: 20px">${requestScope.product.productName}</span></strong> <span style="font-family:Papyrus">sell by
						${requestScope.product.username}</span><br/>
						<b>$${requestScope.product.productPrice}</b><br/><br/>
						<form class="form-inline" action="addtocart.htm?id=${product.productID}" method="POST">
							<input type="number" name="quantity" min="1" class="form-control">
							
							<button type="submit" value="Add to Cart" class="btn btn-default"><span class="glyphicon glyphicon-shopping-cart"></span></button>
							<div class="input-group" style="color: red">${requestScope.quantityError}</div>
						</form>
						
						<br/>
						
						<c:choose>
						<c:when test="${not empty user.name }">
							<c:choose>
								<c:when test="${requestScope.wish.contains(product.productID)}">
									<a type="button" class="btn btn-default" href="removeFromWish.htm?id=${product.productID}"><span class="glyphicon glyphicon-heart wish"></span></a>							
								</c:when>
								<c:otherwise>
									<a type="button" class="btn btn-default" href="productAddToWish.htm?id=${product.productID}"><span class="glyphicon glyphicon-heart"></span></a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<a type="button" class="btn btn-default" href="productAddToWish.htm?id=${product.productID}"><span class="glyphicon glyphicon-heart"></span></a>
						</c:otherwise>
						</c:choose>
						<span style="font-family: Papyrus">Add to Wishlist</span>
						
					</div>
					<div class="div-divider">
					<h3><span class="glyphicon glyphicon-leaf"></span>About Product</h3>
						<p>${requestScope.product.description}</p>
					</div>
				</div>
				</div>
				
				<div class="col-lg-12 div-divider-lg">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#returnpolicy"><b>Return Policy</b></a></li>
                		<li><a data-toggle="tab" href="#contact"><b>Contact Seller</b></a></li>
					</ul>
				
					<div class="tab-content div-divider-sm">
						<div class="tab-pane fade in active" id="returnpolicy">
							<p>Since this is not a real shopping site, we will not offer any return services. Please do not pay anything on this site. Or you can do some tests through paypal sandbox.</p>
						</div>
					
						<div class="tab-pane fade" id="contact">
							<p>If you have any issue, please contact <i>${requestScope.sellerEmail}</i></p>
						</div>
					
					</div>
				</div>
				
			
		<div class="col-lg-12">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h2 class="panel-title">
					<a data-toggle="collapse" href="#comment">User Comments  <span class="glyphicon glyphicon-comment"></span></a>
					</h2>
				</div>
				<div id="comment" class="panel-collapse collapse">
				<div class="panel-body table-responsive">
					<table class="table table-sm table-striped table-inverse">

						<c:forEach var="comment" items="${requestScope.commentList}">
							<tr>
								<td id="user" class="center"><span class="glyphicon glyphicon-user"></span> ${comment.user.name}</td>
								<td class="col-md-8">${comment.comment}</td>
								<td class="center">${comment.commentTime}</td>
							<tr>
						</c:forEach>
					</table>
				</div>
				</div>
			</div>
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