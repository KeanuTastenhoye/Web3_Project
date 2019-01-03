<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta charset="UTF-8">
<title>personOverview</title>
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
                    <li id="actual">
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
                    <c:if test="${role != null && role == 'ADMIN'}">
                        <li>
                            <a href="Controller?action=NaarAddProduct">Add Product</a>
                        </li>
                    </c:if>
                </ul>
            </nav>
            <h2>User Overview</h2>
        </header>
        <main>

            <form action="Controller?action=Sort" method="post">
                <p>Sorteren op:</p>
                <select name="sort">
                    <option name="leeg" value=""> </option>
                    <c:forEach var="head" items="${headers}">
                        <option name="${head}" value="${head}"> ${head} </option>
                    </c:forEach>
                    <input type="submit" value="Sort">
                </select>
            </form>
            <table>
                <tr>
                    <th>User id</th>
                    <th>E-mail</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <c:if test="${role != null && role == 'ADMIN'}">
                        <th>Delete</th>
                        <th>Check Password</th>
                    </c:if>
                </tr>
                <c:forEach var="person" items="${persons}">
                <tr>
                    <td><c:out value='${person.userid}'/></td>
                    <td><c:out value='${person.email}'/></td>
                    <td><c:out value='${person.firstName}'/></td>
                    <td><c:out value='${person.lastName}'/></td>
                    <c:if test="${role != null && role == 'ADMIN'}">
                        <td><a href="Controller?action=RemovePerson&userid=<c:out value='${person.userid}'/>"> Delete </a></td>
                        <td><a href="Controller?action=CheckPassword&userid=${fn:escapeXml(person.userid)}"> Check </a></td>
                    </c:if>
                </tr>
                </c:forEach>
                <caption>Users Overview</caption>
            </table>
        </main>
        <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
    </div>
</body>
</html>