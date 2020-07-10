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
    <head> <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <%=WebDependencies.getCSSandJS()%> 
        <meta name="description" content=""> 
        <meta name="author" content=""> 
 
        <title>ABM SBB</title> 
        <style>
    .containerIntro{ 
    background: #ffffff;
    box-shadow: 0 2px 9px 0 rgba(0,0,0,0.20);
    width: 95%;
    height: auto;
    margin-right: 10px;
    margin-left: 10px;
    text-align: center;
    margin-top: 10%;
    margin-bottom: 15px;
    border-radius: 8px;
}
.tituloIntro{
    color: red;
}
.pIntro{
   text-align: left;
   font-size: 22px;
   margin-top: 8%;
}
ulIntro{
   text-align: left !important;
   font-size: 22px;
   margin-top: 8%;
}
.imgIntro{
    width: 45%;
}
.containerImgIntro{
    
}
        </style>
    </head> 
    <body> 
        <%@include file="menu.jsp" %>
        <div class="row">
            <div class="containerIntro">
                <div class="col-md-6">
                    <div>
                        <h1 class="tituloIntro">Smart Boxing Bag App</h1>
                        <br>
                        <h2>Bienvenido al panel de Administración</h2>
                        <p class="pIntro">En el panel de administración puede crear Usuarios de la Aplicación, usuarios para la administración y crear nuevos desafíos.

                        </p>
                        <p class="pIntro">
                            1. Seleccione una opción del menú. <br>
                            2. Crea, modifica o elimina la aplicación.
                            <br>
                            <br>
                            </p>
                        <h5> Created and Designed by:<h5>
                            Daniel Ruiz<br>
                            Franco Rosi<br>
                        <br>
                        <span>All Rights reserved Smart Boxing Bag® 2018<br>
                            Escuela de Arte Multimedial Da Vinci -
                                Ciudad Autónoma de Buenos Aires, Argentina. </span>
                    </div>
                    </div>
                <div class="col-md-6" class="containerImgIntro">
                    <img class="imgIntro" alt="" src="img/logoApp2.png">
                    <img class="imgIntro" alt="" src="img/logoApp.png"> 
                </div>
            </div>
           
        </div>
        </div>
    </body> 
</html> 
