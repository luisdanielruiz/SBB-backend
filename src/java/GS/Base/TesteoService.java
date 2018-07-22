package GS.Base;

import AplicationConstant.DatabaseConstant;
import java.io.IOException; 
import java.io.PrintWriter; 
import javax.servlet.ServletException; 
import java.util.Date; 
import java.util.Vector; 
import Entidades.EntidadVO; 
import Entidades.Helpers.EntidadHLP; 
import Py.DB.VO.Helpers.PyEntidadHLP;
import Py.DB.VO.PyVO;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
 
@WebServlet(name = "TesteoService", urlPatterns = {"/TesteoService"}) 
public class TesteoService extends HttpServlet { 
    public static TesteoI str = new TesteoI();
 
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException { 
        response.setContentType("text/html;charset=UTF-8"); 
        PrintWriter out = response.getWriter(); 
        try { 
            if (UsuarioService.comprobarSession(request)){
               if (request.getParameter("accTesteo").equals("guardar")){ 
                   out.println(guardar(request)); 
               }if (request.getParameter("accTesteo").equals("eliminar")){ 
                   out.println(eliminar(request)); 
               }else if (request.getParameter("accTesteo").equals("getData")){ 
                   out.println(getData(request)); 
               }else if (request.getParameter("accTesteo").equals("getDataT")){ 
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
           //sqls.add(LogService.loguearSQL(new Date(), usuario.usuario, "Alta de Institucionales " ,request.getRemoteAddr()));
           if(request.getParameter("fromABMTesteo").equals("n")){ 
               sqls.add(PyEntidadHLP.SQLInsert(nuevoDB,str));
           }else{ 
               List<String> where = new ArrayList<String>();
               where.add(  nuevoDB.getWhereFieldValue(str, str.idTest, nuevoDB.getValue(str.idTest)));               
               sqls.add(PyEntidadHLP.sqlUpdate(nuevoDB,str,where));
               
           } 
 
            PyEntidadHLP.executeTransaction(sqls, "Error en alta de Institucionales", "Institucionales", "",DatabaseConstant.conexionDefault);
            return "OK#1231321";             
        }catch (Exception exe){ 
            exe.printStackTrace();
            return "ER"; 
        } 
 
    } 
 
    public static String eliminar(HttpServletRequest request){ 
        try{ 
        //if(!UsuarioService.comprobarSession(usuario))
        //      throw new Exception("Error de seguridad. Ingrese nuevamente");
            PyVO  nuevoDB = new PyVO(str.cantCols);
            nuevoDB.getDataFrom(str,request);

            List<String> where = new ArrayList<String>();
            where.add(  nuevoDB.getWhereFieldValue(str,str.idTest, nuevoDB.getValue(str.idTest)));              
            
            
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
        //if(!UsuarioService.comprobarSession(usuario))
        //      throw new Exception("Error de seguridad. Ingrese nuevamente");
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
        //if(!UsuarioService.comprobarSession(usuario))
        //      throw new Exception("Error de seguridad. Ingrese nuevamente");
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
            exe.printStackTrace();
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
