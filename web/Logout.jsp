<%-- 
    Document   : Logout
    Created on : Jul 10, 2020, 2:19:09 PM
    Author     : daniel
--%>

<%@page import="GS.Base.UsuarioService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%
    UsuarioService.logout(request);
    response.sendRedirect("index.jsp"); 
%>