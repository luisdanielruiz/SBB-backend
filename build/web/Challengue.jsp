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
        <script src="ChallengueABM.js"></script> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>Challengue</title> 
 
    </head> 
    <body>      
 <%@include file="menu.jsp" %>
        <span id="notification" style="display:none;"></span>
        <div id="example">
            <div class="bttGridContainer">
                <em id="iconButtonNuevo" onclick="nuevoChallengue();"><span class="k-sprite ">Nuevo</span></em>
                <em style="display: none" id="iconButtonRefresh" onclick="actualizarChallengueTelerik();"><span class="k-sprite">Refresh</span></em>
            </div>
            
            <div id="windowChallengue"> 
               <form id="ABMChallengueForm"> 
                   <input type="hidden" name="accChallengue" id="accChallengue" value="guardar"> 
                   <input type="hidden" name="fromABMChallengue" id="fromABMChallengue" value=""> 
                   <ul class="fieldlist"> 
                        <li style="display: none">
                            <label for="simple-input">idChallengue</label>
                            <input name="idChallengue" id="idChallengueR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">Tipo Challenge</label>
                            <input name="tipoChallengue" id="tipoChallengueR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">Categoria</label>
                            <input name="nombre" id="nombreR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">Hits</label>
                            <input name="hits" id="hitsR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                        <li>
                            <label for="simple-input">Time</label>
                            <input name="time" id="timeR" type="text" class="k-textbox" style="width: 100%;" />
                        </li>
                       <li>
                            <a href="javascript:guardarChallengue();" class="k-button">Guardar</a>
                       </li>
						</ul> 
		</form> 
         </div>
            <div id="windowEliminarChallengue">
                    <br>
                    ¿Desea eliminar el Challengue?<br><br>                    
                    <a href="javascript:eliminarChallengue();" class="k-button">Elimnar</a> &nbsp;&nbsp;
                    <a href="javascript:cancelarEliminarChallengue();" class="k-button">Cancelar</a>
                        
                    
            </div>
            
            
            <div id="cargando" class="k-loading-mask cargador" style="display: none;"><span class="k-loading-text">Loading...</span><div class="k-loading-image"></div><div class="k-loading-color"></div></div>
            
            <script>
                
                var categorias = [

                <%  CategoriesI categoria = new CategoriesI();
                    PyVO eCategories = new PyVO(categoria.cantCols);
                    List<String> where1 = new ArrayList<String>();

                    //  where.add(eTipoPlace.getWhereFieldValue(tipoPlace, tipoPlace.descTipo, descripcion));
                    List<PyVO> ents1 = PyEntidadHLP.getList(eCategories, categoria, where1, DatabaseConstant.conexionDefault);
                    String lss1 = "";
                    for (PyVO dato : ents1) {
                        lss1 += "{ \"descripcion\": \"" + dato.getValue(categoria.nombre) + "\"},";
                    }
                    if (lss1.endsWith(",")) {
                        lss1 = lss1.substring(0, lss1.length() - 1);
                    }
                    out.print(lss1);
                %>
                ];
                
      var tipoChallenge = [

                <%  TipoChallengueI tipoChallenge = new TipoChallengueI();
                    PyVO eTipoChallengue = new PyVO(tipoChallenge.cantCols);
                    List<String> where2 = new ArrayList<String>();

                    //  where.add(eTipoPlace.getWhereFieldValue(tipoPlace, tipoPlace.descTipo, descripcion));
                    List<PyVO> ents2 = PyEntidadHLP.getList(eTipoChallengue, tipoChallenge, where2, DatabaseConstant.conexionDefault);
                    String lss2 = "";
                    for (PyVO dato : ents2) {
                        lss2 += "{ \"descripcion\": \"" + dato.getValue(tipoChallenge.nombre) + "\"},";
                    }
                    if (lss2.endsWith(",")) {
                        lss2 = lss2.substring(0, lss2.length() - 1);
                    }
                    out.print(lss2);
                %>
                ];
                
                 $(document).ready(function() {
                     actualizarChallengueTelerik();
                    kendo.culture("es-AR");            
                    $("#iconButtonNuevo").kendoButton({
                        spriteCssClass: "k-icon k-i-plus"
                    }); 
                    $("#iconButtonRefresh").kendoButton({
                        spriteCssClass: "k-icon k-i-refresh"
                    });                     
                    if (!$("#windowChallengue").data("kendoWindow")) {
                        $("#windowChallengue").kendoWindow({
                            width: "300px",
                            title: "ABM Challengue",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    if (!$("#windowEliminarChallengue").data("kendoWindow")) {
                        $("#windowEliminarChallengue").kendoWindow({
                            width: "300px",
                            title: "Eliminar Challengue",
                            visible: false,
                            modal: true
                            /*close: onClose*/
                        });
                    }
                    
                    $("#nombreR").kendoAutoComplete({
                        dataTextField: "descripcion",
                        dataSource: categorias,
                        filter: "contains",
                        placeholder: "Selecione Categoria...",
                        
                    });
                    
                    $("#tipoChallengueR").kendoAutoComplete({
                        dataTextField: "descripcion",
                        dataSource: tipoChallenge,
                        filter: "contains",
                        placeholder: "Selecione Tipo Challenge...",
                     
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
 
                DataGrid dg = new DataGrid("Challengue", columnas, -1, dl); 
 
                //columnas.add(new DataGridColumn("idChallengue", "idChallengue", "1", "", "", "0", "")); 
                columnas.add(new DataGridColumn("Tipo Challengue", "tipoChallengue", "1", "", "", "0", "")); 
                columnas.add(new DataGridColumn("Categoria Challenge", "nombre", "1", "", "", "1", "")); 
                columnas.add(new DataGridColumn("Hits Challenge", "hits", "1", "", "", "2", "")); 
                columnas.add(new DataGridColumn("Time Challenge", "time", "1", "", "", "3", "")); 
                out.println(dg.toHTML(false, true, true, "", 0)); 

            %>

</div>


        
</body>
</html> 