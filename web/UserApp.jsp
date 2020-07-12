<%@page import="Py.DB.VO.Helpers.PyEntidadHLP"%>
<%@page import="AplicationConstant.DatabaseConstant"%>
<%@page import="java.util.List"%>
<%@page import="Py.DB.VO.PyVO"%>
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
        <script src="UserAppABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>Usuarios App</title> 
 
    </head> 
    <body> 
        <%@include file="menu.jsp" %>
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em style="display: none" id="iconButtonNuevo" onclick="nuevoUserApp();"><span class="k-sprite ">Nuevo</span></em>
                <em style="display: none" id="iconButtonRefresh" onclick="actualizarUserAppTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowUserApp"> 
               <form id="ABMUserAppForm"> 
                   <input type="hidden" name="accUserApp" id="accUserApp" value="guardar"> 
                   <input type="hidden" name="fromABMUserApp" id="fromABMUserApp" value=""> 
                   <ul class="fieldlist"> 
                       <li style="display: none" >
                            <label for="simple-input">idUser</label>
                            <input name="idUser" id="idUserR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">userName</label>
                            <input name="userName" id="userNameR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">Email</label>
                            <input name="email" id="emailR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">Nombre</label>
                            <input name="name" id="nameR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">Apellido</label>
                            <input name="surname" id="surnameR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">Contraseña</label>
                            <input name="password" id="passwordR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">country</label>
                            <input name="country" id="countryR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">state</label>
                            <input name="state" id="stateR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">city</label>
                            <input name="city" id="cityR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">address</label>
                            <input name="address" id="addressR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">postalCode</label>
                            <input name="postalCode" id="postalCodeR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">idFacebook</label>
                            <input name="idFacebook" id="idFacebookR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">idGoogle</label>
                            <input name="idGoogle" id="idGoogleR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">profilePicture</label>
                            <input name="profilePicture" id="profilePictureR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">phone_cod</label>
                            <input name="phone_cod" id="phone_codR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">phone</label>
                            <input name="phone" id="phoneR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">status</label>
                            <input name="status" id="statusR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">axisY</label>
                            <input name="axisY" id="axisYR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">axisX</label>
                            <input name="axisX" id="axisXR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">location</label>
                            <input name="location" id="locationR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">mailConfirmado</label>
                            <input name="mailConfirmado" id="mailConfirmadoR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">dni</label>
                            <input name="dni" id="dniR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">cuil</label>
                            <input name="cuil" id="cuilR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">premium</label>
                            <input name="premium" id="premiumR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">fechaRegistro</label>
                            <input name="fechaRegistro" id="fechaRegistroR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">sesionHistory</label>
                            <input name="sesionHistory" id="sesionHistoryR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li style="display: none">
                            <label for="simple-input">statistics</label>
                            <input name="statistics" id="statisticsR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                       <li>
                            <a href="javascript:guardarUserApp();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarUserApp">
                    <br>
                    ¿Desea eliminar el UserApp?<br><br>                    
                    <a href="javascript:eliminarUserApp();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarUserApp();" class="k-button">Cancelar</a>
                        
                    
            </div>
            
            
            <div id="cargando" class="k-loading-mask cargador" style="display: none;"><span class="k-loading-text">Loading...</span><div class="k-loading-image"></div><div class="k-loading-color"></div></div>
            
            <script>
                
                 $(document).ready(function() {
                     actualizarUserAppTelerik();
                    kendo.culture("es-AR");            
                    $("#iconButtonNuevo").kendoButton({
                        spriteCssClass: "k-icon k-i-plus"
                    }); 
                    $("#iconButtonRefresh").kendoButton({
                        spriteCssClass: "k-icon k-i-refresh"
                    });         
                    
                    if (!$("#windowUserApp").data("kendoWindow")) {
                        $("#windowUserApp").kendoWindow({
                            width: "500px",
                            height: "300px",
                            title: "Modificar Usuario",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarUserApp").data("kendoWindow")) {
                        $("#windowEliminarUserApp").kendoWindow({
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
 
                DataGrid dg = new DataGrid("UserApp", columnas, -1, dl); 
 
                //columnas.add(new DataGridColumn("idUser", "idUser", "1", "", "", "0", "")); 
                //columnas.add(new DataGridColumn("userName", "userName", "1", "", "", "1", ""));
                columnas.add(new DataGridColumn("Email", "email", "1", "", "", "1", ""));
                columnas.add(new DataGridColumn("Nombre", "name", "1", "", "", "2", "")); 
                columnas.add(new DataGridColumn("Apellido", "surname", "1", "", "", "3", "")); 
                columnas.add(new DataGridColumn("Contraseña", "password", "1", "", "", "4", "")); 
                 
               
                //columnas.add(new DataGridColumn("country", "country", "1", "", "", "6", "")); 
                //columnas.add(new DataGridColumn("state", "state", "1", "", "", "7", "")); 
                //columnas.add(new DataGridColumn("city", "city", "1", "", "", "8", "")); 
                //columnas.add(new DataGridColumn("address", "address", "1", "", "", "9", "")); 
                //columnas.add(new DataGridColumn("postalCode", "postalCode", "1", "", "", "10", "")); 
                //columnas.add(new DataGridColumn("idFacebook", "idFacebook", "1", "", "", "11", "")); 
                //columnas.add(new DataGridColumn("profilePicture", "profilePicture", "1", "", "", "13", "")); 
                //columnas.add(new DataGridColumn("phone_cod", "phone_cod", "1", "", "", "14", "")); 
                //columnas.add(new DataGridColumn("phone", "phone", "1", "", "", "15", "")); 
                //columnas.add(new DataGridColumn("status", "status", "1", "", "", "16", "")); 
                //columnas.add(new DataGridColumn("axisY", "axisY", "1", "", "", "17", "")); 
                //columnas.add(new DataGridColumn("axisX", "axisX", "1", "", "", "18", "")); 
                //columnas.add(new DataGridColumn("location", "location", "1", "", "", "19", "")); 
                //columnas.add(new DataGridColumn("mailConfirmado", "mailConfirmado", "1", "", "", "20", "")); 
                //columnas.add(new DataGridColumn("dni", "dni", "1", "", "", "21", "")); 
                //columnas.add(new DataGridColumn("cuil", "cuil", "1", "", "", "22", "")); 
                //columnas.add(new DataGridColumn("premium", "premium", "1", "", "", "23", "")); 
                columnas.add(new DataGridColumn("Fecha de Registro", "fechaRegistro", "1", "", "", "5", "")); 
                //columnas.add(new DataGridColumn("sesionHistory", "sesionHistory", "1", "", "", "25", "")); 
                //columnas.add(new DataGridColumn("statistics", "statistics", "1", "", "", "26", "")); 
                out.println(dg.toHTML(false, true, true, "", 0)); 
            
            
            
            %>

</div>


        
</body>
</html> 