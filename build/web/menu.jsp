<%@page import="GS.UTIL.ReporteModelo.ReporteModeloVO"%>
<%@page import="Entidades.Helpers.EntidadHLP"%>
<%@page import="Entidades.EntidadVO"%>
<%@page import="java.util.Vector"%>
<%@page import="GS.GRIApp.Navigation.HorizontalMenu"%>
<%
    try{
            HorizontalMenu hm = new HorizontalMenu("Smart Boxing Bag ABM ",
                "["+
                    "{\"label\": \"Inicio\", \"acc\": \"appClean.jsp\",\"child\": []}," +
                    "{\"label\": \"Usuarios admin\", \"acc\": \"Usuario.jsp\",\"child\": []}," +
                    "{\"label\": \"Usuarios APP\", \"acc\": \"UserApp.jsp\",\"child\": []}," +
                    "{\"label\": \"Challenge\", \"acc\": \"Challengue.jsp\",\"child\": []}," +
                    "{\"label\": \"Categoria Challenges\", \"acc\": \"Categories.jsp\",\"child\": []}," +
                    "{\"label\": \"Stadisticas\", \"acc\": \"Statistics.jsp\",\"child\": []}," +
                    "{\"label\": \"Tipo Challenge\", \"acc\": \"TipoChallengue.jsp\",\"child\": []}" +         
                "]");
            
            out.print(hm.toHTML("appClean.jsp"));
    }catch (Exception exeee){
        System.out.println(exeee.getStackTrace()+"No se cargo el menu correctamente");
    }
%> 