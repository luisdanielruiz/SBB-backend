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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author usuario
 */
@WebServlet(name = "SBBMyAccount", urlPatterns = {"/SBBMyAccount"})
public class SBBMyAccount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            UserAppI usuario = new UserAppI();
            PyVO e = new PyVO(usuario.cantCols);

            String idUser = request.getParameter("idUser");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String userName = request.getParameter("userName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String dni = request.getParameter("dni");
            String city = request.getParameter("city");
            String country = request.getParameter("country");
            String newPassword1 = request.getParameter("newPassword1");
            String newPassword2 = request.getParameter("newPassword2");
            String password = request.getParameter("password");

            List<String> where = new ArrayList<String>();

            e.getDataFrom(usuario, request);

            where.add(e.getWhereFieldValue(usuario, usuario.idUser, idUser));
            List<PyVO> ents = PyEntidadHLP.getList(e, usuario, where, DatabaseConstant.conexionDefault);

            if (ents.isEmpty()) {

                out.print("No existe el ususario que desea editar");

            } else if ((email == null || email.isEmpty())
                    || (name == null || name.isEmpty())
                    || (surname == null || surname.isEmpty())
                    || (userName == null || userName.isEmpty())
                    || (city == null || city.isEmpty())
                    || (dni == null || dni.isEmpty())
                    || (address == null || address.isEmpty())
                    || (country == null || country.isEmpty())
                    || (phone == null || phone.isEmpty())) {

                out.print("{status\": \"error\", \"errorCode\": \"000004\", \"stacktrace\": \"error in one or more values required in the register}");

            } else if (!((newPassword1 == null || newPassword1.isEmpty())
                    || (newPassword2 == null || newPassword2.isEmpty())
                    || (password == null || password.isEmpty()))) {

                if ((newPassword1.equals(newPassword2)) && (ents.get(0).getValue(usuario.password).equals(password))) {

                    e.setValue(usuario.password, newPassword2);
                    PyEntidadHLP.doUpdate(e, usuario, where, DatabaseConstant.conexionDefault);
                    out.print("{ \"status\" : \"ok\",\"result\":" + e.toJSON(usuario) + "}");
                } else {

                    out.print("{ \"status\" : \"error\",\"result\":cambio de contraseña fallido, reingrese los datos}");
                }
            } else {

                e.setValue(usuario.password, ents.get(0).getValue(usuario.password));
                out.print("{ \"status\" : \"ok\",\"result\":" + e.toJSON(usuario) + "}");
                PyEntidadHLP.doUpdate(e, usuario, where, DatabaseConstant.conexionDefault);
            }
        } catch (Exception ex) {
            Logger.getLogger(SBBMyAccount.class.getName()).log(Level.SEVERE, null, ex);
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
