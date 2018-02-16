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
        <c:if test="${error != null}">
                ${error}
        </c:if>
        <div class="page-header">
            <h3>Create new user</h3>
        </div>
        <div class="form-control">
            <form method="post" action="${pageContext.request.contextPath}/newUser.do">
                <div class="form-group">
                    <label for="username">User name</label>
                    <input type="text" name="username" id="username"/>
                </div>
                <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password"/>
                </div>
                <div class="form-group">
                    <label for="confirm">Confirm password</label>
                    <input type="password" name="confirm" id="confirm">
                </div>
                <input value="Create new user" type="submit" class="btn btn-default"/>
            </form>
        </div>
    </div>
</body>
</html>
