<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Product Form</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" ></script>

<style type="text/css">
h2 { text-align: center; }

#form { margin-top: 80px;}

</style>
</head>
<body>
<jsp:include page="menu2.jsp" />

<main>
	
	<div class="container well" id="form">
	<div class="row">
	<h2>Add a Product Here</h2>
	<form:form action="addProduct.htm" class="form-horizontal" commandName="product" method="post" enctype="multipart/form-data" >
	<div class="form-group">
		<label class="control-label col-sm-3">Product Name:</label>
		<div class="col-sm-8">
		<form:input path="productName" size="30" htmlEscape="true" class="form-control"/><font color="red"><form:errors path="productName"/></font>
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Price:</label>
		<div class="col-sm-8">
		<form:input type="number" path="productPrice" size="30" htmlEscape="true" /><font color="red"><form:errors path="productPrice"/></font>
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Stock:</label>
		<div class="col-sm-8">
		<form:input type="number" path="stock" size="30" htmlEscape="true" /><font color="red"><form:errors path="stock"/></font> 
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Description:</label>
		<div class="col-sm-8">
		<form:textarea path="description"  class="form-control" htmlEscape="true" id="exampleTextarea" rows="3"/><font color="red"><form:errors path="description"/></font>
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Category:</label>
			<div class="col-sm-8">
				<form:select path="category" id="select" class="form-control">
					<form:option value="electronics">electronics</form:option>
					<form:option value="computers">computers</form:option>
				</form:select>
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Image: </label>
		<div class="col-sm-8">
		<input type="file" name="image" size="50" id="file" /><font color="red"></font>
		</div>
		</div>
		
		<div class="form-group">
		<div class="col-sm-offset-3 col-sm-8">
			<button type="submit" value="Add Product" class="btn btn-primary btn-lg">Add Product</button>
	</div>
	</div>
	</form:form>
	</div>
	</div>
</main>
</body>
</html>