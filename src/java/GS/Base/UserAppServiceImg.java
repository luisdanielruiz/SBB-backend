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
  
@WebServlet(name = "UserAppServiceImg", urlPatterns = {"/UserAppServiceImg"})  
public class UserAppServiceImg extends HttpServlet {   
  
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
            PyVO p = new PyVO(UserAppService.str.cantCols); 
              
            List items = upload.parseRequest(request); 
            Iterator iterator = items.iterator(); 
             
            String fromABMUserApp =""; 
             
             
             
            int i=0; 
            String f = new Date().getTime()+""; 
            f = f.substring(5, f.length()); 
            while (iterator.hasNext()){ 
                 FileItem item = (FileItem) iterator.next(); 
                                   
                 if (item.isFormField()) { 
                    System.out.println(item.getFieldName()+ ": "+ item.getString()); 
                     
                    if (item.getFieldName().equals("fromABMUserApp")){ 
                       fromABMUserApp = item.getString(); 
                    }
                    else if (item.getFieldName().equals(UserAppService.str.idUser)){/****/ 
                        p.setDecodedValue(UserAppService.str.idUser ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.userName)){/****/ 
                        p.setDecodedValue(UserAppService.str.userName ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.password)){/****/ 
                        p.setDecodedValue(UserAppService.str.password ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.email)){/****/ 
                        p.setDecodedValue(UserAppService.str.email ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.name)){/****/ 
                        p.setDecodedValue(UserAppService.str.name ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.surname)){/****/ 
                        p.setDecodedValue(UserAppService.str.surname ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.country)){/****/ 
                        p.setDecodedValue(UserAppService.str.country ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.state)){/****/ 
                        p.setDecodedValue(UserAppService.str.state ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.city)){/****/ 
                        p.setDecodedValue(UserAppService.str.city ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.address)){/****/ 
                        p.setDecodedValue(UserAppService.str.address ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.postalCode)){/****/ 
                        p.setDecodedValue(UserAppService.str.postalCode ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.idFacebook)){/****/ 
                        p.setDecodedValue(UserAppService.str.idFacebook ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.idGoogle)){/****/ 
                        p.setDecodedValue(UserAppService.str.idGoogle ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.profilePicture)){/****/ 
                        p.setDecodedValue(UserAppService.str.profilePicture ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.phone_cod)){/****/ 
                        p.setDecodedValue(UserAppService.str.phone_cod ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.phone)){/****/ 
                        p.setDecodedValue(UserAppService.str.phone ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.status)){/****/ 
                        p.setDecodedValue(UserAppService.str.status ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.axisY)){/****/ 
                        p.setDecodedValue(UserAppService.str.axisY ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.axisX)){/****/ 
                        p.setDecodedValue(UserAppService.str.axisX ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.location)){/****/ 
                        p.setDecodedValue(UserAppService.str.location ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.mailConfirmado)){/****/ 
                        p.setDecodedValue(UserAppService.str.mailConfirmado ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.dni)){/****/ 
                        p.setDecodedValue(UserAppService.str.dni ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.cuil)){/****/ 
                        p.setDecodedValue(UserAppService.str.cuil ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.premium)){/****/ 
                        p.setDecodedValue(UserAppService.str.premium ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.fechaRegistro)){/****/ 
                        p.setDecodedValue(UserAppService.str.fechaRegistro ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.sesionHistory)){/****/ 
                        p.setDecodedValue(UserAppService.str.sesionHistory ,item.getString(), encode); 
                    } 
                    else if (item.getFieldName().equals(UserAppService.str.statistics)){/****/ 
                        p.setDecodedValue(UserAppService.str.statistics ,item.getString(), encode); 
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
            
           if(fromABMUserApp.equals("n")){  
               sqls.add(PyEntidadHLP.SQLInsert(p,UserAppService.str)); 
           }else{  
               List<String> where = new ArrayList<String>(); 
               where.add(  p.getWhereFieldValue(UserAppService.str, UserAppService.str.idUser, p.getValue(UserAppService.str.idUser)));                
               sqls.add(PyEntidadHLP.sqlUpdate(p,UserAppService.str,where)); 
                
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
