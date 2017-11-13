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
                            "<td>" + users[i].role + "</td>\n";
                        var role = "${sessionScope.get("role")}";
                        var id = "${sessionScope.get("id")}";
                        console.log("role : " + role);
                        console.log("id : " + id);
                        if (role !== '' || (id !== '' && id ===users[i].id) ){
                            result += "<td> " +
                                    "<form method='post' id='actions'>" +
                                    "<button class='btn btn-default' id='edit' name='id' value='" +users[i].id +"' onclick='return updateUser();'>edit</button>" +
                                    "<button class='btn btn-default' id='delete 'name='id' value='" +users[i].id +"' onclick='deleteUser()'>delete</button>"+
                                    "</form>" +
                                " </td>";
                        }
                        result += "</tr>";
                    }
                    result += "</tbody>";
                    var table = document.getElementById("users");
                    table.innerHTML = result;
                }

            })
        );

        function createUser() {
            $.ajax("./json", {
                method: 'post',
                data: {
                    name: document.getElementById("name").value,
                    login: document.getElementById("login").value,
                    password: document.getElementById("password").value,
                    email: document.getElementById("email").value,
                    city: document.getElementById("city").value,
                    country: document.getElementById("country").value,
                    role: document.getElementById("role").value
                }
            });
            return false;
        }

        function updateUser() {
            $.ajax({
                method: "post",
                data: {
                    newname: document.getElementById("newname").value,
                    newlogin: document.getElementById("newlogin").value,
                    newpassword: document.getElementById("newpassword").value,
                    newemail: document.getElementById("newemail").value,
                    newcity: document.getElementById("newcity").value,
                    newcountry: document.getElementById("newcountry").value,
                    newrole: document.getElementById("newrole").value
                }
            });
            return false;
        }

        function deleteUser() {
            $.ajax({});
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
        <a class="btn btn-info" id="newUser">Add new user</a>
    </div>
    <div id="create">
        <div class="page-header">
            <h3>Add new user.</h3>
        </div>
        <%--<form action="${pageContext.request.contextPath}/adduser" method="post" onchange="return validate()">--%>\\
        <form method="post" onchange="validate()">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="login">Login:</label>
                <input type="text" class="form-control" id="login" name="login" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" name="email"
                       pattern="^([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z0-9\.]{2,6})$" title="Must contain @ and .">
            </div>
            <div class="form-group">
                <label for="city">City:</label>
                <input type="text" class="form-control" id="city" name="city" pattern="[A-Za-z\s]+"
                       title="Must contain only letters.">
            </div>
            <div class="form-group">
                <label for="country">Country:</label>
                <input type="text" class="form-control" id="country" name="country" pattern="[A-Za-z\s]+"
                       title="Must contain only letters.">
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select class="form-control" id="role" name="role">
                    <option value="ADMIN">Administrator</option>
                    <option value="USER">User</option>
                </select>
            </div>
            <input class="btn btn-default" type="button" value="Add new user" onclick="return createUser();">
        </form>
    </div>
</div>
</body>
</html>
