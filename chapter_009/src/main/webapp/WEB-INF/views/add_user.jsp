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
    Password <input type="text" name="password"/>
    <br>
    Role <select name="role" size="1">
    <option value="Administrator">ADMIN</option>
    <option value="User">USER</option>
    </select>
    <br>
    <input type="submit" value="Add new user">
</form>
</body>
</html>
