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
    
   private int userID;
   private String username;
   private String password;
   private String email;
   
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static User find(String email,String password) throws ServletException{
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM userDetails WHERE (email =?)");
            ps.setString(1, email);
            ResultSet result = ps.executeQuery();
            User user = null;
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
