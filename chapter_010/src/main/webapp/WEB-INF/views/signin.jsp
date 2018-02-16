<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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
        <form method="post" action="${pageContext.request.contextPath}/signin.do">
            <c:if test="${param.error != null}">
                <p>
                    Invalid user name and password.
                </p>
            </c:if>
            <div class="form-group">
                <label for="login">Login</label>
                <input type="text" id="login" name="username"/>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="passowrd">

            </div>
            <input class="btn btn-default" type="submit" value="Login" name="submit"  required autocomplete="off"/>

        </form>
        <div>
            <button class="btn btn-link" onclick="location.href='newUser.do'">Регистрация</button>
        </div>
    </div>
</body>
</html>
