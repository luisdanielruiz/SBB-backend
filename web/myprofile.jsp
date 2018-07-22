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
    
    <head> <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <%=WebDependencies.getCSSandJS()%> 
         <script src="js/Chart.js"></script>
          <script src="js/boostrap.min.js"></script>
        <meta name="description" content=""> 
        <meta name="author" content=""> 
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
        <title>Smart Boxing Bag</title> 
    </head> 
    <body>
        <div style="background-color:#ff0000;box-shadow: 0px 0px 9px;">
            <h1 class="title" style="text-align: center; color: white">Mi perfil</h1>
        </br>
        </div>
       
            <div class="col-lg-2" style="background-color: #ff0000; height: 1080px;">
          <h2 style="text-align: center; color: white;">Menu</h2>
          </br>
          <ul style="margin-top: 30px;list-style-type:none; color: white;">
              <li id="home" style="margin-bottom: 20px;"><i style="margin-right:  10px;" class="material-icons">home</i><span style="font-size: 25px; position: absolute;margin-top: -4px;"> Inicio</span></li>
              <li href="myprofile.jsp" id="profile" style="margin-bottom: 20px;"><i style="margin-right:  10px;" class="material-icons">perm_identity</i><span style="font-size: 25px; position: absolute;margin-top: -4px;"> Mi perfil</span></li>
              <li id="logout" style="margin-bottom: 20px;"><i style="margin-right:  10px;" class="material-icons">exit_to_app</i><span style="font-size: 25px; position: absolute;margin-top: -4px;"> Salir</span></li>
          </ul>
            </div>
            <div class="col-lg-10">
              <div class="row">   
                  <h1>Mi perfil</h1>
           </div>
           </div>
    
    </body> 
</html> 

