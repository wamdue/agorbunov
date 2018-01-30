<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Sign in.</title>
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/bootstrap.min.css"/>"/>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <h2>Sign in</h2>
        </div>
        <form method="post" action="${pageContext.request.contextPath}/signin">
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" id="login" name="login"/>
            </div>
            <input class="btn btn-default" type="submit" value="Sign in"  required autocomplete="off"/>
        </form>
    </div>
</body>
</html>
