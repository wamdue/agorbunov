<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/bootstrap.min.css"/>"/>
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
