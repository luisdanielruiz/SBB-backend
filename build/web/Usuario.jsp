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
        <script src="UsuarioABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>Usuarios Admin</title> 
 
    </head> 
    <body> 
               
        <%@include file="menu.jsp" %>
 
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em id="iconButtonNuevo" onclick="nuevoUsuario();"><span class="k-sprite ">Nuevo</span></em>
                <em style="display: none" id="iconButtonRefresh" onclick="actualizarUsuarioTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowUsuario"> 
               <form id="ABMUsuarioForm"> 
                   <input type="hidden" name="accUsuario" id="accUsuario" value="guardar"> 
                   <input type="hidden" name="fromABMUsuario" id="fromABMUsuario" value=""> 
                   <ul class="fieldlist"> 
                        <li>
                            <label for="simple-input">user</label>
                            <input name="user" id="userR" type="text" class="k-textbox" style="width: 100%;" readonly />
                        </li>
                        <li>
                            <label for="simple-input">password</label>
                            <input name="password" id="passwordR" type="text" class="k-textbox" style="width: 100%;"  />
                        </li>
                        <li>
                            <label for="simple-input">mail</label>
                            <input name="mail" id="mailR" type="text" placeholder="sophie@example.com" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">idPerfil</label>
                            <input name="idPerfil" id="idPerfilR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">estado</label>
                            <input name="estado" id="estadoR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">recibeMail</label>
                            <input name="recibeMail" id="recibeMailR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">codigoTablet</label>
                            <input name="codigoTablet" id="codigoTabletR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                       <li>
                            <a href="javascript:guardarUsuario();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarUsuario">
                    <br>
                    ¿Desea eliminar el Usuario?<br><br>                    
                    <a href="javascript:eliminarUsuario();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarUsuario();" class="k-button">Cancelar</a>
                        
                    
            </div>
            
            
            <div id="cargando" class="k-loading-mask cargador" style="display: none;"><span class="k-loading-text">Loading...</span><div class="k-loading-image"></div><div class="k-loading-color"></div></div>
            
            <script>
                
                 $(document).ready(function() {
                     actualizarUsuarioTelerik();
                    kendo.culture("es-AR");            
                    $("#iconButtonNuevo").kendoButton({
                        spriteCssClass: "k-icon k-i-plus"
                    }); 
                    $("#iconButtonRefresh").kendoButton({
                        spriteCssClass: "k-icon k-i-refresh"
                    });                     
                    if (!$("#windowUsuario").data("kendoWindow")) {
                        $("#windowUsuario").kendoWindow({
                            width: "300px",
                            height: "460px",
                            title: "ABM Usuario",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarUsuario").data("kendoWindow")) {
                        $("#windowEliminarUsuario").kendoWindow({
                            width: "300px",
                            title: "Eliminar Usuario",
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
 
                DataGrid dg = new DataGrid("Usuario", columnas, -1, dl); 
 
                columnas.add(new DataGridColumn("user", "user", "1", "", "", "0", "")); 
                columnas.add(new DataGridColumn("password", "password", "1", "", "", "1", "")); 
                columnas.add(new DataGridColumn("mail", "mail", "1", "", "", "2", "")); 
                //columnas.add(new DataGridColumn("idPerfil", "idPerfil", "1", "", "", "3", "")); 
                //columnas.add(new DataGridColumn("estado", "estado", "1", "", "", "4", "")); 
                //columnas.add(new DataGridColumn("recibeMail", "recibeMail", "1", "", "", "5", "")); 
                //columnas.add(new DataGridColumn("codigoTablet", "codigoTablet", "1", "", "", "6", "")); 
                out.println(dg.toHTML(false, true, true, "", 0)); 
            
            
            
            %>

</div>


        
</body>
</html> 