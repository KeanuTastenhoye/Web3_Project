<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Update Product</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div id="container">
    <header>
        <h1>
            <span>Web shop</span>
        </h1>
        <nav>
            <ul>
                <li>
                    <a href="Controller">Home</a>
                </li>
                <li>
                    <a href="Controller?action=personOverview">Users</a>
                </li>
                <li>
                    <a href="Controller?action=productOverview">Products</a>
                </li>
                <li>
                    <a href="Controller?action=naarSignUp">Sign up</a>
                </li>
                <li>
                    <a href="Controller?action=naarAddProduct">Add Product</a>
                </li>
            </ul>
        </nav>
        <h2>Update Product</h2>
    </header>
    <main>
        <c:set var="foutenProduct" value="${errorsProduct}"/>
        <c:if test="${errorsProduct != null}">
            <div class="alert-danger">
                <ul>
                    <c:forEach var="errorProduct" items="${errorsProduct}">
                        <li>${errorProduct}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <form method="post" action="Controller?action=updateProduct&productId=<c:out value='${product.productId}'/>" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p>
                <label for="name">Name</label>
                <input type="text" id="name" name="name" required value="<c:out value='${product.name}'/>">
            </p>
            <p>
                <label for="description">Description</label>
                <input type="text" id="description" name="description"required value="<c:out value='${product.description}'/>">
            </p>
            <p>
                <label for="price">Price</label>
                <input type="number" id="price" name="price" required value="<c:out value='${product.price}'/>">
            </p>
            <p>
                <input type="submit" id="updateProduct" value="Update Product">
            </p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>