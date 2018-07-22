<%@page import="GS.Base.UsuarioService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%
    
    if (!UsuarioService.comprobarSession(request)) 
        response.sendRedirect("Login.jsp"); 
    else    
        response.sendRedirect("appClean.jsp");

%>