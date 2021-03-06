package GS.Base; 
 
import AplicationConstant.DatabaseConstant; 
import Atributo.AtributoDB; 
import java.io.IOException;  
import java.io.PrintWriter;  
import javax.servlet.ServletException;  
import java.util.Date;  
 
import Py.DB.VO.Helpers.PyEntidadHLP; 
import Py.DB.VO.PyVO; 
import java.awt.Graphics2D; 
import java.awt.image.BufferedImage; 
import java.io.ByteArrayInputStream; 
import java.io.File; 
import java.io.FileOutputStream; 
import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Iterator; 
import java.util.List; 
import javax.imageio.ImageIO; 
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import org.apache.commons.io.IOUtils;  
import org.apache.tomcat.util.http.fileupload.FileItem; 
import org.apache.tomcat.util.http.fileupload.FileItemFactory; 
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory; 
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload; 
  
@WebServlet(name = "UsuarioServiceImg", urlPatterns = {"/UsuarioServiceImg"})  
public class UsuarioServiceImg extends HttpServlet {   
  
    public static String encode= "UTF-8"; 
      
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
        response.setContentType("text/html;charset=UTF-8");  
        PrintWriter out = response.getWriter();  
        try {  
            boolean isMultipart = ServletFileUpload.isMultipartContent(request); 
            if (UsuarioService.comprobarSession(request)){ 
                if (isMultipart){ 
                    out.println(guardar(request, response)); 
                } 
                
            }else{ 
                   response.setContentType("text/html;charset=UTF-8"); 
                   out = response.getWriter();  
                   out.println("Login.jsp"); 
            }  
  
  
        } finally {              
            out.close();  
        }  
    }  
  
    public static String guardar(HttpServletRequest request,HttpServletResponse response){ 
          
         
         FileItemFactory factory = new DiskFileItemFactory(); 
         ServletFileUpload upload = new ServletFileUpload(factory); 
          
         String retorna =""; 
   
         try { 
            PyVO p = new PyVO(UsuarioService.str.cantCols); 
              
            List items = upload.parseRequest(request); 
            Iterator iterator = items.iterator(); 
             
            String fromABMUsuario =""; 
             
             
             
            int i=0; 
            String f = new Date().getTime()+""; 
            f = f.substring(5, f.length()); 
            while (iterator.hasNext()){ 
                 FileItem item = (FileItem) iterator.next(); 
                                   
                 if (item.isFormField()) { 
                    System.out.println(item.getFieldName()+ ": "+ item.getString()); 
                     
                    if (item.getFieldName().equals("fromABMUsuario")){ 
                       fromABMUsuario = item.getString(); 
                    }
                    else if (item.getFieldName().equals(UsuarioService.str.user)){/****/ 
                        p.setDecodedValue(UsuarioService.str.user ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UsuarioService.str.password)){/****/ 
                        p.setDecodedValue(UsuarioService.str.password ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UsuarioService.str.mail)){/****/ 
                        p.setDecodedValue(UsuarioService.str.mail ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UsuarioService.str.idPerfil)){/****/ 
                        p.setDecodedValue(UsuarioService.str.idPerfil ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UsuarioService.str.estado)){/****/ 
                        p.setDecodedValue(UsuarioService.str.estado ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UsuarioService.str.recibeMail)){/****/ 
                        p.setDecodedValue(UsuarioService.str.recibeMail ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UsuarioService.str.codigoTablet)){/****/ 
                        p.setDecodedValue(UsuarioService.str.codigoTablet ,item.getString(), encode); 
                    } 
                     
                             
                     
                 }else{ 
                     i++; 
                    /* 
                    ByteArrayInputStream bis = new ByteArrayInputStream(item.get()); 
                     
                    String filename=f+"-"+i+"."+item.getName().split("\.")[item.getName().split("\.").length-1].toLowerCase(); 
                     
                     
                    if (item.getFieldName().equals(MovimientosVO.Archivos)){ 
                       imagenesArchi=filename; 
                    } 
                    IOUtils.copy(bis, new FileOutputStream(AplicationConstant.DatabaseConstant.urlImagenes+filename)); 
                      
                    */ 
                     
                 } 
            } 
              
            //p.setValorAtributoSinApostrofeYBLANKS(ArticulosVO.Archivos, imagenesArchi); 
             
             
            
 
           List<String> sqls = new ArrayList<String>(); 
            
           if(fromABMUsuario.equals("n")){  
               sqls.add(PyEntidadHLP.SQLInsert(p,UsuarioService.str)); 
           }else{  
               List<String> where = new ArrayList<String>(); 
               where.add(  p.getWhereFieldValue(UsuarioService.str, UsuarioService.str.idPerfil, p.getValue(UsuarioService.str.idPerfil)));                
               sqls.add(PyEntidadHLP.sqlUpdate(p,UsuarioService.str,where)); 
                
           }  
  
            PyEntidadHLP.executeTransaction(sqls, "Error en alta de ...", "Institucionales", "",DatabaseConstant.conexionDefault); 
            return "OK#1231321";              
        }catch (Exception exe){  
            return "ER"+exe.getLocalizedMessage();  
        }  
  
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
