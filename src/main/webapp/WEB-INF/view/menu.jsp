<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<nav id="nav" class="navbar navbar-inverse navbar-static" data-spy="affix" data-offset-top="400">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>

		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><form action="search.htm" class="navbar-form navbar-center">
						<div class="form-group">
							<input type="search" name="searchProduct" class="form-control"
								placeholder="search products">
							<button type="submit" id="search" class="btn btn-danger">
								<i class="glyphicon glyphicon-search"></i>
							</button>
						</div>
					</form></li>
				<li><a href="showAllProducts.htm">Home</a></li>
				<li><a href="viewCart.htm?action=viewcart">Cart</a></li>
				<li class=" dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"><span class="glyphicon glyphicon-log-in"></span>Login<span
						class="caret"></span></a>
					<div class="dropdown-menu">
						<form:form action="login.htm" commandName="user" method="post"
							id="loginform">
							<div class="form-group">
								<input type="text" name="name" class="form-control"
									id="username" placeholder="Username">
							</div>
							<div class="form-group">
								<input type="password" name="password" class="form-control"
									id="password" placeholder="Password">
							</div>
							<div class="form-group">
								<input type="submit" class="btn btn-primary" name="login">
							</div>
						</form:form>
					</div></li>
				<li><a href="adduser.htm">Register</a></li>
			</ul>
		</div>
	</div>
</nav>
