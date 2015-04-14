/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controllers;

import Model.*;
import Model.PhysicalHealth.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
@WebServlet(name = "LogMealController", urlPatterns = {"/LogMealController"})
public class LogMealController extends HttpServlet {

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
        
        Member member = (Member) session.getAttribute("member"); //member member member member
        
        if (session == null) 
            throw new ServletException("Attempting to log a mealProgress while no session is active (no user logged in)");
        
        MealLogger mealLog = (MealLogger) session.getAttribute("mealLog");
        
        
        //should be  YYYY-MM-DD, i.e is shit and doesnt enforce any formatting 
        //on user in input type=date, deal with it later
        String sdate = request.getParameter("date");
        String [] parts = sdate.split("-");
        
//        Calendar cal = Calendar.getInstance();
//        cal.set(Integer.parseInt(parts[0]), 
//                Integer.parseInt(parts[1]), 
//                Integer.parseInt(parts[2]));
        
        //depricated but meh
        Date date = new Date(Integer.parseInt(parts[0]) - 1900,  //because reasons
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2]));
        
        
        String mtype = request.getParameter("mealType");
  
        MealProgress.MealType mealType = MealProgress.MealType.getValue(mtype);
        int mealID = Integer.parseInt(request.getParameter("meal"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        
        
        MealProgress mp = new MealProgress(member.getUserID(), mealID, mealType, date, amount);
        mp.persist();
        
        
        //reload weight log page, refresh data essentially
        request.getRequestDispatcher("DietLogController").forward(request, response);
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
