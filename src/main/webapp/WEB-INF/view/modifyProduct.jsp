<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modify product</title>

<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js" >
</script>

<style>

.container{margin-top:80px; background-color:beige}

.info{
	padding: 40px;
	font-size: 16px;
}

#submit{
	diplay:block;
	width: 30%;
	font-size: 15px;
	font-family:Lucida Grande;
	padding:10px;
	background-color :black;
	color: white;
	border-radius:8px;
	list-style-type:none;
	
}

label {
        float: left;
        width: 175px;
}
.form-error {
       font-family: Monaco;
}

.error{ 
	color: #e05a21;
	 font-family: Monaco;
}

</style>

</head>
<body>
<c:choose>
		<c:when test="${not empty user.name }">
			<jsp:include page="menu2.jsp"/>
		</c:when>
		<c:otherwise>
			<jsp:include page="menu1.jsp" />
		</c:otherwise>
	</c:choose>

<main>
<div class="container">
<div class="row">
<div class="info col-lg-8 col-lg-offset-3">
	<form:form action="modifyProduct.htm?id=${sessionScope.product.productID}&action=doModify" commandName="product" method="post" enctype="multipart/form-data" >
	
		<p><img src="${sessionScope.product.imageName}" width="40%"/><input type="file" name="image" size="50" id="file"/></p><br>
		
		<p><label>product name:</label> ${sessionScope.product.productName}</p><br>
		<p><label>product stock:</label> ${sessionScope.product.stock}
		<form:input path="stock" type="number" min="1" name="stock" htmlEscape="true" 
		data-validation-error-msg="stock should be at least 1"/>
		<div class="error"><form:errors path="stock"/></div></p><br>
		<p><label for="description">product description:</label>
		<form:textarea path="description" name="description" rows="10" cols="50" htmlEscape="true"/></p>
		<font color="red"><form:errors path="description"/></font><br>
		<p><input type="submit" value="Modify Product" id="submit" class="col-md-offset-2"></p>
		
	
	
	</form:form>
	</div>
	</div>	
</div>
</main>

<footer class="text-center">
		<a class="up-arrow" href="#" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br> <br>
		<p>&copy;Yunlu Zhou</p>
</footer>

 <script type="text/javascript">
	 $.validate({
          	modules: 'html5', 
      	});
	  $("input").rules("remove", "required");
	  
</script>	
</body>


</html>