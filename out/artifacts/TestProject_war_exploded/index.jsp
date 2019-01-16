<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Home</title>
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
					<li id="actual">
						<a href="Controller">Home</a>
					</li>
					<c:if test="${sessionScope.person != null}">
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
					</c:if>
				</ul>
			</nav>
			<h2>Home</h2>
		</header>
		<main> Sed ut perspiciatis unde omnis iste natus error sit
		voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque
		ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae
		dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit
		aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos
		qui ratione voluptatem sequi nesciunt.

			<form action="Controller?action=Quote" method="post" novalidate="novalidate">
				<br>

				<p>Do you want to see a quote?</p>

				<input type="radio" name="choice" value="Yes"
					<c:if test="${quote != null}">
						checked="checked"
					</c:if>>
					Yes

				<input type="radio" name="choice" value="No"
					<c:if test="${quote == null}">
					   checked="checked"
					</c:if>>
					No

				<br/>

				<input type="submit" id="submit" value="Submit">
			</form>

			<p> ${quote} </p>

				<c:if test="${sessionScope.person == null}">
					<form action="Controller?action=Login" method="post" novalidate="novalidate">
						<label for="userid">UserId: </label>
						<input type="text" name="userid" id="userid" placeholder="userid">

						<br>

						<label for="password">Password: </label>
						<input type="password" name="password" id="password">

						<br>

						<input type="submit" id="logIn" value="Log in">
					</form>
				</c:if>

				<br>

				<c:if test="${sessionScope.person != null}">
					<p>Welcome, ${sessionScope.person}.</p>

					<form action="Controller?action=Logout" method="post" novalidate="novalidate">
						<input type="submit" id="logOut" value="Log out">
					</form>
				</c:if>
		</main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>