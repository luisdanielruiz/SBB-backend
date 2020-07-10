
function loginFun() {
    $.ajax({
        type: "POST",
        url: "UsuarioService",
        data: $("#LoginForm").serialize(),
    })
            .done(function (msg) {
                if (msg.indexOf('-1ER') != -1 || msg.indexOf('-2ER') != -1) {

                    $("#notification").empty();

                    var $newdiv1 = $(
                            "<div class=\"alert alert-danger\">" +
                            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>" +
                            "<strong>¡Error!</strong> Los datos ingresados son incorrectos." +
                            "</div>"
                            );

                    $("#notification").append($newdiv1);
                    alert('Datos invalidos, intente nuevamente.')
                }
                if (msg.indexOf('-2ER') != -1) {

                    $("#notification").empty();

                    var $newdiv1 = $(
                            "<div class=\"alert alert-danger\">" +
                            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>" +
                            "<strong>¡Error!</strong> Usuario bloqueado." +
                            "</div>"
                            );

                    $("#notification").append($newdiv1);
                    alert('Datos invalidos, Usuario bloqueado.')

                } else if (msg.indexOf('1OK') != -1) {
                    window.location.href = "appClean.jsp";
                    console.log("ingreso ok");
                }

            });
}

function loginFunWin() {
    $.ajax({
        type: "POST",
        url: "UsuarioService",
        data: $("#LoginForm").serialize()
    })
            .done(function (msg) {
                if (msg.indexOf('-1ER') != -1 || msg.indexOf('-2ER') != -1) {

                    $("#notification").empty();

                    var $newdiv1 = $(
                            "<div class=\"alert alert-danger\">" +
                            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>" +
                            "<strong>¡Error!</strong> Los datos ingresados son incorrectos." +
                            "</div>"
                            );

                    $("#notification").append($newdiv1);

                }
                if (msg.indexOf('-2ER') != -1) {

                    $("#notification").empty();

                    var $newdiv1 = $(
                            "<div class=\"alert alert-danger\">" +
                            "<button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>" +
                            "<strong>¡Error!</strong> Usuario bloqueado." +
                            "</div>"
                            );

                    $("#notification").append($newdiv1);

                } else if (msg.indexOf('1OK') != -1) {
                    $("#loginWin").popup("close");

                }

            });
}
 