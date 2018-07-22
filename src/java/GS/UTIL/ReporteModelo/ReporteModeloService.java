package GS.UTIL.ReporteModelo;

import AplicationConstant.DatabaseConstant;
import java.io.IOException; 

import java.io.PrintWriter; 
import javax.servlet.ServletException; 
import java.util.Date; 
import java.util.Vector; 
import Entidades.EntidadVO; 
import Entidades.Helpers.EntidadHLP; 
import GS.Base.UsuarioService;
import java.sql.ResultSetMetaData;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
@WebServlet(name = "ReporteModeloService", urlPatterns = {"/ReporteModeloService"}) 
public class ReporteModeloService extends HttpServlet { 
    
    public static String tagparam="<param# />";
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        response.setContentType("text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter(); 
        try { 
            if (UsuarioService.comprobarSession(request)){
               if (request.getParameter("accReporteModelo").equals("guardar")){ 
                   out.println(guardar(request)); 
               }if (request.getParameter("accReporteModelo").equals("eliminar")){ 
                   out.println(eliminar(request)); 
               }else if (request.getParameter("accReporteModelo").equals("getData")){ 
                   out.println(getData(request)); 
               }else if (request.getParameter("accReporteModelo").equals("reporteCSV")){ 
                   String rep =ReporteCSV(request); 
                   out.println("<a href=\""+rep+"\">"+rep+"</a>"); 
               }else if (request.getParameter("accReporteModelo").equals("reporteXLSX")){                    
                   String rep =Reporte(request);
                   out.println("<a href=\""+rep+"\">"+rep+"</a>"); 
               } 
            }else{
                   response.setContentType("text/html;charset=UTF-8");
                   out = response.getWriter(); 
                   out.println("Login.jsp");
            } 
 
 
        } catch (Exception exe){
            exe.printStackTrace();
        }finally {             
            out.close(); 
        } 
    } 
 
    public static String guardar(HttpServletRequest request){ 
        try{ 
           //if(!UsuarioService.comprobarSession(usuario))
           // throw new Exception("Error de seguridad. Ingrese nuevamente");
           EntidadVO  nuevoDB = new ReporteModeloVO();
           nuevoDB.getDataFrom(request);


           Vector<String> sqls = new Vector<String>();
           //sqls.add(LogService.loguearSQL(new Date(), usuario.usuario, "Alta de ReporteModelo " ,request.getRemoteAddr()));
           if(request.getParameter("fromABMReporteModelo").equals("n")){ 
               sqls.add(EntidadHLP.SQLdoInsert(nuevoDB));
           }else{ 
               sqls.add(EntidadHLP.sqlUpdatefromPK(nuevoDB));
           } 
 
            EntidadHLP.ejecutarTransaccion(nuevoDB, sqls, "Error en alta de ReporteModelo", "ReporteModelo", "");
            return "OK#1231321";             
        }catch (Exception exe){ 
            return "ER"; 
        } 
 
    } 
 
    public static String eliminar(HttpServletRequest request){ 
        try{ 
        //if(!UsuarioService.comprobarSession(usuario))
        //      throw new Exception("Error de seguridad. Ingrese nuevamente");
            ReporteModeloVO editarDB = new ReporteModeloVO();
            editarDB.setValorPK(request.getParameter(ReporteModeloVO.codigo)+"");

            editarDB.setValorAtributo(ReporteModeloVO.archivos,request.getParameter(ReporteModeloVO.archivos)+"");
            editarDB.setValorAtributo(ReporteModeloVO.tipo,request.getParameter(ReporteModeloVO.tipo)+"");
            editarDB.setValorAtributo(ReporteModeloVO.textos,request.getParameter(ReporteModeloVO.textos)+"");
            editarDB.setValorAtributo(ReporteModeloVO.descripcion,request.getParameter(ReporteModeloVO.descripcion)+"");
            editarDB.setValorAtributo(ReporteModeloVO.descripTextos,request.getParameter(ReporteModeloVO.descripTextos)+"");
            editarDB.setValorAtributo(ReporteModeloVO.html,request.getParameter(ReporteModeloVO.html)+"");
            editarDB.setValorAtributo(ReporteModeloVO.explicacion,request.getParameter(ReporteModeloVO.explicacion)+"");
            editarDB.setValorAtributo(ReporteModeloVO.descripArchivos,request.getParameter(ReporteModeloVO.descripArchivos)+"");
        Vector<String> sqls = new Vector<String>();
 //       sqls.add(EntidadHLP.SQLdoDeleteByPK(editarDB));

        //sqls.add(LogService.loguearSQL(new Date(), usuario.usuario, "Modificacion de ReporteModelo " ,request.getRemoteAddr()));
        EntidadHLP.ejecutarTransaccion(editarDB, sqls, "Error en modificacion de ReporteModelo", "ReporteModelo", "");
 
 
            return "OK#1231321";             
        }catch (Exception exe){ 
            return "ER"+exe.toString(); 
        } 
 
    } 
 
