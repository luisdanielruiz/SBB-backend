function actualizarCategoriesTelerik(){ 
    dataSourceCategories.read({accCategories:"getDataT"});
} 
 
 
 
function nuevoCategories(){/*tabla es string*/ 
    limpiarCategories(); 
    $("#fromABMCategories").val("n"); 
    var win = $("#windowCategories").data("kendoWindow");
    win.center();
    win.open(); 
} 
 
 
function editarCategories(sTabla){/*tabla es string*/ 
    if (eval(sTabla).selectedItem != -1){ 
        var entidad = DGgetSelectedRowObj(eval(sTabla)); 
        fillABMCategories(sTabla); 
        $("#fromABMCategories").val("e"); 
        $( "#ABMCategories" ).popup( "open" ); 
    } 
} 
 
function editarCategories(entidad){/*tabla es string*/ 
    if (entidad != null){ 
        $("#fromABMCategories").val("e"); 
                idR.value = entidad.id; 
        nombreR.value = entidad.nombre; 
 
        var win = $("#windowCategories").data("kendoWindow");
        win.center();
        win.open();
} 
} 
 
function limpiarCategories(){ 
        idR.value = ''; 
        nombreR.value = ''; 

} 
 
function guardarCategories(){
   try{
        $( "#cargando" ).css( "display","block" );  
        
        
        $.ajax({
          type: "POST", 
          url: "CategoriesService", 
          data: $( "#ABMCategoriesForm" ).serialize() 
        }) 
        .done(function( msg ) {         
          if(msg.indexOf('Login.jsp') != -1){    
               $( "#loginWin" ).popup( "open" );  
          }else{           
            if (msg.indexOf('OK#1231321')!=-1){ 
                notification.info("Guardado");
                actualizarCategoriesTelerik();
                var win = $("#windowCategories").data("kendoWindow");
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
function elimnarWinCategories(obj){/*tabla es string*/ 
    if (obj != null){ 
        entidadEliminar = obj;
        $("#windowEliminarCategories").data("kendoWindow").center();
        $("#windowEliminarCategories").data("kendoWindow").open(); 
    } 
} 
function cancelarEliminarCategories(){
    var win = $("#windowEliminarCategories").data("kendoWindow");
    win.close();
    entidadEliminar = null;
}
function eliminarCategories(){ 
    try{
        $( "#cargando" ).css( "display","block" );         
        if (entidadEliminar != null){ 
            
            var objElimino = new Object();
                objElimino.accCategories="eliminar"; 
                objElimino.Codigo=entidadEliminar.Codigo; 

            $.ajax({ 
              type: "POST", 
              url: "CategoriesService", 
              data: objElimino 
            }) 
            .done(function( msg ) {         
               if(msg.indexOf('Login.jsp') != -1){    
                   $( "#loginWin" ).popup( "open" );  
               }else{ 
                if (msg.indexOf('OK#1231321')!=-1){ 
                   notification.info("Eliminado");
                    actualizarCategoriesTelerik();
                    var win = $("#windowEliminarCategories").data("kendoWindow");
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

