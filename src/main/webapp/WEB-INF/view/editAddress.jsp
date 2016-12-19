<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>edit address</title>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.min.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
</script>
<style>
.changeAddress{
	position: absolute;
	top: 60px;
}
</style>
</head>
<body>
<div class="container-fluid"><jsp:include page="menu2.jsp"/></div>
<div class="container">
	<form action="editAddress.htm" method="post" class="changeAddress">
	<div class="form-group">
		phone: <input type="text" name="phone" class="form-control"><br /> 
		</div>
		<div class="form-group">
		address: <input type="text" name="address" class="form-control"><br />
		</div>
		<div class="col-sm-offset-2 col-sm-10">
		 <input type="submit" value="Edit Address" class="btn btn-default">
		 </div>
	</form>
</div>
</body>
</html>