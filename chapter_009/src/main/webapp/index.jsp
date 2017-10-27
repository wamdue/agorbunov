<%@ page import="ru.job4j.crud.DBConnection" %>
<%@ page import="ru.job4j.crud.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
    <a href="add_user.jsp">Add new user</a>
    <table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
        <tr>
            <th>User id</th>
            <th>User name </th>
            <th>Login</th>
            <th>Email</th>
            <th>Creation time</th>
            <th>Actions</th>
        </tr>
        <%for (User user : DBConnection.getInstance().listOfUsers()) {%>
            <tr>
                <td><%=user.getId()%></td>
                <td><%=user.getName()%></td>
                <td><%=user.getLogin()%></td>
                <td><%=user.getEmail()%></td>
                <td><%=user.getCreateDate()%></td>
                <td>
                    <form action="<%=request.getContextPath()%>/update_user.jsp" method="post">
                        <button value="<%=user.getId()%>" name="id">Edit</button>
                    </form>
                    <form action="<%=request.getContextPath()%>/deleteuser" method="post">
                        <button value="<%=user.getId()%>" name="id">Delete</button>
                    </form>
                </td>
            </tr>
        <%}%>
    </table>
</body>
</html>
