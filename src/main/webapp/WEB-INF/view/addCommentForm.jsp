<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add comment</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap/css/bootstrap.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/menu.css" />
<link type="text/css" rel="stylesheet"
	href="<%=request.getContextPath()%>/css/commentForm.css" />
<script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/css/bootstrap/js/bootstrap.min.js" >
	</script>
</head>
<body>
<jsp:include page="menu2.jsp"/>

<div class="comment">
<div class="panel panel-default">
<div class="panel-heading">
		<h3 class="panel-title">
			Add Comment
		</h3>
	</div>
 <div  class="panel-body">
<form action="buyerComment.htm" method="post">
 <textarea name="comment" class="form-control" id="exampleTextarea" rows="3"></textarea><br/><br/>
 <input type="submit" value="add" class="btn btn-default">

</form>
</div>
</div>
</div>  
</body>
</html>