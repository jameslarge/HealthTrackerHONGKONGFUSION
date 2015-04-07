package Model.PhysicalHealth;

import Controllers.*;
import Model.*;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;

/**
 *
 * @author xfu13dcu
 */
public class PhysicalHealth {
    
    private int ID;
    private int memberID;    
    private Height height;
    private ArrayList<WeightProgress> physicalHealthLog; 

    public PhysicalHealth() { }
    
    public PhysicalHealth(int ID, int memberID, double heightcm, ArrayList<WeightProgress> physicalHealthLog) {
        this.ID = ID;
        this.memberID = memberID;
        this.height = new Height(heightcm);  
        this.physicalHealthLog = physicalHealthLog;
    }
    
    //to be used upon account creation, when user provides their current height/weight
    public PhysicalHealth(int memberID, double heightcm, Weight initialWeight) {
        this.ID = -1;
        this.memberID = memberID;
        this.height = new Height(heightcm);  
        WeightProgress currentWeight = new WeightProgress(initialWeight, new Date());
        this.physicalHealthLog = new ArrayList<>();
        physicalHealthLog.add(currentWeight);
    }

    public int getID() {
        return ID;
    }
    
    public int getMemberID() {
        return memberID;
    }
    
    public Height getHeight() {
        return height;
    }
    
    public ArrayList<WeightProgress> getPhysicalHealthLog() {
        return physicalHealthLog;
    }
    
    public void setID(int id) {
        this.ID = id;
    }
    
    public void setMemberID(int memberid) {
        this.ID = memberid;
    }
    
    public void setHeight(Height height) {
        this.height = height;
    }
        
    public void setPhysicalHealthLog(ArrayList<WeightProgress> physicalHealthLog) {
        this.physicalHealthLog = physicalHealthLog;
    }
    
    
    /**
     * Method to find User using email address
     *
     * @param email Email Address of User
     * @param password Password of User
     * @return User object, if found
     * @throws ServletException Exception, User was not found
     */
    public static PhysicalHealth find(int memberID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM physicalHealth WHERE (memberID = ?)");
            ps.setInt(1, memberID);
            ResultSet result = ps.executeQuery();//Run statement
            PhysicalHealth physicalHealth = new PhysicalHealth(); //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                physicalHealth.setID(result.getInt("ID"));
                physicalHealth.setHeight(new Height(result.getInt("heightcm")));
                physicalHealth.setMemberID(result.getInt("memberID"));
                
            }
            
            return physicalHealth;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for physicalhealth from memberid", ex);
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
            PreparedStatement ps = con.prepareStatement("INSERT INTO physicalHealth (heightcm, memberID) VALUES(?, ?)");
            ps.setDouble(1, this.height.getCentimetres());
            ps.setInt(2, this.memberID);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Persist Problem: persisting physicalhealth details", e);
        }
    }
    
    
}

