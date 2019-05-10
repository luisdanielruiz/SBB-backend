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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel
 */
@WebServlet(name = "SBBReadDataStatistics", urlPatterns = {"/SBBReadDataStatistics"})
public class SBBReadDataStatistics extends HttpServlet {

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
            String idUser = request.getParameter("idUser");

            StatisticsI stadistics = new StatisticsI();
            PyVO eStatistics = new PyVO(stadistics.cantCols);
            List<String> where = new ArrayList<String>();

            where.add(eStatistics.getWhereFieldValue(stadistics, stadistics.user, idUser));

            List<PyVO> ents = PyEntidadHLP.getList(eStatistics, stadistics, where, DatabaseConstant.conexionDefault);

            if ((idUser == null || idUser.isEmpty())) {
                out.print("{ \"status\" : \"error\",\"result\": \"Id Vacio o Null\"}");
            } else if (ents.isEmpty()) {
                out.print("{ \"status\" : \"error\",\"result\": \"No hay valores\"}");
            } else {
                String retorna = "[";

                for (PyVO ent : ents) {

                    retorna += ent.toJSON(stadistics) + ",";
                }
                if (retorna.endsWith(",")) {
                    retorna = retorna.substring(0, retorna.length() - 1);
                }
                retorna += "]";

                out.print("{ \"status\" : \"ok\",\"result\": " + retorna + "}");

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
