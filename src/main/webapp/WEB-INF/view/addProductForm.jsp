<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Product Form</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js" ></script>

<style type="text/css">
h2 { text-align: center; }

#form { margin-top: 80px;}

body{background-color: white}

.container{background-color:#f9f9e0}

 .form-group .form-error {
       
   font-family: Monaco;
}

.error{ 
	color: #e05a21;
	 font-family: Monaco;
}

</style>
</head>
<body>
<jsp:include page="menu2.jsp" />

<main>
	
	<div class="container" id="form">
	<div class="row">
	<h2>Add a Product Here</h2>
	<form:form action="addProduct.htm" class="form-horizontal" commandName="product" method="post" enctype="multipart/form-data" >
	<div class="form-group">
		<label class="control-label col-sm-3">Product Name:</label>
		<div class="col-sm-6">
		<form:input path="productName" size="30" htmlEscape="true" class="form-control"
		data-validation="length" data-validation-length="min1" data-validation-error-msg="product name required"/>
		<div class="error"><form:errors path="productName"/></div>
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Price:</label>
		<div class="col-sm-6">
		<form:input path="productPrice" min="0.0" htmlEscape="true" 
		data-validation="number" data-validation-allowing="positive, float" data-validation-error-msg="please input a valid price"/>
		<div class="error"><form:errors path="productPrice"/></div>
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Stock:</label>
		<div class="col-sm-6">
		<form:input path="stock" size="30" min="1" htmlEscape="true" 
		data-validation="number" data-validation-allowing="positive" data-validation-error-msg="product stock at least 1"/>
		<div class="error"><form:errors path="stock"/></div> 
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Description:</label>
		<div class="col-sm-6">
		<form:textarea path="description"  class="form-control" htmlEscape="true" id="exampleTextarea" rows="3"
		data-validation="length" data-validation-length="min1" data-validation-error-msg="description cannot be empty"/>
		<div class="error"><form:errors path="description"/></div>
		</div>
		</div>
		
		
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Image: </label>
		<div class="col-sm-6">
		<input type="file" name="image" id="file" />
		<div class="error"><form:errors path="imageName"/></div>
		</div>
		</div>
		
		<div class="form-group">
		<label class="control-label col-sm-3">Product Category:</label>
			<div class="col-sm-6">
				<form:select path="category" id="select" class="form-control">
					<form:option value="electronics">electronics</form:option>
					<form:option value="computers">computers</form:option>
				</form:select>
		</div>
		</div>
		
		<div class="form-group">
		<div class="col-sm-offset-3 col-sm-8">
			<button type="submit" value="Add Product" class="btn btn-primary btn-lg" id="submit">Add Product</button>
	</div>
	</div>
	</form:form>
	</div>
	</div>
</main>

<footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br> <br>
		<p>&copy;Yunlu Zhou</p>
</footer>

 <script type="text/javascript">
      $.validate({
    	   modules : 'html5',
       });
      
</script>	
</body>
</html>