    public static String getData(HttpServletRequest request){ 
        try{ 
        //if(!UsuarioService.comprobarSession(usuario))
        //      throw new Exception("Error de seguridad. Ingrese nuevamente");
            EntidadVO e =  new ReporteModeloVO();
            String retorna = "[";
            Vector <EntidadVO> ents = EntidadHLP.getByAtribute(e);
            for (int i=0; i<ents.size(); i++){
                retorna+= ents.get(i).toJSON()+",";
            }
            if (retorna.endsWith(",")){ 
               retorna = retorna.substring(0,retorna.length()-1); 
            } 
retorna+="]"; 
            return  retorna ;
        }catch (Exception exe){ 
            return "ER"; 
        } 
 
    } 

    public  static String Reporte(HttpServletRequest request)throws Exception{
        EntidadVO modelo = new ReporteModeloVO();
        modelo.setValorPK(request.getParameter(ReporteModeloVO.codigo));
        modelo = EntidadHLP.getByPK(modelo);
        
        String consulta = request.getParameter("consulta");
       
        String parametros[] = request.getParameterValues("parametros");
        
        
        //EntidadVO modelo,String parametros[],  String consulta
        String tituloExtra="";
        
        try{
          /*  if (modelo.getValorAtributoTrim(ReporteModeloVO.descripcion).contains("Estado de cuenta")){
                EntidadVO cli = new ClienteVO();
                cli.setValorPK(parametros[2]);
                cli = EntidadHLP.getByPK(cli);
                if (cli != null){
                    tituloExtra= cli.getValorAtributoTrim(ClienteVO.Nombre);
                }
                modelo.setValorAtributo(ReporteModeloVO.descripcion, "Estado de cuenta: "+tituloExtra);
            }*/
            
            
        }catch (Exception exe){}
        
        
        
        
        try{
            
            FileOutputStream out = new FileOutputStream(DatabaseConstant.localReportes+"/rep"+modelo.getPK().getValor()+".xlsx");
                // create a new workbook
            XSSFWorkbook wb = ReporteWB(new XSSFWorkbook(),-1, 0, 0, true,true, modelo, parametros,   consulta);
            

            wb.write(out);
            out.close();

            
            // Empiezo a armar el excel
            return DatabaseConstant.wwwReportes+"/rep"+modelo.getPK().getValor()+".xlsx";
            
        }catch (Exception exe){    
            exe.printStackTrace();
            return exe.getLocalizedMessage();            
        }finally{           
        }        
    }
    
