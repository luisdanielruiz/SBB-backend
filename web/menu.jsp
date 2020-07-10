<%@page import="GS.UTIL.ReporteModelo.ReporteModeloVO"%>
<%@page import="Entidades.Helpers.EntidadHLP"%>
<%@page import="Entidades.EntidadVO"%>
<%@page import="java.util.Vector"%>
<%@page import="GS.GRIApp.Navigation.HorizontalMenu"%>
<%
    try{
            HorizontalMenu hm = new HorizontalMenu("Smart Boxing Bag",
                "["+
                    "{\"label\": \"Inicio\", \"acc\": \"appClean.jsp\",\"child\": []}," +
                    "{\"label\": \"Usuarios Admin\", \"acc\": \"Usuario.jsp\",\"child\": []}," +
                    "{\"label\": \"Usuarios App\", \"acc\": \"UserApp.jsp\",\"child\": []}," +
                    "{\"label\": \"Desafíos\", \"acc\": \"Challengue.jsp\",\"child\": []}," +
                    "{\"label\": \"Categorías\", \"acc\": \"Categories.jsp\",\"child\": []}," +
                    "{\"label\": \"Estadísticas\", \"acc\": \"Statistics.jsp\",\"child\": []}," +
                    "{\"label\": \"Tipos de Desafíos\", \"acc\": \"TipoChallengue.jsp\",\"child\": []}," +
                    "{\"label\": \"Cerrar Sesión\", \"acc\": \"Logout.jsp\",\"child\": []}" +
                "]");
            
            out.print(hm.toHTML("appClean.jsp"));
    }catch (Exception exeee){
        System.out.println(exeee.getStackTrace()+"No se cargo el menu correctamente");
    }
%> 