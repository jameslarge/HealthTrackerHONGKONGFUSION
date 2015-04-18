/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Goals.*;
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
@WebServlet(name = "GoalsController", urlPatterns = {"/GoalsController"})
public class GoalsController extends HttpServlet {

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
            throw new ServletException("Attempting to access goals page while no session is active (no user logged in)");
        
        Member member = (Member) session.getAttribute("member");
        int memberID = member.getUserID(); 
        
        GoalLogger goalLog = GoalLogger.find(member.getUserID());
        session.setAttribute("goalLog", goalLog);
        
        ArrayList<Goal> finishedGoals = goalLog.findFinishedGoals();
        ArrayList<Goal> dueGoals = goalLog.findDueGoals();
        ArrayList<Goal> upcomingGoals = goalLog.findUpcomingGoals();
            
        ArrayList<Integer> finishedGoalsProgress = GoalLogger.checkProgress(finishedGoals, memberID);
        ArrayList<Integer> dueGoalsProgress = GoalLogger.checkProgress(dueGoals, memberID);
        ArrayList<Integer> upcomingGoalsProgress = GoalLogger.checkProgress(upcomingGoals, memberID);
        
        session.setAttribute("finishedGoals", finishedGoals);
        session.setAttribute("finishedGoalsProgress", finishedGoalsProgress);
        session.setAttribute("dueGoals", dueGoals);
        session.setAttribute("dueGoalsProgress", dueGoalsProgress);
        session.setAttribute("upcomingGoals", upcomingGoals);
        session.setAttribute("upcomingGoalsProgress", upcomingGoalsProgress);
        
        request.getRequestDispatcher("goalProgress.jsp").forward(request, response);
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