    public  static XSSFWorkbook ReporteWB(XSSFWorkbook wb,int nroSheet, int lineaDesde, int columnaDesde, boolean conTitulo,boolean headerYfooter, EntidadVO modelo,String parametros[],  String consulta)throws Exception{
        //if(!UsuarioService.comprobarSession(usuario))
          //    throw new Exception("Error de seguridad. Ingrese nuevamente");

        ResultSet rs = null;
        if (consulta!=null && !consulta.equals("")){
            rs = ReporteResultSet(consulta);
        }else{
            rs = ReporteResultSet(modelo, parametros);
        }
        
            
        String txtParametros="";
        
        try{
            String listaValoresParam[]= modelo.getValorAtributo(ReporteModeloVO.descripTextos).split("\\|");

            String tiposParam[]= modelo.getValorAtributo(ReporteModeloVO.descripArchivos).split("\\|");


            for (int i=0; i<listaValoresParam.length; i++){
                String p = parametros[i];
                if (tiposParam[i].equals("2")){
                    String separo[]=p.split("\\-");
                    txtParametros += listaValoresParam[i]+": "+separo[2]+"/"+separo[1]+"/"+separo[0]+"   ";
                }else{
                    txtParametros += listaValoresParam[i]+": "+p+"   ";
                }
            }
        }catch (Exception exe){
            
        }
        
        
        try{
            ResultSetMetaData meta = rs.getMetaData();


                // create a new workbook
            
            XSSFDataFormat df = wb.createDataFormat();
                // create a new sheet
            XSSFSheet s = null;
            if (nroSheet == -1){
                s = wb.createSheet();
            }else{
                s = wb.getSheetAt(nroSheet);
            }
            //wb.setSheetName(0, modelo.descripcion); // numero de Shet - nombre
                // declare a row object reference
            XSSFRow r = null;
                // declare a cell object reference
            XSSFCell c = null;
            
            

            //s.autoSizeColumn((short)1);
            ////////  ESTILOS!!!///////////////////////////////////////////////////////
            XSSFCellStyle encabezado = wb.createCellStyle();
            XSSFFont encabezadoFont = wb.createFont();
            encabezadoFont.setFontHeightInPoints((short) 16);
            encabezadoFont.setBoldweight((short)XSSFFont.BOLDWEIGHT_BOLD);
            encabezadoFont.setColor(HSSFColor.BLACK.index);            
            
            encabezado.setFont(encabezadoFont);
            
            
            
            XSSFFont datoTextoFont = wb.createFont();
            datoTextoFont.setFontHeightInPoints((short) 10);
            datoTextoFont.setColor(HSSFColor.BLACK.index);
            
            
            XSSFCellStyle fechasStyle = wb.createCellStyle();            
            fechasStyle.setBorderBottom((short)1);
            fechasStyle.setBorderLeft((short)1);
            fechasStyle.setBorderRight((short)1);
            fechasStyle.setBorderTop((short)1);
            fechasStyle.setFont(datoTextoFont);
            fechasStyle.setDataFormat(wb.createDataFormat().getFormat("d/m/yyyy"));

            XSSFCellStyle numeroSStyle = wb.createCellStyle();            
            numeroSStyle.setBorderBottom((short)1);
            numeroSStyle.setBorderLeft((short)1);
            numeroSStyle.setBorderRight((short)1);
            numeroSStyle.setBorderTop((short)1);
            numeroSStyle.setFont(datoTextoFont);
            numeroSStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));

            XSSFCellStyle datoTexto = wb.createCellStyle();            
            datoTexto.setBorderBottom((short)1);
            datoTexto.setBorderLeft((short)1);
            datoTexto.setBorderRight((short)1);
            datoTexto.setBorderTop((short)1);
            datoTexto.setFont(datoTextoFont);
            
            XSSFCellStyle styleParam = wb.createCellStyle();            
            
            styleParam.setFont(datoTextoFont);
            
            

            XSSFCellStyle titulo = wb.createCellStyle();
            XSSFFont tituloFont = wb.createFont();
            tituloFont.setFontHeightInPoints((short) 10);
            tituloFont.setBoldweight((short)XSSFFont.BOLDWEIGHT_BOLD);
            tituloFont.setColor(HSSFColor.WHITE.index);
            titulo.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            titulo.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
            titulo.setBorderBottom((short)1);
            titulo.setBorderLeft((short)1);
            titulo.setBorderRight((short)1);
            titulo.setBorderTop((short)1);
            titulo.setFont(tituloFont);


            //////////FIN  ESTILOS///////////////////////////////////////////////////////////////
            if (headerYfooter){
                Header header = s.getHeader();
                header.setRight("Hoja " + HSSFHeader.page() + " de " + HSSFHeader.numPages());


                Footer foot = s.getFooter();
                foot.setRight("Fecha de emisión: " + HSSFFooter.date() );
            }
            //**************FIN*FOOTER********************************

