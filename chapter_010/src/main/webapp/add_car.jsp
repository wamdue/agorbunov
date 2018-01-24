<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add new car.</title>
    <link type="text/css" rel="stylesheet" media="all" href="css/bootstrap.min.css"/>
    <script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        function getString(data, id) {
            var result = "";
            for (var i = 0; i < data.length; i++) {
                result += "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
            }
            var select = document.getElementById(id);
            select.innerHTML = result;
        }

        function fillData(address, name) {
            $.ajax(address, {
                method: 'get',
                dataType: 'json',
                complete: function (data) {
                    var brands = JSON.parse(data.responseText);
                    getString(brands, name);

                }
            });
        }

        function fillAll() {
            fillData('./brands.do', "brand_id");
            fillData('./body.do', "body_id");
            fillData('./engine.do', "engine_id");
            fillData('./gearbox.do', "gearbox_id");
            fillData('./axle.do', "axle_id");
        }

        $(
            $.ajax({
                success: function () {
                    fillAll();
                }
            })
        )

    </script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h3>Add new car</h3>
    </div>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <select id="brand_id">
            </select>
            <select id="body_id">
            </select>
            <select id="gearbox_id">
            </select>
            <select id="engine_id">
            </select>
            <select id="axle_id">
            </select>
            <div class="form-group">
                <label for="car_name">Car name</label>
                <input type="text" id="car_name"/>
            </div>
            <div class="form-group">
                <label for="car_price">Car price</label>
                <input type="text" id="car_price"/>
            </div>
            <div class="form-group">
                <label for="car_description">Description.</label>
                <textarea id="car_description" rows="5"></textarea>
            </div>
            <div class="form-group">
                <input type="file" id="photo" multiple accept="image/*, image/jpeg"/>
            </div>
            <button type="submit" onclick="return save()">Create</button>
        </div>
    </form>
</div>
</body>
</html>