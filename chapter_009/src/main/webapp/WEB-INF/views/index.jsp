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
    <script type="text/javascript">

        function validate() {
            var result = true;
            var name = document.getElementById("name").value;
            var login = document.getElementById("login").value;
            var password = document.getElementById("password").value;
            var email = document.getElementById("email").value;
            var city = document.getElementById("city").value;
            var country = document.getElementById("country").value;
            if (name == "" || login == "" || password =="" || email =="" || city == "" || country =="") {
                result = false;
                alert("All fields must be entered!");
            }
            return result;
        }

        function deleteUser(userId) {
            $.ajax("./deleteuser", {
                method: 'post',
                async: false,
                data: {
                    id : userId
                },
                success: function(data) {
                    fillDiv();
                }
            });
            return false;
        }

        function showUpdate(user) {
            $("#newName").value = user.name;
            $("#newLogin").value = user.login;
            $("#newPassword").value = user.password;
            $("#newEmail").value = user.email;
            $("#newCity").value = user.city;
            $("#newCountry").value = user.country;
            $("#newRole").value = user.role;
            $("#newId").value = user.id;
            var role = "${sessionScope.get("role")}";
            if (role === '') {
                var el = $("#newRole").parent();
                el.hide();
            }
            $("#update").toggle();
//            document.getElementById("newName").value = user.name;
//            document.getElementById("newLogin").value = user.login;
//            document.getElementById("newPassword").value = user.password;
//            document.getElementById("newEmail").value = user.email;
//            document.getElementById("newCity").value = user.city;
//            document.getElementById("newCountry").value = user.country;
//            document.getElementById("newRole").value = user.role;
//            document.getElementById("newId").value = user.id;
            return false;
        }

        function fillDiv() {
            $("#users").hide('fast');
            $.ajax('./json', {
                method: 'get',
                dataType: 'json',
                complete: function(data) {
                    var users = JSON.parse(data.responseText);
                    var result ="";
                    for (var i = 0; i < users.length; i++) {
                        result += "<tr class='success'>" +
                            "<td>" + users[i].id + "</td>\n" +
                            "<td>" + users[i].name + "</td>\n" +
                            "<td>" + users[i].login + "</td>\n" +
                            "<td>" + users[i].email + "</td>\n" +
                            "<td>" + users[i].city + "</td>\n" +
                            "<td>" + users[i].country + "</td>\n" +
                            "<td>" + users[i].role + "</td>\n" +
                            "<td>" + users[i].createDate + "</td>\n";
                        var role = "${sessionScope.get("role")}";
                        var id = "${sessionScope.get("id")}";
                        if (role !== '' || (id !== '' && id === users[i].id)) {
                            result += "<td>" +
//                                "<form method='post' id='actions'>" +
                                "<button class='btn btn-default' id='edit' name='id' value='" + users[i].id + "' onclick='return showUpdate("+ users[i] +")'>edit</button>" +
                                "<button class='btn btn-default' id='delete' name='id' value='" + users[i].id + "' onclick='return deleteUser("+ users[i].id +")'>delete</button>" +
//                                "</form>" +
                                "</td>";
                        }
                        result += "</tr>";
                    }
                    var table = document.getElementById("users");
                    table.innerHTML = result;
                    $("#users").show('normal');
                }
            })

        }

        $(
            $.ajax({
                complete: function(data) {
                    fillDiv();
                    $("#create").hide();
                    $("#update").hide();
                }
                })
        );

        $(function() {
           $("#newUser").click(function () {
               var el = $("#create");
               el.toggle();

           })
            $("#cancelNew").click(function () {
               $("#create").hide();
           })
            $("#updateCancel").click(function () {
                $("#update").hide();
            })
        });



        function createUser() {
            $.ajax("./json", {
                method: 'post',
                async: false,
                data: {
                    name: document.getElementById("name").value,
                    login: document.getElementById("login").value,
                    password: document.getElementById("password").value,
                    email: document.getElementById("email").value,
                    city: document.getElementById("city").value,
                    country: document.getElementById("country").value,
                    role: document.getElementById("role").value
                },
                complete: function(data) {
                    fillDiv();
                    $("#create").trigger('reset');
                    $("#create").hide();
                }
            });

            return false;
        }

        function updateUser() {
            $.ajax("./updateuser", {
                method: 'post',
                async: false,
                data: {
                    newname: document.getElementById("newName").value,
                    newlogin: document.getElementById("newLogin").value,
                    newpassword: document.getElementById("newPassword").value,
                    newemail: document.getElementById("newEmail").value,
                    newcity: document.getElementById("newCity").value,
                    newcountry: document.getElementById("newCountry").value,
                    newrole: document.getElementById("newRole").value,
                    id: document.getElementById("newId").value
                },
                complete: function(data) {
                    $("#update").hide();
                    fillDiv();
                }
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
            <tbody id="users">
            </tbody>
        </table>
        <a class="btn btn-info" id="newUser">Add new user</a>
    </div>
    <%--Add user gui begin.--%>
    <div id="create" style="width: 20%;">
        <div class="page-header">
            <h3>Add new user.</h3>
        </div>
        <form method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" autocomplete="off" required>
            </div>
            <div class="form-group">
                <label for="login">Login:</label>
                <input type="text" class="form-control" id="login" name="login" autocomplete="off" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" autocomplete="off" required>
            </div>
            <div class="form-group">
                <label for="email">Email:</label>
                <input type="text" class="form-control" id="email" name="email"
                       pattern="^([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z0-9\.]{2,6})$" title="Must contain @ and ." required value="">
            </div>
            <div class="form-group">
                <label for="city">City:</label>
                <input type="text" class="form-control" id="city" name="city" pattern="[A-Za-z\s]+"
                       title="Must contain only letters." required value="">
            </div>
            <div class="form-group">
                <label for="country">Country:</label>
                <input type="text" class="form-control" id="country" name="country" pattern="[A-Za-z\s]+"
                       title="Must contain only letters." required value="">
            </div>
            <div class="form-group">
                <label for="role">Role:</label>
                <select class="form-control" id="role" name="role">
                    <option value="ADMIN">Administrator</option>
                    <option value="USER">User</option>
                </select>
            </div>
            <input class="btn btn-default" type="button" value="Add new user" onclick="return validate() && createUser();">
            <input class="btn btn-default" type="button" value="Cancel" id="cancelNew">
        </form>
    </div>
    <%--Add user gui end--%>

    <%--Update user gui begin--%>
    <div id="update" style="width: 20%">
        <div class="page-header">
            <h2>Update User</h2>
        </div>
        <form method="post">
            <div class="form-group">
                <label for="newName">Name:</label>
                <input type="text" class="form-control" id="newName" name="newName" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="newLogin">Login:</label>
                <input type="text" class="form-control" id="newLogin" name="newLogin" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="newPassword">Password:</label>
                <input type="password" class="form-control" id="newPassword" name="newPassword" autocomplete="off">
            </div>
            <div class="form-group">
                <label for="newEmail">Email:</label>
                <input type="text" class="form-control" id="newEmail" name="newEmail"
                       pattern="^([a-z0-9_\.-]+)@([a-z0-9_\.-]+)\.([a-z0-9\.]{2,6})$" title="Must contain @ and .">
            </div>
            <div class="form-group">
                <label for="newCity">City:</label>
                <input type="text" class="form-control" id="newCity" name="newCity" pattern="[A-Za-z\s]+"
                       title="Must contain only letters.">
            </div>
            <div class="form-group">
                <label for="newCountry">Country:</label>
                <input type="text" class="form-control" id="newCountry" name="newCountry" pattern="[A-Za-z\s]+"
                       title="Must contain only letters.">
            </div>
            <c:if test="${sessionScope.get('id') == null}">
                <div class="form-group">
                    <label for="newRole">newRole:</label>
                    <select class="form-control" id="newRole" name="newRole">
                        <option value="ADMIN">Administrator</option>
                        <option value="USER">User</option>
                    </select>
                </div>
            </c:if>
            <input type="text" id="newId" name="id" hidden/>
            <button class="btn btn-default" type="button" id="update" onclick="return updateUser()">Update user</button>
            <button class="btn btn-default" type="button" id="updateCancel">Cancel</button>
        </form>
    </div>
    <%--Update user gui end--%>
</div>
</body>
</html>
