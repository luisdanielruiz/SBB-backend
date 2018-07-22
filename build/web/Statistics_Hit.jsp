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
        <script src="Statistics_HitABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>Statistics_Hit</title> 
 
    </head> 
    <body> 
               
 
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em id="iconButtonNuevo" onclick="nuevoStatistics_Hit();"><span class="k-sprite ">Nuevo</span></em>
                <em id="iconButtonRefresh" onclick="actualizarStatistics_HitTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowStatistics_Hit"> 
               <form id="ABMStatistics_HitForm"> 
                   <input type="hidden" name="accStatistics_Hit" id="accStatistics_Hit" value="guardar"> 
                   <input type="hidden" name="fromABMStatistics_Hit" id="fromABMStatistics_Hit" value=""> 
                   <ul class="fieldlist"> 
                        <li>
                            <label for="simple-input">idHit</label>
                            <input name="idHit" id="idHitR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">idStatistic</label>
                            <input name="idStatistic" id="idStatisticR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                       <li>
                            <a href="javascript:guardarStatistics_Hit();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarStatistics_Hit">
                    <br>
                    ¿Desea eliminar el Statistics_Hit?<br><br>                    
                    <a href="javascript:eliminarStatistics_Hit();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarStatistics_Hit();" class="k-button">Cancelar</a>
                        
                    
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
                    if (!$("#windowStatistics_Hit").data("kendoWindow")) {
                        $("#windowStatistics_Hit").kendoWindow({
                            width: "300px",
                            title: "ABM Statistics_Hit",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarStatistics_Hit").data("kendoWindow")) {
                        $("#windowEliminarStatistics_Hit").kendoWindow({
                            width: "300px",
                            title: "Eliminar Statistics_Hit",
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
 
                DataGrid dg = new DataGrid("Statistics_Hit", columnas, -1, dl); 
 
                columnas.add(new DataGridColumn("idHit", "idHit", "1", "", "", "0", "")); 
                columnas.add(new DataGridColumn("idStatistic", "idStatistic", "1", "", "", "1", "")); 
                out.println(dg.toHTML(false, true, true, "", 0)); 
            
            
            
            %>

</div>


        
</body>
</html> 