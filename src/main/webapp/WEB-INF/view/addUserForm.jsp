<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>


<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User Form</title>
 
 <link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link href="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/theme-default.min.css"
    rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" ></script>


<style type="text/css">
body{
	background-image:url('img/star.jpg');
	color:#d9d9d9;
}

h2 { text-align: center;}

.form {margin-top:60px;}

.form-group .form-error {
            color: red;
            font-family: Monaco;
 }

#usernameError{
		font-family: Monaco;
		color: red;
}

.error{ 
	color: #e05a21;
	font-family: Monaco;
}


</style>
</head>
<body>
<script>
	
	var xmlHttp;
	xmlHttp=GetXmlHttpObject();
	function checkUser(){
		if(xmlHttp==null){
			alert("Your browser does not support AJAX!");
			return;
		}
		
		var username=document.getElementById('thisusername').value;
		var query="action=ajaxCheck&username="+username;

		xmlHttp.onreadystatechange = function stateChanged()
        {
            if (xmlHttp.readyState == 4 && xmlHttp.status==200)
            {
               // alert(xmlHttp.responseText);
              // var json = JSON.parse(xmlHttp.responseText);
                document.getElementById("usernameError").innerHTML = xmlHttp.responseText;
            }
        };
        xmlHttp.open("POST", "adduser.htm", true);
        xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlHttp.send(query);
		return false;
		
	}
	
	 function GetXmlHttpObject()
     {
         var xmlHttp = null;
         try
         {
             xmlHttp = new XMLHttpRequest();
         } catch (e)
         {
             // Internet Explorer
             try
             {
                 xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
             } catch (e)
             {
                 xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
             }
         }
         return xmlHttp;
     }
</script>
<jsp:include page="menu1.jsp" />

<main>
<div class="container form">



<div class="page-header">
<h2>Register a New User</h2>
</div>

<div class="container">
<div class="row">

<form:form action="adduser.htm?action=adduser" commandName="user" method="post" class="form-horizontal">
    <div class="form-group">
    <label class="control-label col-sm-2" for="firstName">First Name:</label>
    <div class="col-sm-9">
    <form:input path="firstName" data-validation="alphanumeric" size="30" htmlEscape="true" class="form-control"/>
    
    <div class="error"><form:errors path="firstName"/></div>
    </div>
	</div>
	
	<div class="form-group">
    <label class="control-label col-sm-2" for="lastName">Last Name:</label>
    <div class="col-sm-9">
    <form:input path="lastName" size="30" htmlEscape="true" class="form-control" data-validation="alphanumeric"/>
    <div class="error"><form:errors path="lastName"/></div>
    </div>
	</div>

	<div class="form-group">
	<label class="control-label col-sm-2" for="username">User Name:</label> 
	<div class="col-sm-9">
	<form:input path="name" size="30" class="form-control" id="thisusername" onblur="return checkUser()" htmlEscape="true"/>
	<div id="usernameError" style="color:red"></div> <div class="error"><form:errors path="name"/></div>
	</div>
	</div>

	<div class="form-group">
    <label class="control-label col-sm-2" for="password">Password:</label>
    <div class="col-sm-9">
    <form:password path="password" size="30" class="form-control" data-validation="length" data-validation-length="min3" data-validation-error-msg="password must contains at least 3 character"/>
    <div class="error"><form:errors path="password" htmlEscape="true"/></div>
    </div>
	</div>

	<div class="form-group">
    <label class="control-label col-sm-2" for="email">Email:</label>
    <div class="col-sm-9">
    <form:input type="email" placeholder="me@example.com" path="email.emailId" size="30" class="form-control" data-validation="email"/> 
    <div class="error"><form:errors path="email.emailId" htmlEscape="true"/>${emailError}</div>
    </div>
    
	</div>
	
	<div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
    	<button type="submit" class="btn btn-primary btn-lg" id="submit">Create User</button><br/>
    </div>
    </div>
    <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    	<label>Already Have an Account?</label>
		<a href="login.htm" class="btn btn-info">login</a><br/>
	</div>
	</div>


</form:form>

</div>
</div>
</div>

</main>
<script type="text/javascript">
$.validate({
    modules: 'html5',

});

</script>


</body>
</html>