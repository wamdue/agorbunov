<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/updateuser">
        Name <input type="text" name="newname" value="${user.name}"/>
        <br>
        Login <input type="text" name="newlogin" value="${user.login}"/>
        <br>
        Email <input type="text" name="newemail" value="${user.email}"/>
        <br>
        Password <input type="password" name="newpassword" value="${user.password}"/>
        <br>
        <c:if test="${sessionScope.get('id') == null}">
            Role <select name="newrole">
            <option c:if test="${user.role.equals('ADMIN')}" selected c:if value="ADMIN">Administrator</option>
            <option c:if test="${user.role.equals('USER')}" selected c:if value="USER">User</option>
        </select>
            <br>
        </c:if>
        <input type="text" name="id" hidden value="${user.id}"/>
        <input type="submit" value="Update  user">
    </form>
</body>
</html>
