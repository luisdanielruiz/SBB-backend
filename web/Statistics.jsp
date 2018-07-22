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
 
    <head> 
        <%=WebDependencies.getCSSandJS()%> 
        <script src="StatisticsABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>Statistics</title> 
 
    </head> 
    <body> 
               
  <%@include file="menu.jsp" %>
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em id="iconButtonNuevo" onclick="nuevoStatistics();"><span class="k-sprite ">Nuevo</span></em>
                <em id="iconButtonRefresh" onclick="actualizarStatisticsTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowStatistics"> 
               <form id="ABMStatisticsForm"> 
                   <input type="hidden" name="accStatistics" id="accStatistics" value="guardar"> 
                   <input type="hidden" name="fromABMStatistics" id="fromABMStatistics" value=""> 
                   <ul class="fieldlist"> 
                        <li>
                            <label for="simple-input">id</label>
                            <input name="id" id="idR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">user</label>
                            <input name="user" id="userR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">challengue</label>
                            <input name="challengue" id="challengueR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">tipoChallengue</label>
                            <input name="tipoChallengue" id="tipoChallengueR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">time</label>
                            <input name="time" id="timeR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">hits</label>
                            <input name="hits" id="hitsR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                       <li>
                            <a href="javascript:guardarStatistics();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarStatistics">
                    <br>
                    ¿Desea eliminar el Statistics?<br><br>                    
                    <a href="javascript:eliminarStatistics();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarStatistics();" class="k-button">Cancelar</a>
                        
                    
            </div>
            
            
            <div id="cargando" class="k-loading-mask cargador" style="display: none;"><span class="k-loading-text">Loading...</span><div class="k-loading-image"></div><div class="k-loading-color"></div></div>
            
            <script>
                
                var tipoChallenge = [

                <%  TipoChallengueI tipoChallenge = new TipoChallengueI();
                    PyVO eTipoChallengue = new PyVO(tipoChallenge.cantCols);
                    List<String> where1 = new ArrayList<String>();

                    //  where.add(eTipoPlace.getWhereFieldValue(tipoPlace, tipoPlace.descTipo, descripcion));
                    List<PyVO> ents1 = PyEntidadHLP.getList(eTipoChallengue, tipoChallenge, where1, DatabaseConstant.conexionDefault);
                    String lss1 = "";
                    for (PyVO dato : ents1) {
                        lss1 += "{ \"descripcion\": \"" + dato.getValue(tipoChallenge.nombre) + "\"},";
                    }
                    if (lss1.endsWith(",")) {
                        lss1 = lss1.substring(0, lss1.length() - 1);
                    }
                    out.print(lss1);
                %>
                ];
                
                 var challenge = [

                <%  ChallengueI challenge = new ChallengueI();
                    PyVO eChallenge = new PyVO(challenge.cantCols);
                    List<String> where2 = new ArrayList<String>();

                    //  where.add(eTipoPlace.getWhereFieldValue(tipoPlace, tipoPlace.descTipo, descripcion));
                    List<PyVO> ents2 = PyEntidadHLP.getList(eChallenge, challenge, where2, DatabaseConstant.conexionDefault);
                    String lss2 = "";
                    for (PyVO dato : ents2) {
                        lss2 += "{ \"descripcion\": \"" + dato.getValue(challenge.nombre) + "\"},";
                    }
                    if (lss2.endsWith(",")) {
                        lss2 = lss2.substring(0, lss2.length() - 1);
                    }
                    out.print(lss2);
                %>
                ];
                
                var userApp = [

                <%  UserAppI userApp = new UserAppI();
                    PyVO eUserApp = new PyVO(challenge.cantCols);
                    List<String> where3 = new ArrayList<String>();

                    //  where.add(eTipoPlace.getWhereFieldValue(tipoPlace, tipoPlace.descTipo, descripcion));
                    List<PyVO> ents3 = PyEntidadHLP.getList(eUserApp, userApp, where3, DatabaseConstant.conexionDefault);
                    String lss3 = "";
                    for (PyVO dato : ents3) {
                        lss3 += "{ \"descripcion\": \"" + dato.getValue(userApp.idUser) + "\"},";
                    }
                    if (lss3.endsWith(",")) {
                        lss3 = lss3.substring(0, lss3.length() - 1);
                    }
                    out.print(lss3);
                %>
                ];
                
                
                
                
                 $(document).ready(function() {
                    kendo.culture("es-AR");            
                    $("#iconButtonNuevo").kendoButton({
                        spriteCssClass: "k-icon k-i-plus"
                    }); 
                    $("#iconButtonRefresh").kendoButton({
                        spriteCssClass: "k-icon k-i-refresh"
                    });                     
                    if (!$("#windowStatistics").data("kendoWindow")) {
                        $("#windowStatistics").kendoWindow({
                            width: "300px",
                            title: "ABM Statistics",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarStatistics").data("kendoWindow")) {
                        $("#windowEliminarStatistics").kendoWindow({
                            width: "300px",
                            title: "Eliminar Statistics",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    
                    
                    $("#challengueR").kendoAutoComplete({
                        dataTextField: "descripcion",
                        dataSource: challenge,
                        filter: "contains",
                        placeholder: "Selecione Challenge...",
                        separator: ", "
                    });
                    
                    $("#userR").kendoAutoComplete({
                        dataTextField: "descripcion",
                        dataSource: userApp,
                        filter: "contains",
                        placeholder: "Selecione idUser...",
                        separator: ", "
                    });
                    
                    $("#tipoChallengueR").kendoAutoComplete({
                        dataTextField: "descripcion",
                        dataSource: tipoChallenge,
                        filter: "contains",
                        placeholder: "Selecione tipo challenge...",
                        separator: ", "
                    });
                    
                    
                    
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
 
                DataGrid dg = new DataGrid("Statistics", columnas, -1, dl); 
 
                columnas.add(new DataGridColumn("id", "id", "1", "", "", "0", "")); 
                columnas.add(new DataGridColumn("user", "user", "1", "", "", "1", "")); 
                columnas.add(new DataGridColumn("challengue", "challengue", "1", "", "", "2", "")); 
                columnas.add(new DataGridColumn("tipoChallengue", "tipoChallengue", "1", "", "", "3", "")); 
                columnas.add(new DataGridColumn("time", "time", "1", "", "", "4", "")); 
                columnas.add(new DataGridColumn("hits", "hits", "1", "", "", "5", "")); 
                out.println(dg.toHTML(false, true, true, "", 0)); 
            
            
            
            %>

</div>


        
</body>
</html> 