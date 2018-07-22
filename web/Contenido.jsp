<%@page import="Entidades.Helpers.EntidadHLP"%> 
<%@page import="GS.Base.*"%> 
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
 
    <head> 
        <%=WebDependencies.getCSSandJS()%> 
        <script src="ContenidoABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
        
        <title>Contenido</title> 
 
    </head> 
    <body> 
               
 
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em id="iconButtonNuevo" onclick="nuevoContenido();"><span class="k-sprite ">Nuevo</span></em>
                <em id="iconButtonRefresh" onclick="actualizarContenidoTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowContenido"> 
               <form id="ABMContenidoForm"> 
                   <input type="hidden" name="accContenido" id="accContenido" value="guardar"> 
                   <input type="hidden" name="fromABMContenido" id="fromABMContenido" value=""> 
                   <ul class="fieldlist"> 
                        <li>
                            <label for="simple-input">id</label>
                            <input name="id" id="idR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">modelo</label>
                            <input name="modelo" id="modeloR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">seccion</label>
                            <input name="seccion" id="seccionR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">subcontenido_id</label>
                            <input name="subcontenido_id" id="subcontenido_idR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">orden</label>
                            <input name="orden" id="ordenR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">descripcion</label>
                            <input name="descripcion" id="descripcionR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">seo</label>
                            <input name="seo" id="seoR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">url</label>
                            <input name="url" id="urlR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">textos</label>
                            <input name="textos" id="textosR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">archivos</label>
                            <input name="archivos" id="archivosR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">activo</label>
                            <input name="activo" id="activoR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                       <li>
                            <a href="javascript:guardarContenido();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarContenido">
                    <br>
                    ¿Desea eliminar el Contenido?<br><br>                    
                    <a href="javascript:eliminarContenido();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarContenido();" class="k-button">Cancelar</a>
                        
                    
            </div>
            
            
            <div id="cargando" class="k-loading-mask cargador" style="display: none;"><span class="k-loading-text">Loading...</span><div class="k-loading-image"></div><div class="k-loading-color"></div></div>
            
            <script>
                
                 $(document).ready(function() {
                    kendo.culture("es-AR");            
                    $("#iconButtonNuevo").kendoButton({
                        spriteCssClass: "k-icon k-i-plus"
                    }); 
                    $("#iconButtonRefresh").kendoButton({
                        spriteCssClass: "k-icon k-i-refresh"
                    });                     
                    if (!$("#windowContenido").data("kendoWindow")) {
                        $("#windowContenido").kendoWindow({
                            width: "300px",
                            title: "ABM Contenido",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarContenido").data("kendoWindow")) {
                        $("#windowEliminarContenido").kendoWindow({
                            width: "300px",
                            title: "Eliminar Contenido",
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
 
                DataGrid dg = new DataGrid("Contenido", columnas, -1, dl); 
 
                columnas.add(new DataGridColumn("id", "id", "1", "", "", "0", "")); 
                columnas.add(new DataGridColumn("modelo", "modelo", "1", "", "", "1", "")); 
                columnas.add(new DataGridColumn("seccion", "seccion", "1", "", "", "2", "")); 
                columnas.add(new DataGridColumn("subcontenido_id", "subcontenido_id", "1", "", "", "3", "")); 
                columnas.add(new DataGridColumn("orden", "orden", "1", "", "", "4", "")); 
                columnas.add(new DataGridColumn("descripcion", "descripcion", "1", "", "", "5", "")); 
                columnas.add(new DataGridColumn("seo", "seo", "1", "", "", "6", "")); 
                columnas.add(new DataGridColumn("url", "url", "1", "", "", "7", "")); 
                columnas.add(new DataGridColumn("textos", "textos", "1", "", "", "8", "")); 
                columnas.add(new DataGridColumn("archivos", "archivos", "1", "", "", "9", "")); 
                columnas.add(new DataGridColumn("activo", "activo", "1", "", "", "10", "")); 
                out.println(dg.toHTML(false, true, true, "", 0)); 
            
            
            
            %>

</div>


        
</body>
</html> 