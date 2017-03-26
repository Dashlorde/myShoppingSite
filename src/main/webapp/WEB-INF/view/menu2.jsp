<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<div class="container-fluid">
<div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
		<span class="icon-bar"></span>
	</button>
	<a href="#" class="navbar-brand">Welcome ${sessionScope.username}</a>
 </div>
 
 <div class="collapse navbar-collapse" id="myNavbar">
	<ul class="nav navbar-nav navbar-right" >
	
	<li><form action="search.htm" class="navbar-form navbar-center">
	<div class="form-group">
	<input type="search" name="searchProduct" class="form-control" placeholder="search products"><button type="submit" id="search" class="btn btn-danger"><i class="glyphicon glyphicon-search"></i></button>
	</div>
	</form></li>
	<li><a href="showAllProducts.htm">Home</a></li>
	<li data-role="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">seller account<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu" aria-labelledby="navbarDropdownMenuLink">
			<li><a href="viewMyProduct.htm?action=viewMyProduct">view my product</a></li>
			<li><a href="addProduct.htm" >add product</a></li>
			<li><a href="viewOrder.htm">view order</a></li>
		</ul>
	</li>
	
	<li data-role="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">user account<span class="caret"></span></a>
		<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		<li><a href="orderHistory.htm">order history</a>
		</ul>
	</li>
	
	<li ><a href="viewCart.htm?action=viewcart" >view cart</a> </li>
	<li><a href="logout.htm?action=logout" >log out</a> </li>
	
	
	</ul>
</div>	
</div>
	
</nav>

