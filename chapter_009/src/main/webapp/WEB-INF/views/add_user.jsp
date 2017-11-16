<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
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
    </script>
</head>
<body>
<div class="container" style="width: 30%;">
    <div class="page-header">
        <h3>Add new user.</h3>
    </div>
    <form action="${pageContext.request.contextPath}/adduser" method="post" onsubmit="return validate()">
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
        <input class="btn btn-default" type="submit" value="Add new user">
    </form>
</div>
</body>
</html>
