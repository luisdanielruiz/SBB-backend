<%-- 
    Document   : reporte
    Created on : Aug 16, 2016, 9:48:54 PM
    Author     : admin
--%>

<%@page import="GS.Testo.RespuestaService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte encuesta</title>
    </head>
    <body>
        <h1>Reporte encuesta</h1>
        <%
            RespuestaService.generarReporte(request.getParameter("cod"));
        
        %>
        
        <a href="repo.xlsx" target="_new" >bajar </a>
    </body>
</html>
