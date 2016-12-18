<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<nav class="navbar-default navbar-fixed-top" role="navigation">
	<ul class="nav navbar-nav" >
	<li class="welcome">Welcome ${sessionScope.username}</li>
	
	
	<li data-role="dropdown" class="menu">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">seller account</a>
		<ul class="dropdown-menu" role="menu" aria-labelledby="navbarDropdownMenuLink">
			<li><a href="viewMyProduct.htm?action=viewMyProduct">view my product</a></li>
			<li><a href="addProduct.htm" >add product</a></li>
			<li><a href="viewOrder.htm">view order</a></li>
		</ul>
	</li>
	
	<li data-role="dropdown" class="menu">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">user account</a>
		<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
		<li><a href="orderHistory.htm">order history</a>
		</ul>
	</li>
	
	<li class="menu"><a href="viewCart.htm?action=viewcart" >view cart</a> </li>
	<li class="menu"><a href="logout.htm?action=logout" >log out</a> </li>
	</ul>
	<form action="search.htm" class="form-inline float-xs-right"><input type="search" name="searchProduct"><button type="submit" id="search"><i class="material-icons">search</i></button></form>
	
	

	
</nav>

