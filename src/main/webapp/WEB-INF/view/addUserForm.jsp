<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User Form</title>
 
 <link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>

<style type="text/css">
h2 { text-align: center; }

.form {margin-top:60px}

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
		
		var username=document.getElementById("username").value;
		var query="action=ajaxCheck&username="+username;
		
		
		xmlHttp.onreadystatechange = function stateChanged()
        {
            if (xmlHttp.readyState == 4 && xmlHttp.status==200)
            {
               // alert(xmlHttp.responseText);
               // var json = JSON.parse(xmlHttp.responseText);
                document.getElementById("error").innerHTML = xmlHttp.responseText;
                
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

<div class="container well">
<div class="row">

<form:form action="adduser.htm?action=adduser" commandName="user" method="post" class="form-horizontal">
    <div class="form-group">
    <label class="control-label col-sm-2" for="firstName">First Name:</label>
    <div class="col-sm-9">
    <form:input path="firstName" size="30" htmlEscape="true" class="form-control"/>
    <font color="red"><form:errors path="firstName"/></font>
    </div>
	</div>
	
	<div class="form-group">
    <label class="control-label col-sm-2" for="lastName">Last Name:</label>
    <div class="col-sm-9">
    <form:input path="lastName" size="30" htmlEscape="true" class="form-control"/>
    <font color="red"><form:errors path="lastName"/></font>
    </div>
	</div>

	<div class="form-group">
	<label class="control-label col-sm-2" for="username">User Name:</label> 
	<div class="col-sm-9">
	<form:input path="name" size="30" name="username" class="form-control" id="username" onblur="return checkUser()" htmlEscape="true"/>
	<div id="error" style="color:red"></div> <font color="red"><form:errors path="name"/></font>
	</div>
	</div>

	<div class="form-group">
    <label class="control-label col-sm-2" for="password">Password:</label>
    <div class="col-sm-9">
    <form:password path="password" size="30" class="form-control"/>
    <font color="red"><form:errors path="password" htmlEscape="true"/></font>
    </div>
	</div>

	<div class="form-group">
    <label class="control-label col-sm-2" for="email">Email:</label>
    <div class="col-sm-9">
    <form:input type="email" placeholder="me@example.com" path="email.emailId" size="30" class="form-control"/> 
    <font color="red"><form:errors path="email.emailId" htmlEscape="true"/>${emailError}</font>
    </div>
	</div>
	<!--div>
    Paypal username:
   	<form:input path="paypalUsername" size="30" />


	Paypal password:
    <form:password path="paypalPassword" size="30" />



    Paypal signature:
    <form:input path="signature" size="30" />
    </div-->
    


	<div class="form-group">
	<div class="col-sm-offset-2 col-sm-10">
    	<button type="submit" class="btn btn-primary btn-lg" id="submit">Create User</button><br/>
    </div>
    </div>
    <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    	<label>Already Had an Account?</label>
		<a href="login.htm" class="btn btn-info">login</a><br/>
	</div>
	</div>


</form:form>

</div>
</div>
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