<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="page-header">
    <c:if test="${error != ''}">
        <div style="background-color: red">
            <c:out value="${error}"/>
        </div>
    </c:if>
    </div>
    <div>
    <div class="page-header">
        <h1>Login page</h1>
    </div>
    <br>
    <br>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="login">User name</label>
            <input type="text" id="login" name="login"/>
        </div>
        <input type="submit" class="btn btn-default" name="checkIn" value="Login" autocomplete="off" required>
    </form>
    </div>
</div>
</body>
</html>
