package Model.PhysicalHealth;

import Controllers.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import javax.servlet.ServletException;

public class WeightProgress implements Comparable<WeightProgress> {

    private int physicalHealthID;
    private Weight weight;
    private Date date;

    public WeightProgress(int physicalHealthID, Weight weight, Date date) {
        this.physicalHealthID = physicalHealthID;
        this.weight = weight;
        this.date = date;
    }
    
    public WeightProgress(Weight weight, Date date) {
        this.physicalHealthID = -1;
        this.weight = weight;
        this.date = date;
    }

    public int getPhysicalHealthID() { 
        return physicalHealthID;
    }
    
    public Weight getWeight() {
        return weight;
    }

    public Date getDate() {
        return date;
    }

    public void setPhysicalHealthID(int physicalHealthID) {
        this.physicalHealthID = physicalHealthID;
    }
    
    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(WeightProgress t) {
        return this.date.compareTo(t.date);
    }

    /**
     * Method to find information about Weight progress
     * @param wpID ID of information we are looking for
     * @return Weight Progress
     * @throws ServletException 
     */
    public static WeightProgress find(int wpID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM weightProgress WHERE (id = ?)");
            ps.setInt(1, wpID);
            ResultSet result = ps.executeQuery();//Run statement
            WeightProgress wp = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                wp = new WeightProgress(
                        result.getInt("physicalHealthID"),
                        new Weight(result.getInt("weight")), 
                        result.getDate("weightDate"));
            }
            
            return wp;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for weightProgess from weightprogressid: "
                    + wpID, ex);
        }
    }
    
    /**
     * Method to get information about member using physical health ID
     * @param physHealthID Foreign ID from Physical Health table
     * @return ArrayList of Weight Progress information
     * @throws ServletException 
     */
    public static ArrayList<WeightProgress> findAll(int physHealthID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM weightProgress WHERE (physicalHealthID = ?)");
            ps.setInt(1, physHealthID);
            ResultSet result = ps.executeQuery();//Run statement
            
            ArrayList<WeightProgress> resultsLog = new ArrayList<>();
            
            while (result.next()) {
                WeightProgress tempWP = new WeightProgress(
                        result.getInt("physicalHealthID"),
                        new Weight(result.getInt("weight")), 
                        result.getDate("weightDate"));
                
                resultsLog.add(tempWP);
            }
            
            return resultsLog;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for weightProgesses from physicalid: "
                    + physHealthID, ex);
        }
    }
    
    /**
     * Method to store Weight Progress information for member
     * @param physHealthId Foreign ID from Physical Health table
     * @throws ServletException 
     */
    public void persist(int physHealthId) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO weightProgress (physicalHealthID, weightDate, weight) VALUES(?, ?, ?)");
            
            ps.setInt(1, physHealthId);

            //http://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
            ps.setDate(2, new java.sql.Date(date.getTime()));
            ps.setInt(3, (int) weight.getGrams());

            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException e) {
            throw new ServletException(
                    "Persist Problem: persisting weightProgress details, physicalHealthID: " + physHealthId, e);
        }
    }

    /**
     * Method to Calculate BMI using grams and cm
     * @return value of members BMI
     * @throws ServletException 
     */
    public int calulateBMI() throws ServletException{
        PhysicalHealth ph = PhysicalHealth.find(physicalHealthID);
        int height = ph.getHeight().getCentimetres();
        
        return (int) (weight.getGrams()/(height * height));
    }
            
}
