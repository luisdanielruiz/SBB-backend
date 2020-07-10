<%@page import="GS.Base.*"%>
<%@page import="Entidades.Helpers.EntidadHLP"%> 
<%@page import="Entidades.EntidadVO"%> 
<%@page import="GS.GRIApp.Navigation.HorizontalMenu"%> 
<%@page import="GS.GRIAapp.Comon.WebDependencies"%> 
<%@page import="GS.GRIApp.DataGrid.DataGridColumn"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.Vector"%> 
<%@page import="GS.GRIApp.DataGrid.DataGrid"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html lang="en"> 
    <% 
        if (!UsuarioService.comprobarSession(request)) 
            response.sendRedirect("Login.jsp");     
    %> 
    <link href="img/favicon.ico" rel="icon" type="image/x-icon" />
    <head> 
        <%=WebDependencies.getCSSandJS()%> 
        <script src="CategoriesABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>Categorias</title> 
 
    </head> 
    <body> 
      <%@include file="menu.jsp" %>
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em id="iconButtonNuevo" onclick="nuevoCategories();"><span class="k-sprite ">Nuevo</span></em>
                <em style="display: none" id="iconButtonRefresh" onclick="actualizarCategoriesTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowCategories"> 
               <form id="ABMCategoriesForm"> 
                   <input type="hidden" name="accCategories" id="accCategories" value="guardar"> 
                   <input type="hidden" name="fromABMCategories" id="fromABMCategories" value=""> 
                   <ul class="fieldlist"> 
                        <li style="display: none" >
                            <label for="simple-input">id</label>
                            <input name="id" id="idR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">nombre categoria</label>
                            <input name="nombre" id="nombreR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                       <li>
                            <a href="javascript:guardarCategories();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarCategories">
                    <br>
                    ¿Desea eliminar el Categories?<br><br>                    
                    <a href="javascript:eliminarCategories();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarCategories();" class="k-button">Cancelar</a>
                        
                    
            </div>
            
            
            <div id="cargando" class="k-loading-mask cargador" style="display: none;"><span class="k-loading-text">Loading...</span><div class="k-loading-image"></div><div class="k-loading-color"></div></div>
            
            <script>
                
                 $(document).ready(function() {
                     actualizarCategoriesTelerik();
                    kendo.culture("es-AR");            
                    $("#iconButtonNuevo").kendoButton({
                        spriteCssClass: "k-icon k-i-plus"
                    }); 
                    $("#iconButtonRefresh").kendoButton({
                        spriteCssClass: "k-icon k-i-refresh"
                    });                     
                    if (!$("#windowCategories").data("kendoWindow")) {
                        $("#windowCategories").kendoWindow({
                            width: "300px",
                            title: "ABM Categories",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarCategories").data("kendoWindow")) {
                        $("#windowEliminarCategories").kendoWindow({
                            width: "300px",
                            title: "Eliminar Categories",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                 });
                 var notification = $("#notification").kendoNotification({
                    position: {
                      bottom: 30,
                      right: 30
                    },
                    autoHideAfter: 0
                  }).data("kendoNotification");     
                 
            </script>
           <%
            
                Vector <EntidadVO> dl = null;
                ArrayList<DataGridColumn> columnas= new ArrayList<DataGridColumn>(); 
 
                DataGrid dg = new DataGrid("Categories", columnas, -1, dl); 
 
                //columnas.add(new DataGridColumn("id", "id", "1", "", "", "0", "")); 
                columnas.add(new DataGridColumn("Nombre", "nombre", "1", "", "", "1", "")); 
                out.println(dg.toHTML(false, true, true, "", 0)); 
            
            
            
            %>

</div>


        
</body>
</html> 