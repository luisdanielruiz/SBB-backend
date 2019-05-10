function actualizarUserAppTelerik(){ 
    dataSourceUserApp.read({accUserApp:"getDataT"});
} 
 
 
 
function nuevoUserApp(){/*tabla es string*/ 
    limpiarUserApp(); 
    $("#fromABMUserApp").val("n"); 
    var win = $("#windowUserApp").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarUserApp(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMUserApp(sTabla); 
        $("#fromABMUserApp").val("e"); 
        $( "#ABMUserApp" ).popup( "open" ); 
    } 
} 
 
function editarUserApp(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMUserApp").val("e"); 
                idUserR.value = entidad.idUser; 
        userNameR.value = entidad.userName; 
        passwordR.value = entidad.password; 
        emailR.value = entidad.email; 
        nameR.value = entidad.name; 
        surnameR.value = entidad.surname; 
        countryR.value = entidad.country; 
        stateR.value = entidad.state; 
        cityR.value = entidad.city; 
        addressR.value = entidad.address; 
        postalCodeR.value = entidad.postalCode; 
        idFacebookR.value = entidad.idFacebook; 
        idGoogleR.value = entidad.idGoogle; 
        profilePictureR.value = entidad.profilePicture; 
        phone_codR.value = entidad.phone_cod; 
        phoneR.value = entidad.phone; 
        statusR.value = entidad.status; 
        axisYR.value = entidad.axisY; 
        axisXR.value = entidad.axisX; 
        locationR.value = entidad.location; 
        mailConfirmadoR.value = entidad.mailConfirmado; 
        dniR.value = entidad.dni; 
        cuilR.value = entidad.cuil; 
        premiumR.value = entidad.premium; 
        fechaRegistroR.value = entidad.fechaRegistro; 
        sesionHistoryR.value = entidad.sesionHistory; 
        statisticsR.value = entidad.statistics; 
 
        var win = $("#windowUserApp").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarUserApp(){ 
        idUserR.value = ''; 
        userNameR.value = ''; 
        passwordR.value = ''; 
        emailR.value = ''; 
        nameR.value = ''; 
        surnameR.value = ''; 
        countryR.value = ''; 
        stateR.value = ''; 
        cityR.value = ''; 
        addressR.value = ''; 
        postalCodeR.value = ''; 
        idFacebookR.value = ''; 
        idGoogleR.value = ''; 
        profilePictureR.value = ''; 
        phone_codR.value = ''; 
        phoneR.value = ''; 
        statusR.value = ''; 
        axisYR.value = ''; 
        axisXR.value = ''; 
        locationR.value = ''; 
        mailConfirmadoR.value = ''; 
        dniR.value = ''; 
        cuilR.value = ''; 
        premiumR.value = ''; 
        fechaRegistroR.value = ''; 
        sesionHistoryR.value = ''; 
        statisticsR.value = ''; 

} 
 
function guardarUserApp(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "UserAppService", 
          data: $( "#ABMUserAppForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarUserAppTelerik();
                var win = $("#windowUserApp").data("kendoWindow");
                win.close();

            }else{ 
                alert(msg); 
            } 
            $( "#cargando" ).css( "display","none" );  
           } 
        });     
    }catch (Exce){
        $( "#cargando" ).css( "display","none" );  
    }
} 
var entidadEliminar = null; 
function eliminarWinUserApp(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarUserApp").data("kendoWindow").center();
        $("#windowEliminarUserApp").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarUserApp(){
    var win = $("#windowEliminarUserApp").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarUserApp(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accUserApp="eliminar"; 
                objElimino.idUser=entidadEliminar.idUser; 

            $.ajax({ 
              type: "POST", 
              url: "UserAppService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarUserAppTelerik();
                    var win = $("#windowEliminarUserApp").data("kendoWindow");
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

