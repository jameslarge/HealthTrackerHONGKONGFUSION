/**
 * This Class will Model the Users information
 */
package Model;

/**
 * 
 * @author xfu13dcu
 */
public class User {
    
   private String username;
   private String password;
   private String email;
   
   User(String username, String password, String email){
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
   
}
