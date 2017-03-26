<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit address</title>

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>
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
</html>