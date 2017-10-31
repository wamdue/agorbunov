<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/adduser" method="post">
        Name <input type="text" name="name"/>
        <br>
        Login <input type="text" name="login"/>
        <br>
        Email <input type="text" name="email"/>
        <br>
        <input type="submit" value="Add new user">
    </form>
</body>
</html>
