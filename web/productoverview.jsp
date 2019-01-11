<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Overview</title>
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
                <li id="actual">
                    <a href="Controller?action=ProductOverview">Products</a>
                </li>
                <li>
                    <a href="Controller?action=CartOverview">Cart overview</a>
                </li>
                <li>
                    <a href="Controller?action=NaarSignUp">Sign up</a>
                </li>
                <c:if test="${role != null && role == 'ADMIN'}">
                    <li>
                        <a href="Controller?action=NaarAddProduct">Add Product</a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <h2>Product Overview</h2>

    </header>
    <main>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <c:if test="${role != null && role == 'ADMIN'}">
                    <th>Delete</th>
                </c:if>
                <th>Amount</th>
                <th>Add to cart</th>
            </tr>
            <c:forEach var="product" items="${records}">
            <tr>
                <td><c:if test="${role != null && role == 'ADMIN'}"><a href="Controller?action=EditProduct&productId=<c:out value='${product.productId}'/>"></c:if><c:out value='${product.name}'/></a></td>
                <td><c:out value='${product.description}'/></td>
                <td><c:out value='${product.price}'/></td>
                <c:if test="${role != null && role == 'ADMIN'}">
                    <td><a href="Controller?action=RemoveProduct&productId=<c:out value='${product.productId}'/>"> Delete </a></td>
                </c:if>
                <td>
                    <form method="post" action="Controller?action=AddToCart" novalidate="novalidate">
                        <input type="hidden" id="productId" name="productId" required value="<c:out value='${product.productId}'/>">
                        <input type="number" id="amount" name="amount" value="<c:out value='${number}'/>">
                        <input type="submit" id="addToCart" name="addToCart" value="Add To Cart">
                    </form>
                </td>
            </tr>
            </c:forEach>
            <caption>Products Overview</caption>
        </table>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>