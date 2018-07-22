function actualizarTesteoTelerik(){ 
    dataSourceTesteo.read({accTesteo:"getDataT"});
} 
 
 
 
function nuevoTesteo(){/*tabla es string*/ 
    limpiarTesteo(); 
    $("#fromABMTesteo").val("n"); 
    var win = $("#windowTesteo").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarTesteo(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMTesteo(sTabla); 
        $("#fromABMTesteo").val("e"); 
        $( "#ABMTesteo" ).popup( "open" ); 
    } 
} 
 
function editarTesteo(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMTesteo").val("e"); 
        idTestR.value = entidad.idTest; 

        apellidoR.value = entidad.apellido; 
        nombreR.value = entidad.nombre; 
        var win = $("#windowTesteo").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarTesteo(){ 

        idTestR.value = ""; 

        apellidoR.value = ""; 
        nombreR.value = ""; 
} 
 
function guardarTesteo(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "TesteoService", 
          data: $( "#ABMTesteoForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarTesteoTelerik();
                var win = $("#windowTesteo").data("kendoWindow");
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
         
function elimnarWinTesteo(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarTesteo").data("kendoWindow").center();
        $("#windowEliminarTesteo").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarTesteo(){
    var win = $("#windowEliminarTesteo").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarTesteo(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accTesteo="eliminar"; 
                objElimino.idTest=entidadEliminar.idTest; 

            $.ajax({ 
              type: "POST", 
              url: "TesteoService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarTesteoTelerik();
                    var win = $("#windowEliminarTesteo").data("kendoWindow");
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

