<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
<div class="container">
    <div class="row" style="width: 113%;">
        <div class="col-xs-10 col-md-10"></div>
        <div class="col-xs-1 col-md-1">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <input type="button" class="btn btn-danger" value="Logout"></form>
            </form>
        </div>
    </div>
    <div class="page-header">
        <h3>List of users.</h3>
    </div>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr class="active">
                <th>User id</th>
                <th>User name</th>
                <th>Login</th>
                <th>Email</th>
                <th>City</th>
                <th>Country</th>
                <th>Role</th>
                <th>Creation time</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <c:choose>
                    <c:when test="${(sessionScope.get('role') != null)}">
                        <tr class="success">
                    </c:when>
                    <c:otherwise>
                        <tr>
                    </c:otherwise>
                </c:choose>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.city}"/></td>
                    <td><c:out value="${user.country}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td><c:out value="${user.createDate}"/></td>
                    <td>
                        <c:if test="${(sessionScope.get('role') != null) ||
                        ((sessionScope.get('id') != null) && (sessionScope.get('id').equals(user.id)))}">
                            <form action="${pageContext.request.contextPath}/updateuser" method="get">
                                <button value="${user.id}" name="id">Edit</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/deleteuser" method="post">
                                <button value="${user.id}" name="id">Delete</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a class="btn btn-info" href="${pageContext.request.contextPath}/adduser">Add new user</a>

    </div>
</div>
</body>
</html>
