<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Sign in</title>
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/bootstrap.min.css"/>"/>
    <script type="text/javascript">
        function validate() {
            var result = true;
            var login = document.getElementById("login").value;
            var password = document.getElementById("password").value;
            if (login == "" || password == "") {
                result = false;
                alert("Please enter login and password!");
            }
            return result;
        }
    </script>
</head>
<body>
<div class="container" style="width: 20%;">
<div class="page-header">
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>
</div>
    <div class="page-header">
        <h1>Sign in</h1>
    </div>
    <form onsubmit="return validate();" method="post" action="${pageContext.request.contextPath}/signin">
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" name="login" id="login" class="form-control" placeholder="Enter login" autocomplete="off"/>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" class="form-control" placeholder="Enter password" autocomplete="off"/>
        </div>
        <input type="submit" class="btn btn-default" value="Sing in"/>
    </form>
</div>
</body>
</html>
