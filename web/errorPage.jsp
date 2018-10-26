<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Something wrong</title>
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
                </ul>
            </nav>
            <h2>Oh dear!</h2>
        </header>
        <main>
            <p>You caused a ${pageContext.exception.message} on the server!</p>
        </main>
        <footer>&copy; Webontwikkeling 3, UC Leuven-Limburg</footer>
    </div>
</body>
</html>