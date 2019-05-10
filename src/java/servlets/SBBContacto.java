/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import AplicationConstant.DatabaseConstant;
import GS.Base.MensajesI;
import Py.DB.VO.Helpers.PyEntidadHLP;
import Py.DB.VO.PyVO;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
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
@WebServlet(name = "SBBContacto", urlPatterns = {"/SBBContacto"})
public class SBBContacto extends HttpServlet {

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
            MensajesI mensajes = new MensajesI();
            PyVO str = new PyVO(mensajes.cantCols);

            String idUser = request.getParameter("idUser");
            String mail = request.getParameter("mail");
            String asunto = request.getParameter("asunto");
            String mensaje = request.getParameter("mensaje");

            List<String> where = new ArrayList<String>();
            str.getDataFrom(mensajes, request);
            List<PyVO> ents = PyEntidadHLP.getList(str, mensajes, where, DatabaseConstant.conexionDefault);

            out.print("{ \"status\" : \"ok\",\"result\":" + str.toJSON(mensajes) + "}");
            PyEntidadHLP.doInsert(str, mensajes, DatabaseConstant.conexionDefault);

            Email from = new Email("info@touchandshopapp.com");
            String subject = asunto;
            Email to = new Email("info@touchandshopapp.com");
            Content content = new Content("text/html", mensaje);
            com.sendgrid.Mail email = new com.sendgrid.Mail(from, subject, to, content);

            SendGrid sg = new SendGrid("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");
            Request req = new Request();
            req.method = (Method.POST);
            req.endpoint = ("mail/send");
            req.body = (email.build());
            Response respuesta = sg.api(req);

            String retorna = "OK" + respuesta.statusCode;
        } catch (Exception ex) {
            Logger.getLogger(SBBContacto.class.getName()).log(Level.SEVERE, null, ex);
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
