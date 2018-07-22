/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import AplicationConstant.DatabaseConstant;
import GS.Base.UserAppI;
import Py.DB.VO.Helpers.PyEntidadHLP;
import Py.DB.VO.PyVO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "SBBRegistroManual", urlPatterns = {"/SBBRegistroManual"})
public class SBBRegistroManual extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     UserAppI strRegister = new UserAppI();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        
        
       try {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String password = request.getParameter("password");
                
                PyVO eRegister = new PyVO(strRegister.cantCols);

                List<String> where = new ArrayList<String>();
                if ((password == null || password.isEmpty()) 
                        || (email.isEmpty() || email == null)
                        || (name == null || name.isEmpty())
                        || (surname == null || surname.isEmpty())                      
                        || (phone == null || phone.isEmpty())) {
                    out.print("{status\": \"error\", \"errorCode\": \"000004\", \"stacktrace\": \"error in one or more values required in the register\"}");
                } else {
                    where.add(eRegister.getWhereFieldValue(strRegister, strRegister.email, email));
                    List<PyVO> ents = PyEntidadHLP.getList(eRegister, strRegister, where, DatabaseConstant.conexionDefault);

                    if (ents.isEmpty()) {
                        eRegister.getDataFrom(strRegister, request);
                         
                        String idUser = DatabaseConstant.rnd.nextInt(99999) + "";

                        where.clear();
                        where.add(eRegister.getWhereFieldValue(strRegister, strRegister.idUser, idUser));

                        ents = PyEntidadHLP.getList(eRegister, strRegister, where, DatabaseConstant.conexionDefault);

                        while (ents.size() > 0) {
                            idUser = DatabaseConstant.rnd.nextInt(99999) + "";

                            where.clear();
                            where.add(eRegister.getWhereFieldValue(strRegister, strRegister.idUser, idUser));

                            ents = PyEntidadHLP.getList(eRegister, strRegister, where, DatabaseConstant.conexionDefault);

                        }

                        eRegister.setValue(strRegister.idUser, idUser);
                        PyEntidadHLP.doInsert(eRegister, strRegister, DatabaseConstant.conexionDefault);
                        out.print("{\"status\": \"ok\", \"result\":" + eRegister.toJSON(strRegister) + "}");
                        
                    } else {

                        out.print("{\"status\": \"error\", \"errorCode\": \"000003\", \"stacktrace\" : \"this email adresss are already in use \"}");
                    }
                }

                //out.print("register page"); 
        }catch(Exception e){
            e.printStackTrace();
            out.print("{\"status\": \"error\", \"errorCode\": \"000003\", \"stacktrace\" : \""+e.getMessage()+" \"}");
        }
        finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}