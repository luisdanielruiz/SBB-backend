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
                    "{\"label\": \"Desaf�os\", \"acc\": \"Challengue.jsp\",\"child\": []}," +
                    "{\"label\": \"Categor�as\", \"acc\": \"Categories.jsp\",\"child\": []}," +
                    "{\"label\": \"Estad�sticas\", \"acc\": \"Statistics.jsp\",\"child\": []}," +
                    "{\"label\": \"Tipos de Desaf�os\", \"acc\": \"TipoChallengue.jsp\",\"child\": []}," +
                    "{\"label\": \"Cerrar Sesi�n\", \"acc\": \"Logout.jsp\",\"child\": []}" +
                "]");
            
            out.print(hm.toHTML("appClean.jsp"));
    }catch (Exception exeee){
        System.out.println(exeee.getStackTrace()+"No se cargo el menu correctamente");
    }
%> 