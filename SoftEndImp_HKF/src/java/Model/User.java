/**
 * This Class will Model the Users information
 */
package Model;

import Controllers.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;

/**
 * 
 * @author xfu13dcu
 */
public class User {
    
   private String username;
   private String password;
   private String email;
   
   public User(String username, String password, String email){
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
    
    public static User find(String username,String password){
        //To Be Added
        
        return null;
    }
    
    /**
     * Enter for the details for this user in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO userDetails (username, password, email) VALUES(?, ?, ?)");
            ps.setString(1, this.username);
            ps.setString(2, this.password);
            ps.setString(3, this.email);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new ServletException("Persist Problem: registering user details ", e);
        }
    }
   
}
