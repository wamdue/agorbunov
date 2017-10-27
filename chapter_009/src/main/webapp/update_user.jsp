<%@ page import="ru.job4j.crud.DBConnection" %>
<%@ page import="ru.job4j.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <%User user = DBConnection.getInstance().getUserById(Integer.valueOf(request.getParameter("id")));%>
    <form method="post" action="<%=request.getContextPath()%>/updateuser">
        Name <input type="text" name="newname" value="<%=user.getName()%>"/>
        <br>
        Login<input type="text" name="newlogin" value="<%=user.getLogin()%>"/>
        <br>
        Email<input type="text" name="newemail" value="<%=user.getEmail()%>"/>
        <br>
        <input type="text" name="id" hidden value="<%=user.getId()%>"/>
        <input type="submit" value="Update  user">
    </form>
</body>
</html>
