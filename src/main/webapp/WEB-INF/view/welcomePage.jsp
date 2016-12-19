<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome to my shopping website</title>
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/menu.css" />
  <link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/css/listProduct.css" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
  <link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
	</script>

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
	
	
            
            <c:forEach var="product" items="${requestScope.productList}">
            <div class="floatBox">
            <ul class="product">
                <li class="pic"><a href="showProductInfo.htm?id=${product.productID }&action=showProductInfo"><img src="${product.imageName}" width="100%" /></a></li>
                <li class="left"><a href="showProductInfo.htm?id=${product.productID }&action=showProductInfo">${product.productName}</a></li>
                <li class="left"> $${product.productPrice }</li> 
              	<li class="right"><a href="addOneToCart.htm?id=${product.productID}&action=addtocart"><i class="material-icons" >add_shopping_cart</i></a></li>
            </ul>
            </div>
            </c:forEach>
     
   
</body>
</html>