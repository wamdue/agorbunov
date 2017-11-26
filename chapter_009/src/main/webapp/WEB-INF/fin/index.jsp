<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"--%>
          <%--integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">--%>
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="${pageContext.request.contextPath}/css/bootstrap.min.css"/>"/>
    <script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js" type="text/javascript"> </script>
    <%--<script--%>
            <%--src="https://code.jquery.com/jquery-3.2.1.js"--%>
            <%--integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="--%>
            <%--crossorigin="anonymous"></script>--%>

    <script type="text/javascript">

        function drawTable(users) {
            var result = '';
            for (var i = 0; i < users.length; i++) {
                result += "<tr class='success'>\n" +
                    "<td>" + users[i].id + "</td>\n" +
                    "<td>" + users[i].name + "</td>\n" +
                    "<td>" + users[i].address.address + "</td>\n" +
                    "<td>";
                var types = users[i].musicTypes;
                for (var x = 0; x < types.length; x++) {
                    result += types[x].name;
                    if (x < (types.length - 1)) {
                        result += ", ";
                    }
                }
                result += "</td>\n <td>";
                var role = users[i].roles;
                for (var y = 0; y < role.length; y++) {
                    result += role[y].name;
                    if (x < (role.length - 1)) {
                        result += ", ";
                    }
                }
                result += "</td>\n";
                result += "</tr>\n";
            }
            return result;
        }

        function fillUsers() {
            $("#users").hide();
            $.ajax('./json_users.do', {
                method: 'get',
                dataType: 'json',
                complete: function (data) {
                    var users = JSON.parse(data.responseText);
                    var result = drawTable(users);
                    var table = document.getElementById("users");
                    table.innerHTML = result;
                    $("#users").show('fast');
                }
            })
        }

        function fillMusicTypes() {
            $.ajax('./json_types.do', {
                method: 'get',
                dataType: 'json',
                complete: function (data) {
                    var types = JSON.parse(data.responseText);
                    var text = '';
                    for (var i = 0; i < types.length; i++) {
                        text += '<input type="checkbox" name="type" value=' + types[i].id +
                            '>' + types[i].name + '<br>';
                    }
                    var div = document.getElementById("types");
                    div.innerHTML = text;
                }
            })
        }

        function fillRoles() {
            $.ajax('./json_roles.do', {
                method: 'get',
                dataType: 'json',
                complete: function (data) {
                    var roles = JSON.parse(data.responseText);
                    var text = '';
                    for (var i = 0; i < roles.length; i++) {
                        text += '<input type="checkbox" name="role" value=' + roles[i].id +
                            '>' + roles[i].name + '<br>';
                    }
                    var div = document.getElementById("roles");
                    div.innerHTML = text;

                }
            })
        }

        $(
            $.ajax({
                complete: function () {
                    fillUsers();
                }
            })
        );

        $(function () {
            $("#add").click(function () {
                fillMusicTypes();
                fillRoles();
                $("#list").hide();
                var element = $("#create");
                element.removeAttr("hidden");
                element.show();
            })
            $("#srch").click(function () {
                searchResult($("#search").val());
            })

        })

        function showUsers() {
            var element = $("#create");
//            element.attr("hidden");
            element.hide();
            fillUsers();
            $("#list").show();

            return false;
        }

        function createUser() {
            var types = document.getElementsByName("type");
            var arr = [];
            console.log(types.length);
            for (var i = 0; i < types.length; i++) {
                if (types[i].checked) {
                    arr.push(types[i].value);
                    console.log(types[i].value);
                }
            }

            var roles = document.getElementsByName("role");
            var arrRole = [];
            for (var i = 0; i < roles.length; i++) {
                if (roles[i].checked) {
                    arrRole.push(roles[i].value)
                }
            }

            var data = {};
            $.ajax("./createUser", {
                method: 'post',
                async: false,
                data: {
                    name: document.getElementById("name").value,
                    address: document.getElementById("address").value,
                    musicTypes: arr,
                    roles: arrRole
                },
                complete: function () {
                    showUsers();
                }
            });
            return false;
        }

        function searchResult(data) {
            if (data === undefined || data === '') {
                fillUsers();
            } else {
                var table = $("#users");
                table.hide();
                $.ajax("./json_search.do", {
                    method: 'get',
                    async: false,
                    dataType: 'json',
                    data: {
                        searchType : $("#searchType").val(),
                        search : $("#search").val()
                    },
                    complete: function (data) {
                        var users = JSON.parse(data);
                        var result = drawTable(users);
                        table.innerHTML = result;
                        table.show();
                    }
                });
            }
            return false;
        }


    </script>
</head>
<body>
<div class="container" id="list">
    <div class="page-header">
        <h3>List of users</h3>
    </div>
    <div class="input-group">
        <form method="post">
            <input type="text" id="search" name="search">
            <select name="searchType" id="searchType">
                <option value="address">Address</option>
                <option value="role">Role</option>
                <option value="type">Music type</option>
            </select>
            <button class="btn btn-default" type="submit" id="srch">Search</button>
        </form>
    </div>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr class="active">
                <th>Id</th>
                <th>Name</th>
                <th>Address</th>
                <th>Music types</th>
                <th>Roles</th>
            </tr>
            </thead>
            <tbody id="users">
            </tbody>
        </table>
        <button class="btn btn-default" id="add">Add new user</button>
    </div>
</div>
<div class="container" id="create" style="width: 30%" hidden>
    <div class="page-header">
        <h3>Add new user.</h3>
    </div>
    <form method="post">
        <div class="form-group">
            <label for="name">User name</label>
            <input type="text" class="form-control" id="name" name="name" autocomplete="off" required>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <input type="text" class="form-control" id="address" name="address" autocomplete="off" required>
        </div>
        Music types:
        <div class="form-group" id="types">
        </div>
        Roles:
        <div class="form-group" id="roles">
        </div>
        <button class="btn btn-default" type="submit" id="addUser" value="Create user" onclick="return createUser()">
            Create user
        </button>
        <button class="btn btn-default" type="button" value="Cancel" onclick="return showUsers()">Cancel</button>
    </form>
</div>
</body>
</html>
