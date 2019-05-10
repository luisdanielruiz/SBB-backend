/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import AplicationConstant.DatabaseConstant;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import GS.Base.*;
import Py.DB.VO.Helpers.PyEntidadHLP;
import Py.DB.VO.PyVO;
import java.util.List;
import java.util.ArrayList;

@WebServlet(name = "SBBLoginManual", urlPatterns = {"/SBBLoginManual"})
public class SBBLoginManual extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            UserAppI user = new UserAppI();
            PyVO eUser = new PyVO(user.cantCols);
            List<String> where = new ArrayList<String>();

            where.add(eUser.getWhereFieldValue(user, user.email, email));
            where.add(eUser.getWhereFieldValue(user, user.password, password));

            List<PyVO> ents = PyEntidadHLP.getList(eUser, user, where, DatabaseConstant.conexionDefault);

            if ((email == null || email.isEmpty())) {
                out.print("{ \"status\" : \"error\",\"result\": \"Mail Vacio o Null\"}");
            } else if (password == null || password.isEmpty()) {
                out.print("{ \"status\" : \"error\",\"result\": \"Password Vacia o Null\"}");
            } else if (ents.isEmpty()) {
                out.print("{ \"status\" : \"error\",\"result\": \"No hay valores\"}");
            } else {
                out.print("{ \"status\" : \"ok\",\"result\":" + ents.get(0).toJSON(user) + "}");
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
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
