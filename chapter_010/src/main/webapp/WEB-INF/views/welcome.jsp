<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome screen</title>
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/bootstrap.min.css"/>"/>
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
    <script src="<c:url value="/js/bootstrap.min.js"/>" type="text/javascript"></script>
    <script type="text/javascript">

        function drawTable(data) {
            var result = "";
            var cars = JSON.parse(data.responseText);
            for (var i = 0; i < cars.length; i++) {
                result += "<tr>"
                    + "<td>" + cars[i].id + "</td>"
                    + "<td>" + cars[i].brand.name + "</td>"
                    + "<td>" + cars[i].name + "</td>"
                    + "<td>" + cars[i].carCreated + "</td>"
                    + "<td>" + cars[i].engine.name + "</td>"
                    + "<td>" + cars[i].gearbox.name + "</td>"
                    + "<td>" + cars[i].axle.name + "</td>"
                    + "<td>" + cars[i].body.name + "</td>"
                    + "<td>" + cars[i].price + "</td>"
                    + "<td>" + cars[i].description + "</td>"
                    + "<td>";
                var p = cars[i].pics;
                if (p.length != null && p.length > 0) {
                    for (var j = 0; j < p.length; j++) {
                        result += "<img width='150' height='150' src='data:image/jpg;base64," + p[j].path + "'/>";
                    }
                }
                result += "</td>"
                    + "<td>" + new Date(cars[i].post) + "</td>"
                    + "<td><input type='checkbox' ";
                if (cars[i].status > 0) {
                    result += "checked"
                }
                result += " onclick='changeStatus(" + cars[i].id + ")'/></td>"
                    + "</tr>";
            }
            return result;
        }

        function loadCars() {
            $("#list").hide();
            $.ajax('./list.do', {
                method: 'get',
                dataType: 'json',
                complete: function (data) {
                    var result = drawTable(data);
                    var table = document.getElementById("list");
                    table.innerHTML = result;
                    $("#list").show();
                }
            });
        }

        function loadFilter() {
            $("#list").hide();
            $.ajax('./filter.do', {
                method: 'get',
                dataType: 'json',
                data: {
                    brand: document.getElementById("brand").value,
                    pic: document.getElementById("pic").checked,
                    time: document.getElementById("fresh").checked
                },
                complete: function (data) {
                    var result = drawTable(data);
                    var table = document.getElementById("list");
                    table.innerHTML = result;
                    $("#list").show();
                }
            });
        }

        function changeStatus(id) {
            $.ajax('./status.do', {
                method: 'post',
                dataType: 'json',
                data: {id: id},
                complete: function () {
                    loadCars();
                }

            });
        }

        function loadList() {
            $.ajax('./brand.do', {
                method: 'get',
                dataType: 'json',
                complete: function (data) {
                    var result = "";
                    var brands = JSON.parse(data.responseText);
                    result += "<option value='0'>Любая</option>";
                    for (var i = 0; i < brands.length; i++) {
                        result += "<option value='" + brands[i].id + "'>" + brands[i].name + "</option>";
                    }
                    var element = document.getElementById("brand");
                    element.innerHTML = result;
                }
            });
        }

        $(
            $.ajax({
                success: function () {
                    loadList();
                    loadCars();
                }
            })
        );
    </script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <button class="btn btn-default" onclick="location.href='newcar.do'">Добавить объявление</button>
    </div>
    <div>
        <form method="get" class="form-group">
            <select id="brand">
            </select>
            <label for="pic">Только с картинками</label>
            <input type="checkbox" id="pic"/>
            <label for="fresh">За сегодня</label>
            <input type="checkbox" id="fresh">
            <input type="button" class="btn btn-default" onclick="loadFilter()" value="Filter">
        </form>
    </div>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Brand</th>
                <th>Name</th>
                <th>Production date</th>
                <th>Engine</th>
                <th>Gearbox</th>
                <th>Axle</th>
                <th>Body</th>
                <th>Price</th>
                <th>Description</th>
                <th>Picture</th>
                <th>Posting date</th>
                <th>Sold status</th>
            </tr>
            </thead>
            <tbody id="list">
            </tbody>
        </table>
    </div>
</div>
</body>
</html>