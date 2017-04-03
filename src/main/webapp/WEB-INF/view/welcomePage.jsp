<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>welcome to my shopping website</title>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />

<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!--script src="<%=request.getContextPath()%>/css/masonry.pkgd.min.js" ></script-->
 <script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js"></script>


<style type="text/css">
body { background-color: #edeff2;}

.product {
	color: black;
	text-decoration: none;
}

.div_divider {
	border-bottom: solid 1px #404040;
}

.demo {
	margin-top: 20px;
	font-family:Papyrus;
}

.thumbnail:hover .image {
  opacity: 0.7;
}

.thumbnail:hover .upright{
	opacity: 1;
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
	border: none;
	border-radius: 10px;
	box-shadow: 3px 3px 3px #d7d9dd;
}

.thumbnail p {
	margin-top: 15px;
	color: #555;
}

.thumbnail a:hover {
	color: #b24276;
	text-decoration: none;
}

.grid-item {
	width: 500px;
	margin: 10px;
}

#nav.affix {
    position: fixed;
    top: 0;
    width: 100%;
    z-index:10;
}



#background{
	position:relative;
	z-index:10;
}

#sidebar{
 position:fixed;
 top:80px
}
 

#sidebar li a{
	display:block;
	color: #000;
	padding: 8px 16px;
	font-family:Lucida Grande;
}

#sidebar li a:hover {
    background-color: #555;
    color: white;
}

.customer{font-family:Papyrus;}

.wish{
	color:#b24276;
}

.center {
    position: absolute;
    top: 35%;
    left: 35%;
    transform: translate(-50%, -50%);
    font-size: 25px;
    color: #d7dfe0;
    font-family:Papyrus;
}


@media only screen and (max-width: 988px) {
  .sidebar-offcanvas {
   display:none;
}
	.center{
	display:none;
}
	

}

@media only screen and (max-width: 768px) {
  #background{
  	display:none;
  }
  

}
</style>

</head>
<body>
<script>

function cartAlert() {
    alert("successfully add to cart");
}

</script>
<header>
<c:choose>
		<c:when test="${empty user.name }">
		<div id="background">
		<img src="img/star.jpg" class="img-responsive"/>
			<div class="center"><p><b>
			Welcome to my shopping site demo<br>
			If you want to try it without registering a new user, you can try my user account<br>
			Username: user<br>
			Password: 123<br>
			</b></p></div>
		</div>
		</c:when>
	</c:choose>
</header>

	<c:choose>
		<c:when test="${not empty user.name }">
			<jsp:include page="menu2.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="menu.jsp" />
		</c:otherwise>
	</c:choose>
	
	<div class="container-fluid demo">
	
	<div class="row">
	<div class="col-md-2 col-md-offset-0">
	<div class="sidebar-nav-fixed affix"  id="sidebar">
	
	<div class="sidebar-offcanvas" > 
	
        <ul class="nav nav-pills nav-stacked" > 
          	<li><a href="showAllProducts.htm" target="content">All Products</a></li>
          	
			<li><a href="showElectronics.htm" target="content">Electronics</a></li>
			<li><a href="showComputers.htm" target="content">Computers</a></li>
        </ul> 
      </div> 
	
	</div>
</div>

<div class="jumbotron col-md-10 ">
		<div class="grid text-center ">

			<c:forEach var="product" items="${requestScope.productList}">
				<div class="col-xs-offset-0 col-xs-12 col-sm-offset-0 col-sm-6 col-md-6 col-lg-4 col-lg-offset-0 col-xlg-3 item">

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
							$${product.productPrice } <a class="product" href="addOneToCart.htm?id=${product.productID}&action=addtocart" onclick="cartAlert()">
							<i class="glyphicon glyphicon-shopping-cart"></i></a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		</div>
		</div>
	</div>


	

	<footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br> <br>
		<p>&copy;Yunlu Zhou</p>
</footer>

	<script type="text/javascript">
	
	$(function(){
		$('.thumbnail img').load(function(){   
	        $('.grid').masonry({   
	            itemSelector: '.item',  
	            layoutMode : 'fitRows'             
	        });       
	    });  
	});
	$(function() {
			$('.grid').masonry({
				// options
				itemSelector : '.item',
				layoutMode : 'fitRows'
				 
			});

		});
	
	</script>
</body>
</html>