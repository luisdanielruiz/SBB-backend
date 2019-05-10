function actualizarUsuarioTelerik(){ 
    dataSourceUsuario.read({accUsuario:"getDataT"});
} 
 
 
 
function nuevoUsuario(){/*tabla es string*/ 
    limpiarUsuario(); 
    $("#fromABMUsuario").val("n"); 
    //hago editable el id
    $("#userR").removeAttr("readonly");
    var win = $("#windowUsuario").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarUsuario(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMUsuario(sTabla); 
        $("#fromABMUsuario").val("e"); 
        $( "#ABMUsuario" ).popup( "open" ); 
    } 
} 
 
function editarUsuario(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMUsuario").val("e"); 
        //hago no editable el id
        $("#userR").attr("readonly", "userR");
        
        userR.value = entidad.user; 
        passwordR.value = entidad.password; 
        mailR.value = entidad.mail; 
        idPerfilR.value = entidad.idPerfil; 
        estadoR.value = entidad.estado; 
        recibeMailR.value = entidad.recibeMail; 
        codigoTabletR.value = entidad.codigoTablet; 
 
        var win = $("#windowUsuario").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarUsuario(){ 
        userR.value = ''; 
        passwordR.value = ''; 
        mailR.value = ''; 
        idPerfilR.value = ''; 
        estadoR.value = ''; 
        recibeMailR.value = ''; 
        codigoTabletR.value = ''; 

} 
 
function guardarUsuario(){
    
    var validado = true;
    var a = $("#estadoR").val().valueOf();
    var b = $("#mailR").val();
    //valido el estado
    if (a > 1 || a < 0 || isNaN(a)) {
        alert("estado incorrecto: debe ser 1 o 0 ");
        validado = false;
    }
    //valido el mail
    if (validarEmail(b) === false) {
        validado = false;
    }
    try{
         if (validado === true) {
             
         
            $( "#cargando" ).css( "display","block" );  


            $.ajax({
              type: "POST", 
              url: "UsuarioService", 
              data: $( "#ABMUsuarioForm" ).serialize() 
            }) 
            .done(function( msg ) {         
              if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
              }else{           
                if (msg.indexOf('OK#1231321')!=-1){ 
                    notification.info("Guardado");
                    actualizarUsuarioTelerik();
                    var win = $("#windowUsuario").data("kendoWindow");
                    win.close();

                }else{ 
                    alert(msg); 
                } 
                $( "#cargando" ).css( "display","none" );  
               } 
            });     
        }
    }catch (Exce){
        $( "#cargando" ).css( "display","none" );  
    }
}
 
var entidadEliminar = null; 
function eliminarWinUsuario(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarUsuario").data("kendoWindow").center();
        $("#windowEliminarUsuario").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarUsuario(){
    var win = $("#windowEliminarUsuario").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarUsuario(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accUsuario="eliminar"; 
                objElimino.user=entidadEliminar.user; 

            $.ajax({ 
              type: "POST", 
              url: "UsuarioService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarUsuarioTelerik();
                    var win = $("#windowEliminarUsuario").data("kendoWindow");
                    win.close();
                }else{ 
                    alert(msg); 
                }
                $( "#cargando" ).css( "display","none" );  
              }
            }); 
        }
    }catch (Exce){
        $( "#cargando" ).css( "display","none" );  
    }
}
function validarEmail(email) {
    expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    if (!expr.test(email)) {
        alert("Error: La dirección de correo " + email + " es incorrecta.");
        return false;
    }
}


