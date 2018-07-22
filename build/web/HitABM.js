function actualizarHitTelerik(){ 
    dataSourceHit.read({accHit:"getDataT"});
} 
 
 
 
function nuevoHit(){/*tabla es string*/ 
    limpiarHit(); 
    $("#fromABMHit").val("n"); 
    var win = $("#windowHit").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarHit(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMHit(sTabla); 
        $("#fromABMHit").val("e"); 
        $( "#ABMHit" ).popup( "open" ); 
    } 
} 
 
function editarHit(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMHit").val("e"); 
                idR.value = entidad.id; 
        valueForceR.value = entidad.valueForce; 
        valueVelR.value = entidad.valueVel; 
        dateR.value = entidad.date; 
 
        var win = $("#windowHit").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarHit(){ 
        idR.value = ''; 
        valueForceR.value = ''; 
        valueVelR.value = ''; 
        dateR.value = ''; 

} 
 
function guardarHit(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "HitService", 
          data: $( "#ABMHitForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarHitTelerik();
                var win = $("#windowHit").data("kendoWindow");
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
function elimnarWinHit(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarHit").data("kendoWindow").center();
        $("#windowEliminarHit").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarHit(){
    var win = $("#windowEliminarHit").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarHit(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accHit="eliminar"; 
                objElimino.Codigo=entidadEliminar.Codigo; 

            $.ajax({ 
              type: "POST", 
              url: "HitService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarHitTelerik();
                    var win = $("#windowEliminarHit").data("kendoWindow");
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

