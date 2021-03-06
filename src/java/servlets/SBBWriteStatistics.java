/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import AplicationConstant.DatabaseConstant;
import GS.Base.StatisticsI;
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
@WebServlet(name = "SBBWriteStatistics", urlPatterns = {"/SBBWriteStatistics"})
public class SBBWriteStatistics extends HttpServlet {

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

            StatisticsI statistic = new StatisticsI();
            PyVO str = new PyVO(statistic.cantCols);

            /*parametros para insertar en stadisticas*/
            String user = request.getParameter("user");
            String challengue = request.getParameter("challengue");
            String tipoChallengue = request.getParameter("tipoChallengue");
            String time = request.getParameter("time");
            String hits = request.getParameter("hits");

            List<String> where = new ArrayList<String>();
            str.getDataFrom(statistic, request);
            where.add(str.getWhereFieldValue(statistic, statistic.user, user));
            where.add(str.getWhereFieldValue(statistic, statistic.challengue, challengue));
            where.add(str.getWhereFieldValue(statistic, statistic.tipoChallengue, tipoChallengue));
            where.add(str.getWhereFieldValue(statistic, statistic.time, time));
            where.add(str.getWhereFieldValue(statistic, statistic.hits, hits));

            List<PyVO> ents = PyEntidadHLP.getList(str, statistic, where, DatabaseConstant.conexionDefault);

            if (ents.isEmpty()) {

                out.print("{ \"status\" : \"ok\",\"result\":" + str.toJSON(statistic) + "}");
                PyEntidadHLP.doInsert(str, statistic, DatabaseConstant.conexionDefault);

            } else {
                out.print("error");
            }

        } catch (Exception ex) {
            Logger.getLogger(SBBWriteStatistics.class.getName()).log(Level.SEVERE, null, ex);
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
