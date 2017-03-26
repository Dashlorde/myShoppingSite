<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userLogin</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>

<style>
h2 { text-align: center; color:#d9d9d9 }

body{background-color:black}

.form { margin-top:60px }
</style>
</head>
<body>
<jsp:include page="menu1.jsp" />

<main>
<div class="container form">
<div class="page-header">
<h2>User Log In</h2>
</div>

<div class="container well">
<div class="row">
<form:form class="form-horizontal" action="login.htm" commandName="user" method="post" id="form">
 <div class="form-group">
	<label class="control-label col-sm-2" for="name">User Name:</label>
	<div class="col-sm-9">
	<form:input path="name" size="30" htmlEscape="true" class="form-control"/>
	<div style="color:red">${error} </div>
	</div>
	</div>
	
 <div class="form-group">
	<label class="control-label col-sm-2" for="password">Password:</label>
	<div class="col-sm-9">
	<form:password path="password" size="30" htmlEscape="true" class="form-control"/>
	</div>
</div>

 <div class="form-group">
 <div class="col-sm-offset-2 col-sm-10">
<button type="submit" value="login" class="btn btn-primary">login</button><br/>
</div>
</div>

 <div class="form-group">
 <div class="col-sm-offset-2 col-sm-10">
<label>OR Register a New User</label>
 <a href="adduser.htm" class="btn btn-info">Register</a> <br/>
 </div>
 </div>
 


</form:form>
</div>
</div>
</div>

</main>

<footer class="text-center">
	<a class="up-arrow" href="#" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br> <br>
	<p>&copy;Yunlu Zhou</p>
</footer>
</body>
</html>