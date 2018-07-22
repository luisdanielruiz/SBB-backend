function actualizarTipoChallengueTelerik(){ 
    dataSourceTipoChallengue.read({accTipoChallengue:"getDataT"});
} 
 
 
 
function nuevoTipoChallengue(){/*tabla es string*/ 
    limpiarTipoChallengue(); 
    $("#fromABMTipoChallengue").val("n"); 
    var win = $("#windowTipoChallengue").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarTipoChallengue(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMTipoChallengue(sTabla); 
        $("#fromABMTipoChallengue").val("e"); 
        $( "#ABMTipoChallengue" ).popup( "open" ); 
    } 
} 
 
function editarTipoChallengue(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMTipoChallengue").val("e"); 
                idR.value = entidad.id; 
        nombreR.value = entidad.nombre; 
 
        var win = $("#windowTipoChallengue").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarTipoChallengue(){ 
        idR.value = ''; 
        nombreR.value = ''; 

} 
 
function guardarTipoChallengue(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "TipoChallengueService", 
          data: $( "#ABMTipoChallengueForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarTipoChallengueTelerik();
                var win = $("#windowTipoChallengue").data("kendoWindow");
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
function elimnarWinTipoChallengue(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarTipoChallengue").data("kendoWindow").center();
        $("#windowEliminarTipoChallengue").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarTipoChallengue(){
    var win = $("#windowEliminarTipoChallengue").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarTipoChallengue(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accTipoChallengue="eliminar"; 
                objElimino.Codigo=entidadEliminar.Codigo; 

            $.ajax({ 
              type: "POST", 
              url: "TipoChallengueService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarTipoChallengueTelerik();
                    var win = $("#windowEliminarTipoChallengue").data("kendoWindow");
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

