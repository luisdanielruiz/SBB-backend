
function loginFun() {
    $.ajax({
        type: "POST",
        url: "UsuarioService",
        data: $("#LoginForm").serialize(),
    }).done(function (msg) {
        if (msg.indexOf('1OK') != -1) {
            window.location.href = "appClean.jsp";
            console.log("ingreso ok");
        } else {
            alert('Datos invalidos, intente nuevamente.');
        }
    });
}
