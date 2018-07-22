package GS.Base;

import java.io.IOException; 
import GS.Base.UsuarioService; 
import java.io.PrintWriter; 
import javax.servlet.ServletException; 
import java.util.Date; 
import java.util.Vector; 
import Py.DB.VO.Helpers.PyEntidadHLP; 
import Py.DB.VO.PyVO; 
import java.util.ArrayList; 
import java.util.List; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import AplicationConstant.DatabaseConstant; 
 
@WebServlet(name = "CategoriesService", urlPatterns = {"/CategoriesService"}) 
public class CategoriesService extends HttpServlet { 
 
    public static CategoriesI str = new CategoriesI();
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        response.setContentType("text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter(); 
        try { 
            if (UsuarioService.comprobarSession(request)){
               if (request.getParameter("accCategories").equals("guardar")){ 
                   out.println(guardar(request)); 
               }if (request.getParameter("accCategories").equals("eliminar")){ 
                   out.println(eliminar(request)); 
               }else if (request.getParameter("accCategories").equals("getData")){ 
                   out.println(getData(request)); 
               }else if (request.getParameter("accCategories").equals("getDataT")){ 
                   out.println(getDataTelerik(request)); 
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
 
    public static String guardar(HttpServletRequest request){ 
        try{ 
           //if(!UsuarioService.comprobarSession(usuario))
           // throw new Exception("Error de seguridad. Ingrese nuevamente");
           PyVO  nuevoDB = new PyVO(str.cantCols);
           nuevoDB.getDataFrom(str, request);


           Vector<String> sqls = new Vector<String>();
           //sqls.add(LogService.loguearSQL(new Date(), usuario.usuario, "Alta de Categories " ,request.getRemoteAddr()));
           if(request.getParameter("fromABMCategories").equals("n")){ 
               sqls.add(PyEntidadHLP.SQLInsert(nuevoDB,str));
           }else{ 
               List<String> where = new ArrayList<String>();
               where.add(  nuevoDB.getWhereFieldValue(str, str.id, nuevoDB.getValue(str.id)));  
               sqls.add(PyEntidadHLP.sqlUpdate(nuevoDB,str,where));
           } 
 
            PyEntidadHLP.executeTransaction(sqls, "Error en alta de Categories", "Categories", "",DatabaseConstant.conexionDefault);
            return "OK#1231321";             
        }catch (Exception exe){ 
            return "ER"; 
        } 
 
    } 
 
    public static String eliminar(HttpServletRequest request){ 
        try{ 
            PyVO  nuevoDB = new PyVO(str.cantCols); 
            nuevoDB.getDataFrom(str,request); 
 
            List<String> where = new ArrayList<String>(); 
            where.add(  nuevoDB.getWhereFieldValue(str,str.id, nuevoDB.getValue(str.id)));               
 
 
            Vector<String> sqls = new Vector<String>(); 
            sqls.add(PyEntidadHLP.SQLDeleteWhere(nuevoDB,str,where)); 
 
            PyEntidadHLP.executeTransaction(sqls, "Error en alta de Institucionales", "Institucionales", "",DatabaseConstant.conexionDefault); 
 
 
            return "OK#1231321";             
        }catch (Exception exe){ 
            return "ER"+exe.toString(); 
        } 
 
    } 
 
    public static String getData(HttpServletRequest request){ 
        try{ 
            PyVO  e = new PyVO(str.cantCols); 
            List<String> where = new ArrayList<String>(); 
            String retorna = "["; 
            List <PyVO> ents = PyEntidadHLP.getList(e,str,where, DatabaseConstant.conexionDefault);             
            for (PyVO ent:ents){ 
                retorna+= ent.toJSON(str)+","; 
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
    public static String getDataTelerik(HttpServletRequest request){ 
        try{ 
			PyVO  e = new PyVO(str.cantCols);             
            List<String> where = new ArrayList<String>(); 
            String retorna = "["; 
            List <PyVO> ents = PyEntidadHLP.getList(e,str,where, DatabaseConstant.conexionDefault);             
            for (PyVO ent:ents){ 
                retorna+= ent.toJSON(str)+","; 
            } 
            if (retorna.endsWith(",")){  
               retorna = retorna.substring(0,retorna.length()-1);  
            }  
            retorna+="]";  
            retorna = request.getParameter("$callback")+"("+ 
 
            "{  "+  
            "   \"d\":{  "+  
            "      \"results\":  "+  
                    retorna+         
            "        ,"+ 
            "      \"__count\":\""+ents.size()+"\""+ 
            "   }"+ 
            "} )";     
            return  retorna ;
        }catch (Exception exe){ 
            return "ER"; 
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
