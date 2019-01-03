<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Cart Overview</title>
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
                <li id="actual">
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
        <h2>Cart Overview</h2>
    </header>
    <main>
        <p>Following items have been added to the shopping cart!</p>
        <table>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
            </tr>

            <tr>
                <td><c:out value='${product.name}'/></td>
                <td><c:out value='${product.description}'/></td>
                <td><c:out value='${product.price}'/></td>
            </tr>
        </table>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>
