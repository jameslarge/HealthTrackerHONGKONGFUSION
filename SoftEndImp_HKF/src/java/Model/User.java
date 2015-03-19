/**
 * This Class will Model the Users information
 */
package Model;

import Controllers.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;

/**
 * 
 * @author xfu13dcu
 */
public class User {
    
   private int userID; //Users ID Number
   private String username; //User Username
   private String password; //User Password 
   private String email; //User Email
   
   public User(String username, String password, String email){
       this.userID = -1; 
       this.username = username;
       this.password = password;
       this.email = email;
   }
   
   public User(int userID, String username, String password, String email){
       this.userID = userID;
       this.username = username;
       this.password = password;
       this.email = email;
   }
    //Getter Methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
    //End of Getter Methods
    
    //Setter Methods
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //End of Setter Methods
    
    /**
     * Method to find User using email address
     * @param email Email Address of User
     * @param password Password of User
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static User find(String email,String password) throws ServletException{
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM userDetails WHERE (email =?)");
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();//Run statement
            User user = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                user = new User(result.getInt("id"), 
                        result.getString("username"), 
                        result.getString("password"), 
                        result.getString("email"));
            }
            return user;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for user while logging in ", ex);
        }
    }
    
    /**
     * Enter for the details for this user in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO userDetails (email, password, username) VALUES(?, ?, ?)");
            ps.setString(1, this.email);
            ps.setString(2, this.password);
            ps.setString(3, this.username);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Persist Problem: registering user details ", e);
        }
    }
   
}
