function actualizarContenidoTelerik(){ 
    dataSourceContenido.read({accContenido:"getDataT"});
} 
 
 
 
function nuevoContenido(){/*tabla es string*/ 
    limpiarContenido(); 
    $("#fromABMContenido").val("n"); 
    var win = $("#windowContenido").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarContenido(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMContenido(sTabla); 
        $("#fromABMContenido").val("e"); 
        $( "#ABMContenido" ).popup( "open" ); 
    } 
} 
 
function editarContenido(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMContenido").val("e"); 
                idR.value = entidad.id; 
        modeloR.value = entidad.modelo; 
        seccionR.value = entidad.seccion; 
        subcontenido_idR.value = entidad.subcontenido_id; 
        ordenR.value = entidad.orden; 
        descripcionR.value = entidad.descripcion; 
        seoR.value = entidad.seo; 
        urlR.value = entidad.url; 
        textosR.value = entidad.textos; 
        archivosR.value = entidad.archivos; 
        activoR.value = entidad.activo; 
 
        var win = $("#windowContenido").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarContenido(){ 
        idR.value = ''; 
        modeloR.value = ''; 
        seccionR.value = ''; 
        subcontenido_idR.value = ''; 
        ordenR.value = ''; 
        descripcionR.value = ''; 
        seoR.value = ''; 
        urlR.value = ''; 
        textosR.value = ''; 
        archivosR.value = ''; 
        activoR.value = ''; 

} 
 
function guardarContenido(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "ContenidoService", 
          data: $( "#ABMContenidoForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarContenidoTelerik();
                var win = $("#windowContenido").data("kendoWindow");
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
function eliminarWinContenido(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarContenido").data("kendoWindow").center();
        $("#windowEliminarContenido").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarContenido(){
    var win = $("#windowEliminarContenido").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarContenido(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accContenido="eliminar"; 
                objElimino.id=entidadEliminar.id; 

            $.ajax({ 
              type: "POST", 
              url: "ContenidoService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarContenidoTelerik();
                    var win = $("#windowEliminarContenido").data("kendoWindow");
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

