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
        <input type="text" name="id" hidden value="${user.id}"/>
        <input type="submit" value="Update  user">
    </form>
</body>
</html>