            XSSFPrintSetup ps = s.getPrintSetup();
            
            ps.setPaperSize(XSSFPrintSetup.A4_PAPERSIZE);
            ps.setFooterMargin(0.2);


            s.setMargin(XSSFSheet.TopMargin, (double) .10);
            s.setMargin(XSSFSheet.BottomMargin, (double) .50);
            s.setMargin(XSSFSheet.LeftMargin, (double) .10);
            s.setMargin(XSSFSheet.RightMargin, (double) .10);
            wb.setRepeatingRowsAndColumns(0,0,meta.getColumnCount(), 0,  5);
            
            
            
            
            
            int cantiadadColumnas=0;
            int columnIndex =columnaDesde;
            int rowIndex =lineaDesde;

            ///// CREO FILA DE TITULOS/////////////////////////////////

            if (conTitulo){
            
                r  = s.getRow(rowIndex)==null? s.createRow(rowIndex): s.getRow(rowIndex);
                c = r.createCell(columnIndex);            
                c.setCellValue(modelo.getValorAtributo(ReporteModeloVO.descripcion));
                c.setCellStyle(encabezado);                

                c = r.createCell(columnIndex+1);
                c = r.createCell(columnIndex+2);
                c = r.createCell(columnIndex+3);
                c = r.createCell(columnIndex+4);
                c = r.createCell(columnIndex+5);
                c = r.createCell(columnIndex+6);
                c = r.createCell(columnIndex+7);
                s.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,(short)columnIndex,(short)(columnIndex+7)));



                rowIndex++;
                columnIndex =columnaDesde;
                r  = s.getRow(rowIndex)==null? s.createRow(rowIndex): s.getRow(rowIndex);
                c = r.createCell(columnIndex);
                rowIndex++;


                 r  = s.getRow(rowIndex)==null? s.createRow(rowIndex): s.getRow(rowIndex);
                c = r.createCell(columnIndex);            
                c.setCellValue(txtParametros);
                c.setCellStyle(styleParam);                

                c = r.createCell(columnIndex+1);
                c = r.createCell(columnIndex+2);
                c = r.createCell(columnIndex+3);
                c = r.createCell(columnIndex+4);
                c = r.createCell(columnIndex+5);
                c = r.createCell(columnIndex+6);
                c = r.createCell(columnIndex+7);
                s.addMergedRegion(new CellRangeAddress(rowIndex,rowIndex,(short)columnIndex,(short)(columnIndex+7)));


                rowIndex++;
                rowIndex++;
                columnIndex =columnaDesde;
            }
            ///
            

            r  = s.getRow(rowIndex)==null? s.createRow(rowIndex): s.getRow(rowIndex);
            
            
            for (int i=0; i<meta.getColumnCount(); i++){                
                c = r.createCell(columnIndex);
                columnIndex++;
                c.setCellValue(meta.getColumnName(columnIndex-columnaDesde));
                c.setCellStyle(titulo);                
                //meta.getColumnTypeName(columnIndex);
            }
            rowIndex++;
            columnIndex =columnaDesde;

            while (rs.next()){            
                r  = s.getRow(rowIndex)==null? s.createRow(rowIndex): s.getRow(rowIndex);
                for (int i=1; i<=meta.getColumnCount(); i++){                
                    c = r.createCell(columnIndex);
                    columnIndex++;
                    if (meta.getColumnTypeName(i).toLowerCase().contains("num")||
                            meta.getColumnTypeName(i).toLowerCase().contains("float")||
                            meta.getColumnTypeName(i).toLowerCase().contains("double")||
                            meta.getColumnTypeName(i).toLowerCase().contains("int")
                            ){
                        try{
                            c.setCellValue(rs.getDouble(i));
                        }catch(Exception ese){}
                        c.setCellStyle(numeroSStyle);                
                        
                        
                    }else if (meta.getColumnTypeName(i).toLowerCase().contains("date")
                           
                            ){
                        try{
                            c.setCellValue(rs.getDate(i));  
                        }catch(Exception ese){}
                        c.setCellStyle(fechasStyle);                                        
                        
                    }
                    else{
                        String valor = rs.getString(i);
                        if (valor!= null && valor.startsWith("funcct")){
                            try{
                                c.setCellFormula(valor.substring(6,valor.length()).replaceAll("#", (rowIndex+1)+"")
                                                    .replaceAll("\\|\\.\\|", (rowIndex)+""));
                            }catch(Exception ese){
                                ese.printStackTrace();
                               }
                                                                 
                        }else{

                            try{
                                c.setCellValue(rs.getString(i));
                            }catch(Exception ese){}
                            c.setCellStyle(datoTexto);                
                        }
                    }
                    

                }
                rowIndex++;
                columnIndex =columnaDesde;
            }
            
            for (int i=0; i<meta.getColumnCount(); i++){  
                s.autoSizeColumn(i);
            }
            

