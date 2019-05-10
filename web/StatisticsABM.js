function actualizarStatisticsTelerik(){ 
    dataSourceStatistics.read({accStatistics:"getDataT"});
} 
 
 
 
function nuevoStatistics(){/*tabla es string*/ 
    limpiarStatistics(); 
    $("#fromABMStatistics").val("n"); 
    var win = $("#windowStatistics").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarStatistics(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMStatistics(sTabla); 
        $("#fromABMStatistics").val("e"); 
        $( "#ABMStatistics" ).popup( "open" ); 
    } 
} 
 
function editarStatistics(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMStatistics").val("e"); 
                idR.value = entidad.id; 
        userR.value = entidad.user; 
        challengueR.value = entidad.challengue; 
        tipoChallengueR.value = entidad.tipoChallengue; 
        timeR.value = entidad.time; 
        hitsR.value = entidad.hits; 
 
        var win = $("#windowStatistics").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarStatistics(){ 
        idR.value = ''; 
        userR.value = ''; 
        challengueR.value = ''; 
        tipoChallengueR.value = ''; 
        timeR.value = ''; 
        hitsR.value = ''; 

} 
 
function guardarStatistics(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "StatisticsService", 
          data: $( "#ABMStatisticsForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarStatisticsTelerik();
                var win = $("#windowStatistics").data("kendoWindow");
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
function eliminarWinStatistics(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarStatistics").data("kendoWindow").center();
        $("#windowEliminarStatistics").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarStatistics(){
    var win = $("#windowEliminarStatistics").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarStatistics(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accStatistics="eliminar"; 
                objElimino.id=entidadEliminar.id; 

            $.ajax({ 
              type: "POST", 
              url: "StatisticsService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarStatisticsTelerik();
                    var win = $("#windowEliminarStatistics").data("kendoWindow");
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

