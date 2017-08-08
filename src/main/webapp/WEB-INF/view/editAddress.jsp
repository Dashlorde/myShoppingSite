<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit address</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="script/countries.js"></script>
<style>
.changeAddress{
	margin-top:80px;
	width:60%
}
</style>
</head>
<body>

<jsp:include page="menu2.jsp"/>

<main>
<div class="container changeAddress">
	<form:form action="editAddress.htm" commandName="userAddress" method="post">
	<div class="panel panel-default">
<div class="panel-heading">
<h3 class="panel-title">Edit Address</h3>
</div>
<div  class="panel-body">
	<div class="form-group">
		phone: <form:input path="phone" name="phone" class="form-control"/><font color="red"><form:errors path="phone"/></font><br/> 
		</div>
		<div class="form-group">
		address: <form:input path="address" name="address" class="form-control"/><font color="red"><form:errors path="address"/></font><br/>
		</div>
		<div class="form-group">
		city: <form:input path="city" name="city" class="form-control"/><font color="red"><form:errors path="address"/></font><br/>
		</div>
		<div class="form-group">
		country: <form:select path="country" name="country" class="form-control" id="country"/><font color="red"><form:errors path="address"/></font><br/>
		</div>
		<div class="form-group">
		state: <form:select path="state" name="state" class="form-control" id="state"/><font color="red"><form:errors path="address"/></font><br/>
		</div>
		<div class="form-group">
		postal code: <form:input path="pcode" name="pcode" class="form-control"/><font color="red"><form:errors path="address"/></font><br/>
		</div>
		
		<div class="col-sm-offset-2 col-sm-10">
		
		 <input type="submit" value="Edit Address" class="btn btn-default">
		 </div>
		 </div>
</div>
	</form:form>
</div>
</main>
<footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span
			class="glyphicon glyphicon-chevron-up"></span>
		</a><br> <br>
		<p>&copy;Yunlu Zhou</p>
	</footer>
</body>
<script>
populateCountries("country", "state");
</script>
</html>