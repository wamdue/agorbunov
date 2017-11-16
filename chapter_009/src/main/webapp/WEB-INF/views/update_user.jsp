<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Update user.</h3>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/updateuser">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" class="form-control" id="name" name="newname" autocomplete="off" value="${user.name}">
        </div>
        <div class="form-group">
            <label for="login">Login:</label>
            <input type="text" class="form-control" id="login" name="newlogin" autocomplete="off" value="${user.login}">
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="newpassword" autocomplete="off" value="${user.password}">
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="text" class="form-control" id="email" name="newemail"
                   pattern="^([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z0-9\.]{2,6})$" title="Must contain @ and ." value="${user.email}">
        </div>
        <div class="form-group">
            <label for="city">City:</label>
            <input type="text" class="form-control" id="city" name="newcity" pattern="[A-Za-z\s]+"
                   title="Must contain only letters." value="${user.city}">
        </div>
        <div class="form-group">
            <label for="country">Country:</label>
            <input type="text" class="form-control" id="country" name="newcountry" pattern="[A-Za-z\s]+"
                   title="Must contain only letters." value="${user.country}">
        </div>
        <c:if test="${sessionScope.get('id') == null}">
            <div class="form-group">
                <label for="role">Role:</label>
                <select class="form-control" id="role" name="newrole">
                    <option c:if test="${user.role.equals('ADMIN')}" selected c:if value="ADMIN">Administrator</option>
                    <option c:if test="${user.role.equals('USER')}" selected c:if value="USER">User</option>
                </select>
            </div>
        </c:if>
        <input type="text" name="id" hidden value="${user.id}"/>
        <input class="btn btn-default" type="submit" value="Update user">
    </form>
</div>
</body>
</html>
