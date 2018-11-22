<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Check password</title>
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
                    <a href="Controller?action=NaarSignUp">Sign up</a>
                </li>
                <li>
                    <a href="Controller?action=NaarAddProduct">Add Product</a>
                </li>
            </ul>
        </nav>
        <h2>Check Password</h2>
    </header>
    <main>
        <form method="post" action="Controller?action=VerifyPassword&userid=<c:out value='${userid}'/>" novalidate="novalidate">
            <!-- novalidate in order to be able to run tests correctly -->
            <p>
                <label for="Password">Password</label>
                <input type="password" id="password" name="password" required value="">
            </p>
            <p>
                <input type="submit" id="submit" value="Check"/>
            </p>
        </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>