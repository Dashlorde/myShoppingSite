<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div >
	<ul class=menu>
	<li class="welcome">Welcome ${sessionScope.username}</li>
	<li class="menu"><form action="search.htm" class="search"><input type="search" name="searchProduct"><button type="submit"><i class="material-icons">search</i></button></form></li>
	<li class="menu"><a href="logout.htm?action=logout" >log out</a> </li>
	<li class="menu"><a href="viewCart.htm?action=viewcart" >view cart</a> </li>
	<li class="menu"><a href="addProduct.htm" >add product</a></li>
	<li class="menu"><a href="viewMyProduct.htm?action=viewMyProduct">view my product</a></li>

	</ul>
</div>
