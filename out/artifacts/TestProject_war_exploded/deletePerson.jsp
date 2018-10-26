<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <meta charset="UTF-8">
    <title>Delete Person</title>
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
        <h2>Delete Person</h2>
    </header>
    <main>
        <p>Are you sure you want to delete this person?</p>
            <form>
                <a href="Controller?action=deletePerson&userid=<c:out value='${person.userid}'/>"> Yes </a>
            </form>
            <form>
                <a href="Controller?action=personOverview"> No </a>
            </form>
    </main>
    <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
</div>
</body>
</html>