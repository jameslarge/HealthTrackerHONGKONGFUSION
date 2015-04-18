package Model.PhysicalHealth;

import Controllers.*;
import Model.HKFDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
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

    public PhysicalHealth() { 
        this.physicalHealthLog = new ArrayList<>();
    }
    
    public PhysicalHealth(int ID, int memberID, int heightcm, ArrayList<WeightProgress> physicalHealthLog) {
        this.ID = ID;
        this.memberID = memberID;
        this.height = new Height(heightcm);  
        this.physicalHealthLog = physicalHealthLog;
    }
    
    //to be used upon account creation, when user provides their current height/weight
    public PhysicalHealth(int memberID, int heightcm, Weight initialWeight) {
        this.ID = -1;
        this.memberID = memberID;
        this.height = new Height(heightcm);  
        WeightProgress currentWeight = new WeightProgress(initialWeight, new HKFDate());
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
    
    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }
    
    public void setHeight(Height height) {
        this.height = height;
    }
        
    public void setPhysicalHealthLog(ArrayList<WeightProgress> physicalHealthLog) {
        this.physicalHealthLog = physicalHealthLog;
    }
    
    public void addWeightProgess(WeightProgress wp) {
        physicalHealthLog.add(wp);
    }
    
    public Weight getMostRecentWeight() {
        return Collections.max(physicalHealthLog).getWeight();
    }
    
    public Weight findWeightOnDate(HKFDate date) {
        if (physicalHealthLog.isEmpty()) 
            return null;
        
        Collections.sort(physicalHealthLog);
        
        int prevDateIndex = 0, postDateIndex = 0; //if no weightProgress entered
        //specifically on the given date, average out the two weightprogresses either side of the date
        //if available
        
        for (WeightProgress wp : physicalHealthLog) {
            int comp = wp.getDate().compareTo(date);
            
            if (comp == 0)
                return wp.getWeight();
            else if (comp < 0) {
                prevDateIndex++;
                postDateIndex = prevDateIndex + 1;
            }   
            else 
                break;
        }
        
        if (postDateIndex == 0) //date is BEFORE any weightprogresses were entered
            return physicalHealthLog.get(0).getWeight();
        if (postDateIndex == physicalHealthLog.size()) //date is AFTER any weightprogresses entered
            return physicalHealthLog.get(physicalHealthLog.size()-1).getWeight();
        
        return new Weight((physicalHealthLog.get(prevDateIndex).getWeight().getGrams() + physicalHealthLog.get(postDateIndex).getWeight().getGrams()) / 2);
    }
    
    public int findTotalWeightDifferenceVersusDate(HKFDate dateToCompareAgainst) {
        //TODO
        return 1;
    }
    
    public double findAverageWeightDifferencePerWeekVersusDate(HKFDate dateToCompareAgainst) {
        //TODO
        return 1.0;
    }
    
    public double findAverageWeightDifferencePerMonthVersusDate(HKFDate dateToCompareAgainst) {
        //TODO
        return 1.0;
    }
    
    /**
     * Method to Sort information so that Date is in ascending order
     * @return Sorted ArrayList of WeightProgress
     */
    public ArrayList<WeightProgress> sortDate() {
        Collections.sort(physicalHealthLog);
        return physicalHealthLog;
    }
    
    /**
     * Method to find Users Physical Health details using members ID
     *
     * @param memberID ID of Member to find the physicalHealth data for
     * @return PhysicalHealth object, if found
     * @throws ServletException Exception, PhysicalHealth was not found for member
     */
    public static PhysicalHealth find(int memberID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement physHealthStatement = con.prepareStatement(
                    "SELECT * FROM physicalHealth WHERE (memberID = ?)");
            physHealthStatement.setInt(1, memberID);
            ResultSet physHealthResult = physHealthStatement.executeQuery();//Run statement
            PhysicalHealth physicalHealth = new PhysicalHealth();
            
            
            if (physHealthResult.next()) {
                physicalHealth.setID(physHealthResult.getInt("ID"));
                physicalHealth.setHeight(new Height(physHealthResult.getInt("heightcm")));
                physicalHealth.setMemberID(physHealthResult.getInt("memberID"));
            }
            
            con.close();
            
            physicalHealth.physicalHealthLog = WeightProgress.findAll(physicalHealth.getID());
            
            return physicalHealth;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for physicalhealth from memberid: " + memberID, ex);
        }
    }
    
     /**
     * Method to find a PhysicalHealth's ID in the case that it hasn't been set in the bean already.
     * Intended for when a new PhysicalHealth has just been persisted, and we need to find the id 
     * generated by the database during that persist, so that the new weightprogresses
     * can be generated using it
     *
     * @param ph The PhysicalHealth to find the id of
     * @return id of given PhysicalHealth, if found
     * @throws ServletException Exception, PhysicalHealth was not found
     */
    public static int findID(PhysicalHealth ph) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM physicalHealth WHERE (memberID = ?)");
            ps.setInt(1, ph.getMemberID());
            
            ResultSet result = ps.executeQuery();//Run statement
            int id = -1;
            
            if (result.next())
                id = result.getInt("id");
            
            con.close();
            
            return id;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for physicalHealthId in PhysicalHealth.findID, "
                            + "memberID: " + ph.getMemberID(),
                    ex);
        }
    }


    /**
     * Enter for the details for this PhysicalHealth in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO physicalHealth (heightcm, memberID) VALUES(?, ?)");
            ps.setDouble(1, this.height.getCentimetres());
            ps.setInt(2, this.memberID);
            ps.executeUpdate();
            
            con.close();
            
            this.ID = PhysicalHealth.findID(this); //find id generated by database
            
            for (WeightProgress weightProg : physicalHealthLog) {
                weightProg.persist(ID); //passing this psyhicalHealthId
            }
            
        } catch (SQLException e) {
            throw new ServletException("Persist Problem: persisting physicalhealth details", e);
        }
    }
    
    
}

