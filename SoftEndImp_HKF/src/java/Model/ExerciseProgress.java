package Model;

import Controllers.DatabaseAccess;
import Model.PhysicalHealth.Weight;
import Model.PhysicalHealth.WeightProgress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;

public class ExerciseProgress implements Comparable<ExerciseProgress> {
    private int ID;
    private Exercise exercise;
    private Date date;
    private int duration;
    private int amount;

    public ExerciseProgress(int ID, Exercise exercise, Date date, int duration, int amount) {
        this.ID = ID;
        this.exercise = exercise;
        this.date = date;
        this.duration = duration;
        this.amount = amount;
    }
    
    public ExerciseProgress(Exercise exercise, Date date, int duration, int amount) {
        this.ID = -1;
        this.exercise = exercise;
        this.date = date;
        this.duration = duration;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }
    
    public Exercise getExercise() {
        return exercise;
    }

    public Date getDate() {
        return date;
    }

    public int getDuration() {
        return duration;
    }

    public int getAmount() {
        return amount;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    /**
     * Method to find ExerciseProgress using ExerciseProgressID
     * @param epID ExerciseProgressID
     * @return the ExerciseProgress
     * @throws ServletException 
     */
    public static ExerciseProgress find(int epID) throws ServletException{
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM exerciseProgress WHERE (id = ?)");
            ps.setInt(1, epID);
            ResultSet result = ps.executeQuery();//Run statement
            ExerciseProgress eProgress = null; //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                eProgress = new ExerciseProgress(
                        Exercise.find(result.getInt("exerciseID")),
                        result.getDate("exerciseDate"),
                        result.getInt("duration"),
                        result.getInt("amount"));
            }
            
            return eProgress;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for exerciseProgess from exerciseProgressID: "
                    + epID, ex);
        }
    }
    
    /**
     * Method to return all Exercise Progress data for a member
     * @param memberID ID of the member we are trying to get information for
     * @return ArrayList of members Exercise Progress information
     * @throws ServletException 
     */
    public static ArrayList<ExerciseProgress> findAll(int memberID) throws ServletException{
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM exerciseProgress WHERE (memberID = ?)");
            ps.setInt(1, memberID);
            ResultSet result = ps.executeQuery();//Run statement
            ArrayList<ExerciseProgress> epList = new ArrayList<>(); //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            while (result.next()) {
                ExerciseProgress eProgress = new ExerciseProgress(
                        Exercise.find(result.getInt("exerciseID")),
                        result.getDate("exerciseDate"),
                        result.getInt("duration"),
                        result.getInt("amount"));
                
                epList.add(eProgress);
            }            
            return epList;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for exerciseProgess for memberID: "
                    + memberID, ex);
        }
    }
    
    /**
     * Method to add information to the exerciseProgress table
     * @param memberID ID of member that we are adding information for
     * @throws ServletException 
     */
    public void persist(int memberID) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO exerciseProgress (memberID, exerciseDate, amount, duration, exerciseID) VALUES(?, ?, ?, ?, ?)");
            
            ps.setInt(1, memberID);

            //http://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
            ps.setDate(2, new java.sql.Date(date.getTime()));
            ps.setInt(3, amount);
            ps.setInt(4, duration);
            ps.setInt(5, exercise.getID());

            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            throw new ServletException(
                    "Persist Problem: persisting exerciseProgress details, memberID: " + memberID, ex);
        }
    }
    
    /**
     * Method to Calculate the Calories burnt depending
     * @return Amount of Calories burnt
     */
    public int calculateCals(){
        if(exercise.getExerciseType() == "Rep"){
            return exercise.getCalPerUnit() * amount;
        }else{
            return exercise.getCalPerUnit() * duration;
        }
    }

    /**
     * Method to be used to sort Exercise Progress using date
     * @param t Exercise Progress Object
     * @return 1,0 or -1
     */
    @Override
    public int compareTo(ExerciseProgress t) {
        return (this.date.compareTo(t.date));
    }
}
