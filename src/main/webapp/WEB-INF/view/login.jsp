<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userLogin</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>

<style>
h2 { text-align: center;}

body{background-image:url('img/star.jpg'); color:#d9d9d9}

.form { margin-top:60px }

.form-group .form-error {
       color: red;
       font-family: Monaco;
 }
 

</style>
</head>
<body>
<jsp:include page="menu1.jsp" />

<main>
<div class="container form">
<div class="page-header">
<h2>User Log In</h2>
</div>

<div class="container">
<div class="row">
<form:form class="form-horizontal" action="login.htm" commandName="user" method="post" id="form">
 <div class="form-group">
	<label class="control-label col-sm-2" for="name">User Name:</label>
	<div class="col-sm-9">
	<form:input path="name" size="30" htmlEscape="true" class="form-control" data-validation="alphanumeric"/>
	<div class="form-error">${error} </div>
	</div>
	</div>
	
 <div class="form-group">
	<label class="control-label col-sm-2" for="password">Password:</label>
	<div class="col-sm-9">
	<form:password path="password" size="30" htmlEscape="true" class="form-control" data-validation="length" data-validation-length="min3" data-validation-error-msg="password must contains at least 3 character"/>
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
<script type="text/javascript">
$.validate({
    modules: 'html5',
    
});

</script>
</main>
</body>
</html>