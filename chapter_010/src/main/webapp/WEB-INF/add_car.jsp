<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=utf-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add new car.</title>
    <link type="text/css" rel="stylesheet" media="all" href="<c:url value="/css/bootstrap-theme.min.css"/>"/>
    <script src="<c:url value="/js/jquery-3.2.1.min.js"/>" type="text/javascript"></script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Add new car</h3>
    </div>
    <form method="post" action="${pageContext.request.contextPath}/newcar" enctype="multipart/form-data">
        <div class="form-group">
            <select name="brand_id" id="brand_id" title="Brand">
                <c:forEach items="${brands}" var="brand">
                    <option value="${brand.id}"><c:out value="${brand.name}"/></option>
                </c:forEach>
            </select>
            <select name="body_id" title="Body">
                <c:forEach items="${bodies}" var="body">
                    <option value="${body.id}"><c:out value="${body.name}"/></option>
                </c:forEach>
            </select>
            <select name="gearbox_id" title="Gearbox">
                <c:forEach items="${gearboxes}" var="gearbox">
                    <option value="${gearbox.id}"><c:out value="${gearbox.name}"/></option>
                </c:forEach>
            </select>
            <select name="engine_id" title="Engine type">
                <c:forEach items="${engines}" var="engine">
                    <option value="${engine.id}"><c:out value="${engine.name}"/></option>
                </c:forEach>
            </select>
            <select name="axle_id" title="Axle">
                <c:forEach items="${axles}" var="axle">
                    <option value="${axle.id}"><c:out value="${axle.name}"/></option>
                </c:forEach>
            </select>
            <div class="form-group">
                <label for="car_name">Car name</label>
                <input type="text" id="car_name" name="car_name" required/>
            </div>
            <div class="form-group">
                <label for="car_date">Year of production</label>
                <input type="text" id="car_date" name="car_date" required/>
            </div>
            <div class="form-group">
                <label for="car_price">Car price</label>
                <input type="text" id="car_price" name="car_price" required/>
            </div>
            <div class="form-group">
                <label for="car_description">Description.</label>
                <textarea id="car_description" rows="5" name="car_description"></textarea>
            </div>
            <div class="form-group">
                <input type="file" id="photo" multiple accept="image/*, image/jpeg" name="photo"/>
            </div>
            <input class="btn btn-default" type="submit">Create</input>
        </div>
    </form>
</div>
</body>
</html>