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

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
<script
	src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js">
	
</script>


<style type="text/css">
body {
	background-color: #edeff2
}

body,html,.row-offcanvas {
  height:100%;
}
#myNavbar li a{
	font-size:16px;
}

.product {
	color: black;
	text-decoration: none;
}



.div_divider {
	border-bottom: solid 1px #404040;
}

.demo {
	margin-top: 20px;
	
}

.thumbnail {
	padding: 10px;
	border: none;
	border-radius: 10px;
	margin: 20px;
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
#sidebar {
  background-color:#f5f5f5;
  margin-top:80px;
  width: 20%
}

#sidebar li a{
	display:block;
	color: #000;
	padding: 8px 16px;
	text-decoration:none;
	font-family:Lucida Grande;
}

#sidebar li a:hover {
    background-color: #555;
    color: white;
}

@media screen and (max-width: 768px) {
  .row-offcanvas {
    position: relative;
    -webkit-transition: all 0.25s ease-out;
    -moz-transition: all 0.25s ease-out;
    transition: all 0.25s ease-out;
    width:calc(100% + 220px);
  }
    
  .row-offcanvas-left
  {
    left: -220px;
  }

  .row-offcanvas-left.active {
    left: 0;
  }

  .sidebar-offcanvas {
    position: absolute;
    top: 0;
  }
}
</style>
<script>
$(document).ready(function () {
	  $('[data-toggle="offcanvas"]').click(function () {
	    $('.row-offcanvas').toggleClass('active')
	  });
	});
</script>
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
	
	
	<div class="container-fluid demo">
	
	<div class="row">
	<div class="col-md-2 col-lg-2">
	<div class="sidebar-nav-fixed affix" id="sidebar">
	
	<div class="sidebar-offcanvas"> 
        <ul class="nav nav-pills nav-stacked"> 
          	<li><a href="showAllProducts.htm" target="content">All Products</a>
			<li><a href="showElectronics.htm" target="content">Electronics</a>
			<li><a href="showComputers.htm" target="content">Computers</a>
        </ul> 
      </div> 
	
	</div>
</div>

<div class="jumbotron col-md-10 col-lg-10 ">
		<div class="grid text-center ">

			<c:forEach var="product" items="${requestScope.productList}">
				<div
					class="col-xs-offset-0 col-xs-12 col-sm-offset-0 col-sm-6 col-md-6 item">

					<div class="thumbnail">

						<a
							href="showProductInfo.htm?id=${product.productID }&action=showProductInfo"><img 
							src="${product.imageName}" /></a>
						<div class="caption">
							<p>

								<a class="product"
									href="showProductInfo.htm?id=${product.productID }&action=showProductInfo"><strong>${product.productName}</strong></a>

							</p>
							$${product.productPrice } <a class="product"
								href="addOneToCart.htm?id=${product.productID}&action=addtocart"><i
								class="glyphicon glyphicon-shopping-cart"></i></a>
						</div>
					</div>
				</div>
			</c:forEach>
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

	<script
		src="https://unpkg.com/isotope-layout@3/dist/isotope.pkgd.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.grid').isotope({
				// options
				itemSelector : '.item',
				layoutMode : 'fitRows'
			});

		});
	</script>
</body>
</html>