<%-- 
    Document   : ConfirmacionMail
    Created on : 23/11/2017, 15:21:16
    Author     : usuario
--%>
<%@page import="Py.DB.VO.Helpers.PyEntidadHLP"%>
<%@page import="AplicationConstant.DatabaseConstant"%>
<%@page import="java.util.List"%>
<%@page import="Py.DB.VO.PyVO"%>
<%@page import="GS.Base.*"%>
<%@page import="Entidades.Helpers.EntidadHLP"%>  
<%@page import="Entidades.EntidadVO"%> 
<%@page import="java.util.ArrayList"%> 
<%@page import="java.util.Vector"%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>T&S</title>
    </head>
    <body>
        <img src="http://.net/logo.png" style="widht:200px; height:150px; margin:10px auto;">
        <h1>Mail confirmado.</h1>
        <p>Gracias.</p>
        <%          UserAppI usuario = new UserAppI();
                    PyVO e = new PyVO(usuario.cantCols);
                    String mail = request.getParameter("mail");
                   
                    if (mail == null || mail==""){
                        out.print("{ \"status\" : \"error\",\"result\": \"No se encuentra mail.\"}");
                    }else{
                        List<String> where = new ArrayList<String>();

                        where.add(e.getWhereFieldValue(usuario, usuario.email, mail));
                        List<PyVO> ents1 = PyEntidadHLP.getList(e, usuario, where, DatabaseConstant.conexionDefault);

                        ents1.get(0).setValue(usuario.mailConfirmado,"si");
                        PyEntidadHLP.doUpdate(ents1.get(0), usuario,where, DatabaseConstant.conexionDefault);
                        if(ents1.get(0).getValue(usuario.mailConfirmado)=="si"){
                            out.print(" mail confirmado "+mail);
                        }else{
                      
                            out.print("no funcó "+mail);
                        }
                    }
                %>
    </body>
</html>
