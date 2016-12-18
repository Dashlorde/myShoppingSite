<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userLogin</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/userForm.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>
</head>
<body>
<h2>User Log In</h2>

<form:form action="login.htm" commandName="user" method="post" id="form">

<table>
<tr>
	<td>User Name:</td>
	<td><form:input path="name" size="30" htmlEscape="true"/></td>
	<td><div style="color:red">${error} </div></td>
</tr>

<tr>
	<td>Password:</td>
	<td><form:password path="password" size="30" htmlEscape="true"/></td>
</tr>



</table>

<input type="submit" value="login" id="submit"> <br/>
<p id="actionChange">OR Register a New User</p>
 <a href="adduser.htm" class="actionChange">Register</a> <br/>
 


</form:form>

</body>
</html>