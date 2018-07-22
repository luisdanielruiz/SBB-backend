function actualizarStatistics_HitTelerik(){ 
    dataSourceStatistics_Hit.read({accStatistics_Hit:"getDataT"});
} 
 
 
 
function nuevoStatistics_Hit(){/*tabla es string*/ 
    limpiarStatistics_Hit(); 
    $("#fromABMStatistics_Hit").val("n"); 
    var win = $("#windowStatistics_Hit").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarStatistics_Hit(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMStatistics_Hit(sTabla); 
        $("#fromABMStatistics_Hit").val("e"); 
        $( "#ABMStatistics_Hit" ).popup( "open" ); 
    } 
} 
 
function editarStatistics_Hit(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMStatistics_Hit").val("e"); 
                idHitR.value = entidad.idHit; 
        idStatisticR.value = entidad.idStatistic; 
 
        var win = $("#windowStatistics_Hit").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarStatistics_Hit(){ 
        idHitR.value = ''; 
        idStatisticR.value = ''; 

} 
 
function guardarStatistics_Hit(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "Statistics_HitService", 
          data: $( "#ABMStatistics_HitForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarStatistics_HitTelerik();
                var win = $("#windowStatistics_Hit").data("kendoWindow");
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
function elimnarWinStatistics_Hit(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarStatistics_Hit").data("kendoWindow").center();
        $("#windowEliminarStatistics_Hit").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarStatistics_Hit(){
    var win = $("#windowEliminarStatistics_Hit").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarStatistics_Hit(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accStatistics_Hit="eliminar"; 
                objElimino.Codigo=entidadEliminar.Codigo; 

            $.ajax({ 
              type: "POST", 
              url: "Statistics_HitService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarStatistics_HitTelerik();
                    var win = $("#windowEliminarStatistics_Hit").data("kendoWindow");
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

