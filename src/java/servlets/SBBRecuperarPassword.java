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
@WebServlet(name = "SBBRecuperarPassword", urlPatterns = {"/SBBRecuperarPassword"})
public class SBBRecuperarPassword extends HttpServlet {

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

            String mail = request.getParameter("mail");
            String asunto = "SBB: Recuperación de contraseña";

            if (mail == null || mail == "") {
                out.print("{ \"status\" : \"error\",\"result\": \"No se encuentra mail.\"}");
            } else {
                List<String> where = new ArrayList<String>();
                e.getDataFrom(usuario, request);
                where.add(e.getWhereFieldValue(usuario, usuario.email, mail));
                List<PyVO> ents = PyEntidadHLP.getList(e, usuario, where, DatabaseConstant.conexionDefault);

                if (ents.isEmpty()) {
                    out.print("{ \"status\" : \"error\",\"result\": \"No se encuentra mail.\"}");
                } else {
                    String html = "<html>"
                            + "<body>"
                            + "<img src=\"http://.net/logo.png\" style=\"widht:100px; height:100px; margin:10px auto;\">"
                            + "<p>Hola, tu contraseña es: " + ents.get(0).getValue(usuario.password)
                            + "</p>"
                            + "</br>"
                            + "<p>Saludos desde SBB.</p>"
                            + "</body>"
                            + "</html>";

                    Email from = new Email("registro@smartboxingbag.info");
                    String subject = asunto;
                    Email to = new Email(ents.get(0).getValue(usuario.email));
                    // Email to = new Email("medicinabiomolecular4@gmail.com");
                    Content content = new Content("text/html", html);

                    com.sendgrid.Mail email = new com.sendgrid.Mail(from, subject, to, content);

                    SendGrid sg = new SendGrid("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");
                    Request req = new Request();
                    req.method = (Method.POST);
                    req.endpoint = ("mail/send");
                    req.body = (email.build());
                    Response respuesta = sg.api(req);

                    String retorna = "OK" + respuesta.statusCode;
                    out.print("{ \"status\" : \"ok\",\"result\": \"Su contraseña ya se envió a su mail.\"}");
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(SBBRecuperarPassword.class.getName()).log(Level.SEVERE, null, ex);
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
