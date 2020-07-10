package GS.Base;

import java.io.IOException; 
import java.io.PrintWriter; 
import javax.servlet.ServletException; 
import java.util.Date; 
import java.util.Vector; 
import Entidades.EntidadVO; 
import Entidades.Helpers.EntidadHLP; 

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession;

 
@WebServlet(name = "UsuarioService", urlPatterns = {"/UsuarioService"}) 
public class UsuarioService extends HttpServlet { 
    public static String loginErrorsRiesgo ="LOG_ERR";
    public static String loginErrorsNumero ="LOG_ERR_N";
    public static String loginRiesgo ="LOG_RIESGO";
    public static String loginNombreUsuario ="LOG_NU";
    public static String loginMenu ="LOG_MENU";
    public static String loginSoloLectura ="loginSoloLectura";
 
    public static UsuarioI str = new UsuarioI();
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        PrintWriter out =null;
        try { 
            
            if (request.getParameter("accUsuario").equals("login")){ 
                response.setContentType("text/html;charset=UTF-8"); 
                out = response.getWriter(); 
                out.println(UsuarioService.Login(request)); 
            }else{ 
                if (comprobarSession(request)){
                    response.setContentType("text/html;charset=UTF-8"); 
                    out = response.getWriter(); 
                    if (request.getParameter("accUsuario").equals("guardar")){ 
                        out.println(guardar(request)); 
                    }if (request.getParameter("accUsuario").equals("eliminar")){ 
                        out.println(eliminar(request)); 
                    }else if (request.getParameter("accUsuario").equals("getData")){ 
                        out.println(getData(request)); 
                    }else if (request.getParameter("accUsuario").equals("getDataT")){ 
                        out.println(getDataTelerik(request)); 
                    }
                }else{
                    response.setContentType("text/html;charset=UTF-8"); 
                    out = response.getWriter(); 
                    out.println("Login.jsp");
                }
            }
 
 
        }catch (Exception e){
            e.printStackTrace();
        } finally {        
            if (out!= null)
                out.close(); 
        } 
    } 
 
    public static String guardar(HttpServletRequest request){ 
        try{ 
           //if(!UsuarioService.comprobarSession(usuario))
           // throw new Exception("Error de seguridad. Ingrese nuevamente");
           EntidadVO  nuevoDB = new UsuarioVO();
           nuevoDB.getDataFrom(request);
           //nuevoDB.setValorAtributo(UsuarioVO.estado, (request.getParameter(UsuarioVO.estado) != null?"1":"0"));
           nuevoDB.setValorAtributo(UsuarioVO.estado, request.getParameter(UsuarioVO.estado));
           nuevoDB.setValorAtributo(UsuarioVO.recibeMail, "0");


           Vector<String> sqls = new Vector<String>();
           //sqls.add(LogService.loguearSQL(new Date(), usuario.usuario, "Alta de Usuario " ,request.getRemoteAddr()));
           if(request.getParameter("fromABMUsuario").equals("n")){ 
               sqls.add(EntidadHLP.SQLdoInsert(nuevoDB));
           }else{ 
               sqls.add(EntidadHLP.sqlUpdatefromPK(nuevoDB));
           } 
 
            EntidadHLP.ejecutarTransaccion(nuevoDB, sqls, "Error en alta de Usuario", "Usuario", "");
            return "OK#1231321";             
        }catch (Exception exe){ 
            return "ER"; 
        } 
 
    } 
 
    public static String eliminar(HttpServletRequest request){ 
        try{ 
        //if(!UsuarioService.comprobarSession(usuario))
        //      throw new Exception("Error de seguridad. Ingrese nuevamente");
            UsuarioVO editarDB = new UsuarioVO();
            editarDB.setValorPK(request.getParameter(UsuarioVO.usuario)+"");

        Vector<String> sqls = new Vector<String>();
        sqls.add(EntidadHLP.SQLdoDeleteByPK(editarDB));

        //sqls.add(LogService.loguearSQL(new Date(), usuario.usuario, "Modificacion de Usuario " ,request.getRemoteAddr()));
        EntidadHLP.ejecutarTransaccion(editarDB, sqls, "Error en modificacion de Usuario", "Usuario", "");
 
 
            return "OK#1231321";             
        }catch (Exception exe){ 
            return "ER"+exe.toString(); 
        } 
 
    } 
 
    public static String getData(HttpServletRequest request){ 
        try{ 
        //if(!UsuarioService.comprobarSession(usuario))
        //      throw new Exception("Error de seguridad. Ingrese nuevamente");
            EntidadVO e =  new UsuarioVO();
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
    
    
    public static String Login(HttpServletRequest request) throws Exception {
        String retorna = "";
        EntidadVO db = new UsuarioVO();
        db.setValorPK(request.getParameter(UsuarioVO.usuario));
        db = EntidadHLP.getByPK(db);
        if (db == null) {
        } else {
            HttpSession mySession = request.getSession();
            
            if (db.getValorAtributo(UsuarioVO.Password).equals(request.getParameter(UsuarioVO.Password))) {
                if (db.getValorAtributo(UsuarioVO.estado).equals("1")) {
                    mySession.removeAttribute(loginErrorsRiesgo);
                    mySession.removeAttribute(loginErrorsNumero);
                    /*
                    EntidadVO perfil =  new PerfilVO();
                    perfil.setValorPK(db.getValorAtributo(UsuarioVO.idPerfil));
                    perfil = EntidadHLP.getByPK(perfil);
                    String JSperfil ="";
                    if (perfil !=null){
                        JSperfil= "{"+
                            perfil.toJSONsinCierre()+", "+
                            "listaPermisos: "+PerfilService.getPermisosPerfil(perfil.getPK().getValor())
                            +"}";
                    }
                    
                    String JSUser = "{"+
                        db.toJSONsinCierre()+", "+
                        "perfil: "+JSperfil
                        +"}";

                    mySession.setAttribute(loginRiesgo, JSUser);
                    mySession.setAttribute(loginNombreUsuario, db.getPK().getValor());*/

                    retorna = "1OK";
                    
                    String menuUser= getMenuUser(db);
                    mySession.setAttribute(loginMenu, menuUser);
                    mySession.setAttribute(loginRiesgo, db.getPK().getValor());
                    mySession.setAttribute(loginSoloLectura, db.getValorAtributo(UsuarioVO.idPerfil));
                    
                    //LogService.loguear(new Date(), r.usuario, "Se ha logueado el usuario: " + r.usuario,Funcctions.getIP());
                } else {
                    retorna = "-2ER";

                }
            } else {
                int errores = 0;
                if (mySession.getAttribute(loginErrorsRiesgo) != null && mySession.getAttribute(loginErrorsRiesgo).equals(request.getParameter(UsuarioVO.usuario))) {
                    errores = Integer.parseInt(mySession.getAttribute(loginErrorsNumero).toString());
                }
                errores++;
                mySession.setAttribute(loginErrorsRiesgo, db.getValorAtributo(UsuarioVO.usuario));
                mySession.setAttribute(loginErrorsNumero, errores + "");

                if (errores >= 3) {
                    UsuarioVO err = new UsuarioVO();
                    err.setValorPK(request.getParameter(UsuarioVO.usuario));
                    err.setValorAtributo(err.estado, "0");
                    EntidadHLP.doUpdatefromPK(err);
                    
                    retorna = "-2ER";
                }

                
                retorna = "-1ER";
            }
        }


        return retorna;
    }

    public static boolean comprobarSession(HttpServletRequest request){
        return request.getSession().getAttribute(loginRiesgo)!=null ;
    }
    
    public static boolean comprobarSession(HttpServletRequest request, String us, String pass){
        return comprobarSession(request.getSession(), us, pass);
    }
    
    public static void logout (HttpServletRequest request) {
        HttpSession mySession = request.getSession();
        mySession.invalidate(); 
    }

    public static boolean comprobarSession(HttpSession mySession, String us, String pass){
        Gson js = new Gson();
        JsonParser parser = new JsonParser();
        JsonObject obj = (JsonObject)parser.parse(mySession.getAttribute(loginRiesgo).toString());
           
                
        return mySession.getAttribute(loginRiesgo)!=null &&
               (obj.get(UsuarioVO.usuario).getAsString()).equals(us)&&
               (obj.get(UsuarioVO.Password).getAsString()).equals(pass);
    }
 
    
    public static String getMenuUser(EntidadVO usuario){
        String retorna ="";
        try{
            
            
            
            
        
        
        }catch (Exception exe){
            exe.printStackTrace();
        }
        return retorna;
    }
    
    public static String getDataTelerik(HttpServletRequest request){ 
        try{ 
        //if(!UsuarioService.comprobarSession(usuario))
        //      throw new Exception("Error de seguridad. Ingrese nuevamente");
            EntidadVO e =  new UsuarioVO();
            String retorna = "[";
            Vector <EntidadVO> ents = EntidadHLP.getByAtribute(e);
            for (int i=0; i<ents.size(); i++){
                retorna+= ents.get(i).toJSON()+",";
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
            "} )";                    return  retorna ;
        }catch (Exception exe){ 
            return "ER"; 
        } 
 
    } 
    
    /*public static boolean ifUserPermiso(Vector<EntidadVO> etts, String permiso){
        boolean retorna = false;
        try{
            for (int i=0; i<etts.size();i++){
                if (etts.get(i).getValorAtributo(PermisosPorPerfilVO.idPermiso).equals(permiso)){
                    retorna = true;
                    break;
                }
            }
            
        }catch (Exception exe){
            exe.printStackTrace();
        }        
        
        /////////SACAR ESTO//////////////
        //retorna = true;
        ///////////////////////
        return retorna;
    }
    
    public static boolean tienePermisoUsuarioLogueado(HttpSession session,int permiso){
        boolean retorna = false;
        try{
            EntidadVO e = new UsuarioVO();
            e.setValorPK(session.getAttribute(loginNombreUsuario).toString());
            e = EntidadHLP.getByPK(e);
            EntidadVO p = new PermisosPorPerfilVO();
            p.setValorAtributo(PermisosPorPerfilVO.idPerfil, e.getValorAtributo(UsuarioVO.idPerfil));
            p.setValorAtributo(PermisosPorPerfilVO.idPermiso, permiso+"");
            
            retorna = EntidadHLP.getByAtribute(p).size()>0;
            
            
        }catch(Exception exe){}
        
        
        return retorna;
    }*/
 
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
