<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"
          integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.2.1.js"
            integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
            crossorigin="anonymous"></script>
    <script>
        $(
            $.ajax('./json', {
                method: 'get',
                complete: function (data) {
                    var result = "<thead>\n" +
                        "            <tr class=\"active\">\n" +
                        "                <th>User id</th>\n" +
                        "                <th>User name</th>\n" +
                        "                <th>Login</th>\n" +
                        "                <th>Email</th>\n" +
                        "                <th>City</th>\n" +
                        "                <th>Country</th>\n" +
                        "                <th>Role</th>\n" +
                        "                <th>Creation time</th>\n" +
                        "                <th>Actions</th>\n" +
                        "            </tr>\n" +
                        "            </thead>\n";
                    var users = JSON.parse(data.responseText);

                    result += "<tbody>";
                    for (var i = 0; i < users.length; i++) {
                        result += "<tr class=\"success\"> +" +
                            "<td>" + users[i].id + "</td>\n" +
                            "<td>" + users[i].name + "</td>\n" +
                            "<td>" + users[i].login + "</td>\n" +
                            "<td>" + users[i].password + "</td>\n" +
                            "<td>" + users[i].email + "</td>\n" +
                            "<td>" + users[i].city + "</td>\n" +
                            "<td>" + users[i].country + "</td>\n" +
                            "<td>" + users[i].role + "</td>\n" +
                            "<td> actions </td>";
                        result += "</tr>";
                    }
                    result += "</tbody>";
                    var table = document.getElementById("users");
                    table.innerHTML = result;
                }

            })
        );

        function createUser() {
            $.ajax( {
                method : 'post',
                success : null
                });
            return false;
        }

        function updateUser() {
            $.ajax( {
                method : "post",
                success : null
            });
            return false;
        }

        function deleteUser() {
            $.ajax( {

            });
            return false;
        }
    </script>
</head>
<body>
<div class="container">
    <div class="row" style="width: 113%;">
        <div class="col-xs-10 col-md-10"></div>
        <div class="col-xs-1 col-md-1">
            <a href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </div>
    <div class="page-header">
        <h3>List of users.</h3>
    </div>
    <div class="table-responsive">
        <table class="table" id="users">
        </table>
        <a class="btn btn-info" href="${pageContext.request.contextPath}/adduser" onclick="createUser()">Add new user</a>
    </div>
    <div id="create">

    </div>
</div>
</body>
</html>
