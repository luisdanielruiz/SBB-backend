/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import AplicationConstant.DatabaseConstant;
import GS.Base.ChallengueI;
import static GS.Base.TipoChallengueService.str;
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
 * @author daniel
 */
@WebServlet(name = "SBBListarChallenges", urlPatterns = {"/SBBListarChallenges"})
public class SBBListarChallenges extends HttpServlet {

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

            /* TODO output your page here. You may use following sample code. */
            PyVO e = new PyVO(str.cantCols);
            List<String> where = new ArrayList<String>();
            ChallengueI challenges = new ChallengueI();

            //ahora el listado de rubros
            String retornaRubro = "[";
            List<PyVO> ents2 = PyEntidadHLP.getList(e, challenges, where, DatabaseConstant.conexionDefault);
            for (PyVO ent : ents2) {
                retornaRubro += ent.toJSON(challenges) + ",";
            }
            if (retornaRubro.endsWith(",")) {
                retornaRubro = retornaRubro.substring(0, retornaRubro.length() - 1);
            }
            retornaRubro += "]";

            out.print("{ \"status\" : \"ok\",\"result\": " + retornaRubro + "}");

        } catch (Exception ex) {
            Logger.getLogger(SBBListarChallenges.class.getName()).log(Level.SEVERE, null, ex);
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
