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
        <script src="HitABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>Hit</title> 
 
    </head> 
    <body> 
               
 
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em id="iconButtonNuevo" onclick="nuevoHit();"><span class="k-sprite ">Nuevo</span></em>
                <em id="iconButtonRefresh" onclick="actualizarHitTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowHit"> 
               <form id="ABMHitForm"> 
                   <input type="hidden" name="accHit" id="accHit" value="guardar"> 
                   <input type="hidden" name="fromABMHit" id="fromABMHit" value=""> 
                   <ul class="fieldlist"> 
                        <li>
                            <label for="simple-input">id</label>
                            <input name="id" id="idR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">valueForce</label>
                            <input name="valueForce" id="valueForceR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">valueVel</label>
                            <input name="valueVel" id="valueVelR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">date</label>
                            <input name="date" id="dateR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                       <li>
                            <a href="javascript:guardarHit();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarHit">
                    <br>
                    ¿Desea eliminar el Hit?<br><br>                    
                    <a href="javascript:eliminarHit();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarHit();" class="k-button">Cancelar</a>
                        
                    
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
                    if (!$("#windowHit").data("kendoWindow")) {
                        $("#windowHit").kendoWindow({
                            width: "300px",
                            title: "ABM Hit",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarHit").data("kendoWindow")) {
                        $("#windowEliminarHit").kendoWindow({
                            width: "300px",
                            title: "Eliminar Hit",
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
 
                DataGrid dg = new DataGrid("Hit", columnas, -1, dl); 
 
                columnas.add(new DataGridColumn("id", "id", "1", "", "", "0", "")); 
                columnas.add(new DataGridColumn("valueForce", "valueForce", "1", "", "", "1", "")); 
                columnas.add(new DataGridColumn("valueVel", "valueVel", "1", "", "", "2", "")); 
                columnas.add(new DataGridColumn("date", "date", "1", "", "", "3", "")); 
                out.println(dg.toHTML(false, true, true, "", 0)); 
            
            
            
            %>

</div>


        
</body>
</html> 