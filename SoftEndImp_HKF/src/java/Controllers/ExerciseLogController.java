/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author xmw13bzu
 */
@WebServlet(name = "ExerciseLogController", urlPatterns = {"/ExerciseLogController"})
public class ExerciseLogController extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        
        if (session == null) 
            throw new ServletException("Attempting to access exercise logs page while no session is active (no user logged in)");
        
        Member member = (Member) session.getAttribute("member"); //member member member member
        
        ExerciseLogger exLog = new ExerciseLogger();
        exLog = ExerciseLogger.find(member.getUserID());
        session.setAttribute("exerciseLog", exLog);
        
        String specificDateString = null;
        
        try {  //incase parameter doesnt exist
            specificDateString = request.getParameter("specificDate");
        } catch (Exception ex) {
            specificDateString = null;
        }
        
        //by default todays date
        HKFDate specificDate = specificDateString == null ? new HKFDate() : new HKFDate(specificDateString);
        HKFDate specificDate2 = specificDateString == null ? new HKFDate() : new HKFDate(specificDateString);
        specificDate2.setHours(0);
        specificDate2.setMinutes(0);
        
        ArrayList<ExerciseProgress> exercises = exLog.findProgressesBetweenDates(specificDate, specificDate2);
        session.setAttribute("specificExercises", exercises);
        
        request.getRequestDispatcher("exerciseProgress.jsp").forward(request, response);
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