//            wb.write(out);
//            out.close();

            rs.close();
            // Empiezo a armar el excel
            return wb;
            
        }catch (Exception exe){    
            exe.printStackTrace();
            return null;
        }finally{
            rs.close();
        }
        
        
    }
    
    
    public  static String ReporteCSV(HttpServletRequest request)throws Exception{
        EntidadVO modelo = new ReporteModeloVO();
        modelo.setValorPK(request.getParameter(ReporteModeloVO.codigo));
        modelo = EntidadHLP.getByPK(modelo);
        
        String consulta = request.getParameter("consulta");
       
        String parametros[] = request.getParameterValues("parametros");
        
        //ReporteModeloFlex modelo,String parametros[],  String consulta

        ResultSet rs = null;
        if (consulta!= null && !consulta.equals("")){
            rs = ReporteResultSet(consulta);
        }else{
            rs = ReporteResultSet(modelo, parametros);
        }
        
            
        String txtParametros="";
        
        try{
            String listaValoresParam[]= modelo.getValorAtributo(ReporteModeloVO.descripTextos).split("\\|");

            String tiposParam[]= modelo.getValorAtributo(ReporteModeloVO.descripArchivos).split("\\|");


            for (int i=0; i<listaValoresParam.length; i++){
                String p = parametros[i];
                if (tiposParam[i].equals("2")){
                    String separo[]=p.split("\\-");
                    txtParametros += listaValoresParam[i]+": "+separo[2]+"/"+separo[1]+"/"+separo[0]+"   ";
                }else{
                    txtParametros += listaValoresParam[i]+": "+p+"   ";
                }
            }
        }catch (Exception exe){
            
        }
        
        
        try{
            ResultSetMetaData meta = rs.getMetaData();

            FileOutputStream out = new FileOutputStream(DatabaseConstant.localReportes+"/rep"+modelo.getPK().getValor()+".csv");
            OutputStreamWriter wb = new OutputStreamWriter(out);
            
            wb.write("Fecha de emisión: " + new Date()+"\n" );
            wb.write(modelo.getValorAtributo(ReporteModeloVO.descripcion)+"\n" );
            wb.write(txtParametros+"\n" );
          
                        
            
            for (int i=1; i<=meta.getColumnCount(); i++){                
                wb.write(meta.getColumnName(i)+";");                
            }
            
            wb.write("\n");
            DecimalFormat df = new DecimalFormat("#.##");
            
            while (rs.next()){            

                for (int i=1; i<=meta.getColumnCount(); i++){                
                    
                    if (meta.getColumnTypeName(i).toLowerCase().contains("num")||
                            meta.getColumnTypeName(i).toLowerCase().contains("float")||
                            meta.getColumnTypeName(i).toLowerCase().contains("double")||
                            meta.getColumnTypeName(i).toLowerCase().contains("int")
                            ){
                        try{
                            wb.write(df.format(rs.getDouble(i)).replaceAll(",", ".") +";");
                        }catch(Exception ese){}                        
                        
                    }else if (meta.getColumnTypeName(i).toLowerCase().contains("date")                           
                            ){
                        try{
                             wb.write(rs.getDate(i)+";");
                        
                            
                        }catch(Exception ese){}
                                                             
                        
                    }else{
                        try{
                            wb.write(rs.getString(i)+";");
                            
                        }catch(Exception ese){}
                                        
                    }
                    

                }
               wb.write("\n");
            }
            
            wb.close();

            out.close();

            rs.close();
            // Empiezo a armar el excel
            return DatabaseConstant.wwwReportes+"/rep"+modelo.getPK().getValor()+".csv";
            
        }catch (Exception exe){    
            exe.printStackTrace();
            return exe.getLocalizedMessage();            
        }finally{
            rs.close();
        }
        
        
    }

    
    
    
    
    
    
   @Deprecated
     private String getPipeArray(Object lineas[]){
            String retorna= "";
            for (int i=0; i<lineas.length; i++){
                if (lineas[i]!=null && !lineas[i].toString().trim().equals("")){
                    retorna+= lineas[i].toString()+"|";
                }
            }

            if (retorna.endsWith("|")){
                retorna = retorna.substring(0, retorna.length()-1);
            }

            return (retorna.equals("")? " ": retorna);
      }
   
    public static Vector<EntidadVO> getSelector(String SQL)throws Exception {
        
        if(SQL.contains("pass")|| SQL.contains("password") ||  SQL.contains("contra")) 
            throw new Exception("Error de seguridad. No puede hacer consultas sobre contraseñas");
        
        try{
            Vector <EntidadVO> ents = EntidadHLP.getBystagment(new SelectorVO(),SQL.replaceAll("\"", "'"));
            return ents;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        
    }

    public static  ResultSet ReporteResultSet(String sql)throws Exception{
        
        
//        EntidadVO entidad = new ReporteModeloVO();
        Connection connection = null;
        Statement est = null;
        
        Class.forName(DatabaseConstant.conexionDefault[0]).newInstance();
        connection = DriverManager.getConnection(DatabaseConstant.conexionDefault[1], DatabaseConstant.conexionDefault[2], DatabaseConstant.conexionDefault[3]);
        est = connection.createStatement();


        //System.out.println(stagment);
        java.sql.ResultSet rs = est.executeQuery(sql);

        
        return rs;
        
    }

    public  static ResultSet ReporteResultSet(EntidadVO modelo,String parametros[])throws Exception{


        String consulta = modelo.getValorAtributo(ReporteModeloVO.html) +" ";
        
        
        if (parametros != null){
            for (int i=0;i<parametros.length;i++){
                Pattern p = Pattern.compile("OP\\{"+i+".*"+tagparam.replaceAll("#",i+"")+".*"+i+"\\}", Pattern.CASE_INSENSITIVE);
                //Pattern p = Pattern.compile("OP\\{.*\\}", Pattern.CASE_INSENSITIVE);


                Matcher m = p.matcher(consulta);

                if (parametros[i].isEmpty()){
                    consulta = m.replaceAll("");

                }else{                
                    if (m.find()){
                        String completo = m.group(); // viene con el OP{.....}
                        completo = completo.substring(3+(i+"").length(), completo.length()-1-(i+"").length());
                        consulta = m.replaceAll(completo);
                    }

                }
            }

            for (int i=0;i<parametros.length;i++){

                String pp = parametros[i];
                if (parametros[i].contains("/") && pp.length()== "02/09/2015".length()){
                    pp = DatabaseConstant.dfDB.format(DatabaseConstant.dfUser.parse(pp));
                }


                consulta = consulta.replaceAll(tagparam.replaceAll("#",i+""), pp);
            }
        }
        EntidadVO entidad = new ReporteModeloVO();
        Connection connection = null;
        Statement est = null;
        
        Class.forName(entidad.getDriver()).newInstance();
        connection = DriverManager.getConnection(entidad.getURL(), entidad.getUsuario(), entidad.getPass());
        est = connection.createStatement();


        System.out.println(consulta);
        java.sql.ResultSet rs = est.executeQuery(consulta.replaceAll("\"", "'"));

        
        return rs;
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code."> 
    @Override 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        processRequest(request, response); 
    } 
 
    @Override 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        processRequest(request, response); 
    } 
 
    @Override 
    public String getServletInfo() { 
        return "Short description"; 
    }// </editor-fold> 
} 
