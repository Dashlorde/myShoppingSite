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
	<a href="#" class="navbar-brand customer">Welcome ${sessionScope.username}</a>
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
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Seller Account<span class="caret"></span></a>
		<ul class="dropdown-menu" role="menu" aria-labelledby="navbarDropdownMenuLink">
			<li><a href="viewMyProduct.htm?action=viewMyProduct">My Product</a></li>
			<li><a href="addProduct.htm" >Add Product</a></li>
			<li><a href="viewOrder.htm">View Order</a></li>
		</ul>
	</li>
	
	<li data-role="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">User Account<span class="caret"></span></a>
		<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		<li><a href="personalPage.htm">Account</a></li>
		<li role="separator" class="divider"></li>
		<li><a href="orderHistory.htm">Order History</a></li>
		<li><a href="wishlist.htm">Wish List</a></li>
		</ul>
	</li>
	
	<li ><a href="viewCart.htm?action=viewcart" >Cart</a> </li>
	<li><a href="logout.htm?action=logout" >Log out</a> </li>
	
	
	</ul>
</div>	
</div>
	
</nav>

