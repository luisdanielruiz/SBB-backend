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
 
    <head> 
        <%=WebDependencies.getCSSandJS()%> 
        <script src="TesteoABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>Testeo</title> 
 
    </head> 
    <body> 
               
        <%@include file="menu.jsp" %>
        
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em id="iconButtonNuevo" onclick="nuevoTesteo();"><span class="k-sprite ">Nuevo</span></em>
                <em id="iconButtonRefresh" onclick="actualizarTesteoTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowTesteo"> 
               <form id="ABMTesteoForm"> 
                   <input type="hidden" name="accTesteo" id="accTesteo" value="guardar"> 
                   <input type="hidden" name="fromABMTesteo" id="fromABMTesteo" value=""> 
                   <ul class="fieldlist"> 
                       <li>
                            <label for="simple-input">idTest</label>
                            <input name="idTest" id="idTestR" type="text" class="k-textbox" style="width: 100%;" />
                        <li>
                        <li>
                            <label for="simple-input">apellido</label>
                            <input name="apellido" id="apellidoR" type="text" class="k-textbox" style="width: 100%;" />
                        <li>
                        <li>
                            <label for="simple-input">nombre</label>
                            <input name="nombre" id="nombreR" type="text" class="k-textbox" style="width: 100%;" />
                        <li>
                       <li>
                            <a href="javascript:guardarTesteo();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarTesteo">
                    <br>
                    ¿Desea eliminar el Testeo?<br><br>                    
                    <a href="javascript:eliminarTesteo();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarTesteo();" class="k-button">Cancelar</a>
                        
                    
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
                    if (!$("#windowTesteo").data("kendoWindow")) {
                        $("#windowTesteo").kendoWindow({
                            width: "300px",
                            title: "ABM Testeo",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarTesteo").data("kendoWindow")) {
                        $("#windowEliminarTesteo").kendoWindow({
                            width: "300px",
                            title: "Eliminar Testeo",
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
            
                EntidadVO e =  new GS.UTIL.ReporteModelo.TesteoVO();  
                Vector <EntidadVO> dl = null;
                ArrayList<DataGridColumn> columnas= new ArrayList<DataGridColumn>(); 
                columnas.add(new DataGridColumn("idTest", "idTest", "1", "", "", "1", "")); 
                columnas.add(new DataGridColumn("apellido", "apellido", "1", "", "", "2", "")); 
                columnas.add(new DataGridColumn("nombre", "nombre", "1", "", "", "3", "")); 
 
                DataGrid dg = new DataGrid("Testeo", columnas, -1, dl); 
 
                out.println(dg.toHTML(false, true, true, "", 0)); 
            
            
            
            %>

</div>


        
</body>
</html> 