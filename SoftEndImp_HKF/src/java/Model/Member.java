package Model;

import Model.PhysicalHealth.*;
import Controllers.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class Member {

    private String forename;
    private String surname;
    //Goal goals;
    //Health health;

    private int userID; //Users ID Number
    private String username; //User Username
    private String password; //User Password 
    private String email; //User Email

    public Member() { }

    public Member(String username, String password, String email,
            String forename, String surname) {
        this.userID = -1;
        this.username = username;
        this.password = password;
        this.email = email;
        this.forename = forename;
        this.surname = surname;
    }

    
    public Member(int userID, String username, String password, String email,
            String forename, String surname) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.forename = forename;
        this.surname = surname;
    }

    //Getter Methods
    public int getUserID() {
        return userID;
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
    
    public String getForename() {
        return forename;
    }
    
    public String getSurname() {
        return surname;
    }
    //End of Getter Methods

    //Setter Methods
    public void setUserID(int userID) {
        this.userID = userID;
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
    
    public void setForename(String forename) {
        this.forename = forename;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    //End of Setter Methods

    /**
     * Method to find Member using memberID
     *
     * @param memberID
     * @return Member object, if found
     * @throws ServletException Exception, Member was not found
     */
    public static Member find(int memberID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member WHERE (id = ?)");
            ps.setInt(1, memberID);
            
            ResultSet result = ps.executeQuery();//Run statement
            Member member = null; //Creating a Member object to set returned value to
            //If we find Member set create a new Member using returned values
            if (result.next()) {
                member = new Member(memberID,
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("forename"),
                        result.getString("surname")
                    );
            }
            
            con.close();
            
            return member;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for member by memberID: " + memberID, ex);
        }
    }
    
    /**
     * Method to find Member using email address, to check if email is already in use
     *
     * @param email Email Address of User
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static Member findByEmail(String email) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member WHERE (email = ?)");
            ps.setString(1, email);
            
            ResultSet result = ps.executeQuery();//Run statement
            Member member = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                member = new Member(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("forename"),
                        result.getString("surname")
                    );
            }
            
            con.close();
            
            return member;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for user by email ", ex);
        }
    }
    
    /**
     * Method to find Member by username, to check if it's already in use
     *
     * @param username
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static Member findByUsername(String username) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member WHERE (username = ?)");
            ps.setString(1, username);
            
            ResultSet result = ps.executeQuery();//Run statement
            Member member = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                member = new Member(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("forename"),
                        result.getString("surname")
                    );
            }
            
            con.close();
            
            return member;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for user by username ", ex);
        }
    }
    
    /**
     * Method to find Member using email address and password, used when logging in
     *
     * @param email Email Address of User
     * @param password Password of User
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static Member find(String email, String password) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM member WHERE (email = ? AND password = ?)");
            ps.setString(1, email);
            ps.setString(2, password);
            
            ResultSet result = ps.executeQuery();//Run statement
            Member member = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                member = new Member(result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("email"),
                        result.getString("forename"),
                        result.getString("surname")
                    );
            }
            
            con.close();
            
            return member;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for user while logging in ", ex);
        }
    }
    
    /**
     * Method to find a Member's userID in the case that it hasn't been set in the bean already.
     * Intended for when a new member has just been persisted, and we need to find the id 
     * generated by the database during that persist, so that the new member's physicalHealth
     * can be generated using it
     *
     * @param email Email Address of User
     * @param password Password of User
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static int findID(Member member) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM member WHERE (email = ? AND password = ?)");
            ps.setString(1, member.getEmail());
            ps.setString(2, member.getPassword());
            
            ResultSet result = ps.executeQuery();//Run statement
            int id = -1;
            
            if (result.next())
                id = result.getInt("id");
            
            con.close();
            
            return id;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for userID in Member.findID, "
                            + "email: " + member.getEmail() 
                            + " password: " + member.getPassword(), 
                    ex);
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
            
            PreparedStatement ps = con.prepareStatement("INSERT INTO member (email, password, username, forename, surname) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, this.email);
            ps.setString(2, this.password);
            ps.setString(3, this.username);
            ps.setString(4, this.forename);
            ps.setString(5, this.surname);
            ps.executeUpdate();
            
            con.close();
        } catch (SQLException e) {
            throw new ServletException("Persist Problem: registering user details ", e);
        }
    }
    
    /**
     * Update one of the parameters of this Member in the database
     *
     * @param valueName The name of the value to be changed
     * @param newValue The new value for the above to be set to
     * @throws ServletException
     */
    public void updateValue(String valueName, String newValue) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE member SET " + valueName + " = ? WHERE id = ?;");
            ps.setString(1, newValue);
            ps.setInt(2, userID);
            ps.execute();
        } catch (SQLException ex) {
            throw new ServletException("Problem updating account, field: " + valueName
                                            + " value: " + newValue, ex);
        }
    }
    
    /**
     * Remove this Member from the database
     * @throws SQLException
     * @throws ServletException
     */
    public void delete() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM member WHERE (id = ?)");
            ps.setInt(1, this.userID);
            ps.execute();
        } catch (SQLException ex) {
            throw new ServletException("Problem deleting account");
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof Member)   {
            Member t = (Member) o;
        
            return forename.equals(t.forename)
                && surname.equals(t.surname)
                && username.equals(t.username)
                && password.equals(t.password)
                && email.equals(t.email)
                && userID == t.userID;
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "MemberID: " + userID + 
                "\nUsername: " + username +
                "\nForename: " + forename +
                "\nSurname: " + surname +
                "\nEmail: " + email +
                "\nPassword: " + password; 
    }
}
