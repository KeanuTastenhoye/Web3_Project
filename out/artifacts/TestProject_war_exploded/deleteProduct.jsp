<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Delete Product</title>
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
                    <a href="Controller?action=PersonOverview">Users</a>
                </li>
                <li>
                    <a href="Controller?action=ProductOverview">Products</a>
                </li>
                <li>
                    <a href="Controller?action=CartOverview">Cart overview</a>
                </li>
                <li>
                    <a href="Controller?action=NaarSignUp">Sign up</a>
                </li>
                <c:if test="${sessionScope.role != null}">
                    <li>
                        <a href="Controller?action=NaarAddProduct">Add Product</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <h2>Delete Product</h2>
    </header>
    <main>
        <p>Are you sure you want to delete this product?</p>
            <form>
                <a href="Controller?action=DeleteProduct&productId=<c:out value='${product.productId}'/>"> Yes </a>
            </form>
            <form>
                <a href="Controller?action=ProductOverview"> No </a>
            </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>