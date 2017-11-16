function signInValidate() {
    var result = true;
    var log = document.getElementById("login").value;
    var pas = document.getElementById("password").value;
    if (log == "" || pas == "") {
            result = false;
            alert("Fields login and password must be filled!");
    }
    return result;
}