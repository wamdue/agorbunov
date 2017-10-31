<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/adduser">Add new user</a>
    <table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>User id</th>
            <th>User name </th>
            <th>Login</th>
            <th>Email</th>
            <th>Creation time</th>
            <th>Actions</th>
        </tr>
            <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/> </td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.createDate}"/></td>
                <td>
                    <form action="${pageContext.request.contextPath}/updateuser" method="get">
                        <button value="${user.id}" name="id">Edit</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/deleteuser" method="post">
                        <button value="${user.id}" name="id">Delete</button>
                    </form>
                </td>
            </tr>
            </c:forEach>
    </table>
</body>
</html>
