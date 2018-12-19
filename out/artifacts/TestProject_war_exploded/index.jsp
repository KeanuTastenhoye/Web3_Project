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
					<li>
						<a href="Controller?action=NaarAddProduct">Add Product</a>
					</li>
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

		<article>
			<br/>
			<p> Do you want to see a quote? </p>
			<form action="Controller?action=Quote" method="post">
				<input type="radio" name="quote" value="Yes">
				Yes
				<input type="radio" name="quote" value="No">
				No
				<br/>
				<input type="submit" name="quote" value="Submit">
			</form>
		</article>

		<c:if test="${quote == 'Yes'}">
			<div>
				<p>Greatness from small beginnings</p>
			</div>
			</c:if>
		</main>

		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>