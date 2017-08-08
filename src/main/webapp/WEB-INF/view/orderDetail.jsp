<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Order Detail</title>

    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" />
    <!--link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/contents.css" /-->
    <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/menu.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.min.js">
    </script>
    <style>
     body {
         background-color: #edeff2
     }
     
     .container {
         background-color: white;
         margin-top: 90px
     }
     
     
     @media only screen and (max-width:768px){.colhidden{display:none}}
   </style>
</head>

<body>


    <jsp:include page="menu2.jsp" />

    <main>
        <div class="container table-responsive">
            <table class="productTable table">
                <tr>
                    <th class="colhidden">Picture</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th></th>
                </tr>
                <c:forEach var="product" items="${requestScope.productList}">
                    <tr>
                        <td class="colhidden">
                            <a href="showProductInfo.htm?id=${product.key.productID }&action=showProductInfo"><img src="${product.key.imageName}" width="200px" /></a>
                        </td>
                        <td>${product.key.productName}</td>
                        <td>$${product.key.productPrice}</td>
                        <td>${product.value}</td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.user.id==requestScope.buyerId}">
                                    <!--a class="btn btn-primary" href="buyerComment.htm?id=${product.key.productID}">comment</a-->
                                    <a class="btn btn-primary" data-toggle="modal" data-target="#myModal">comment</a>
                                    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModelLabel" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                            <form action="buyerComment.htm?id=${product.key.productID}" method="post" >
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                                    <h4 class="modal-title" id="myModalLabel">add Comment</h4>
                                                </div>
                                                <div class="modal-body">
                                                	<textarea name="comment" class="form-control" id="exampleTextarea" rows="3"></textarea>
                                                    <!--br /> <br /> <input type="submit" value="add" class="btn btn-default"-->
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                </div>
                                                </form>
                                            </div>
                                            
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </main>

    <footer class="text-center">
        <a class="up-arrow" href="#" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br> <br>
        <p>&copy;Yunlu Zhou</p>
    </footer>

    <script>
        $(function() {
            $('#myModal').modal('hide')
        });
    </script>
</body>

</html>