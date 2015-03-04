package Controllers;

import Model.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A controller for creating a new user and adding them to the database.
 * @author Mercury Aimnh
 */
@WebServlet(name = "NewUserController", urlPatterns = {"/NewUserController"})
public class NewUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Get the details the user has supplied
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        //Confirm the user's email and password are correct
        if(!email.equals(request.getParameter("emailRe"))||!password.equals(request.getParameter("passwordRe"))){
            request.setAttribute("errorMessage", "Details do not match");
            request.getRequestDispatcher("createUser.jsp").forward(request, response);
        }
        
        //Confirm the user's email and password are valid
//        if(!User.isValidEmail(email)||password.length() < 6){
//            if (!User.isValidEmail(email)) {
//                request.setAttribute("errorMessage", "Invalid email address");
//                request.getRequestDispatcher("createUser.jsp").forward(request, response);
//            }
//            else {
//                request.setAttribute("errorMessage", "Passwords must be atleast 6 characters long");
//                request.getRequestDispatcher("createUser.jsp").forward(request, response);
//            }
//        }
        
        //Create a user bean, and add their details to the database.
        User newUser = new User(username, password, email);
        newUser.persist();
        
        //Log the user in
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("LoginController").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
