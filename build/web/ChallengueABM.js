function actualizarChallengueTelerik(){ 
    dataSourceChallengue.read({accChallengue:"getDataT"});
} 
 
 
 
function nuevoChallengue(){/*tabla es string*/ 
    limpiarChallengue(); 
    $("#fromABMChallengue").val("n"); 
    var win = $("#windowChallengue").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarChallengue(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMChallengue(sTabla); 
        $("#fromABMChallengue").val("e"); 
        $( "#ABMChallengue" ).popup( "open" ); 
    } 
} 
 
function editarChallengue(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMChallengue").val("e"); 
                idChallengueR.value = entidad.idChallengue; 
        tipoChallengueR.value = entidad.tipoChallengue; 
        nombreR.value = entidad.nombre; 
        hitsR.value = entidad.hits; 
        timeR.value = entidad.time; 
 
        var win = $("#windowChallengue").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarChallengue(){ 
        idChallengueR.value = ''; 
        tipoChallengueR.value = ''; 
        nombreR.value = ''; 
        hitsR.value = ''; 
        timeR.value = ''; 

} 
 
function guardarChallengue(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "ChallengueService", 
          data: $( "#ABMChallengueForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarChallengueTelerik();
                var win = $("#windowChallengue").data("kendoWindow");
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
function elimnarWinChallengue(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarChallengue").data("kendoWindow").center();
        $("#windowEliminarChallengue").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarChallengue(){
    var win = $("#windowEliminarChallengue").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarChallengue(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accChallengue="eliminar"; 
                objElimino.idChallengue=entidadEliminar.idChallengue; 

            $.ajax({ 
              type: "POST", 
              url: "ChallengueService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarChallengueTelerik();
                    var win = $("#windowEliminarChallengue").data("kendoWindow");
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

