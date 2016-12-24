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
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/userForm.css" />
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>
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

<h2>Register a New User</h2>

<form:form action="adduser.htm?action=adduser" commandName="user" method="post" id="form">

<div >
<table>
<tr>
    <td>First Name:</td>
    <td><form:input path="firstName" size="30" htmlEscape="true"/></td>
    <td> <font color="red"><form:errors path="firstName"/></font></td>
</tr>

<tr>
    <td>Last Name:</td>
    <td><form:input path="lastName" size="30" htmlEscape="true"/></td> 
    <td><font color="red"><form:errors path="lastName"/></font></td>
</tr>
<tr>
	<td>User Name: </td>
	<td><form:input path="name" size="30" name="username" id="username" onblur="return checkUser()" htmlEscape="true"/></td>
	<td><div id="error" style="color:red"></div> <font color="red"><form:errors path="name"/></font></td>
	
<tr>

    <td>Password:</td>
    <td><form:password path="password" size="30" /></td>
    <td> <font color="red"><form:errors path="password" htmlEscape="true"/></font></td>
</tr>

<tr>
    <td>Email:</td>
    <td><form:input type="email" placeholder="me@example.com" path="email.emailId" size="30" /></td> 
    <td><font color="red"><form:errors path="email.emailId" htmlEscape="true"/>${emailError}</font></td>
</tr>

<tr>
    <td>Paypal username:</td>
    <td><form:input path="paypalUsername" size="30" /></td> 
</tr>

<tr>
    <td>Paypal password:</td>
    <td><form:password path="paypalPassword" size="30" /></td> 
</tr>

<tr>
    <td>Paypal signature:</td>
    <td><form:input path="paypalSignature" size="30" /></td> 
</tr>
</table>


    <input type="submit" value="Create User" id="submit"/><br/>
    <p id="actionChange">Already Had an Account</p>
    
<a href="login.htm" id="signin" class="actionChange">Log In</a> <br/>




</div>

</form:form>

</body>
</html>