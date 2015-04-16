/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Meal;

import Controllers.DatabaseAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;

/**
 *
 * @author xfu13dcu
 */
public class MealProgress implements Comparable<MealProgress> {
    private int ID;
    private Meal meal;
    private Date date;
    //private String mealTime;
    private int amount;
    private MealTime mealTime;
    
    public enum MealTime{ 
        
        BREAKFAST(0), LUNCH(1), DINNER(2), SNACK(3);
        final int value;
       
    
        MealTime(int value) {
            this.value = value;
        }    
         
        public int getValue() {
            return value;
        }
        
        /**
        * Method to convert a string to a MealTime
        * @param str string to convert
        * @return relevant mealtime
        * @throws ServletException 
        */
        public static MealTime toMealTime(String str) {
            switch (str) {
                case "breakfast":
                    return BREAKFAST;
                case "lunch":
                    return LUNCH;
                case "dinner":
                    return DINNER;
                default:
                    return SNACK;
            }          
        }
        
        @Override
        public String toString() {
            switch (value) {
                case 0:
                    return "breakfast";
                case 1:
                    return "lunch";
                case 2:
                    return "dinner";
                default:
                    return "snack"; 
            }            
        }
    }

    public MealProgress(int ID, Meal meal, Date date, int amount, int mealTime) {
        this.ID = ID;
        this.meal = meal;
        this.date = date;
        this.amount = amount;
        setMealTime(mealTime);
    }
    
    public MealProgress(Meal meal, Date date, int amount, int mealTime) {
        this.ID = -1;
        this.meal = meal;
        this.date = date;
        this.amount = amount;
        setMealTime(mealTime);
    }

    //Getter Methods 
    
    public int getID() {
        return ID;
    }

    public Meal getMeal() {
        return meal;
    }

    public Date getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
    
    MealTime getMealTime(){
        return mealTime;
    }
    
    //End of Getter Methods

    //Setter Methods
    
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMealID(Meal meal) {
        this.meal = meal;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
        
    final void setMealTime(int pos) {
        mealTime = MealTime.values()[pos];
    }
    
    //End of Setter Methods
    
    public int calcCalories() {
        return meal.getCalsPerUnit() * amount;
    }
    
    /**
     * Method to find MealProgress using ID 
     * @param mpID
     * @return
     * @throws ServletException 
     */
    public static MealProgress find(int mpID) throws ServletException{
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM mealProgress WHERE (id = ?)");
            ps.setInt(1, mpID);
            ResultSet result = ps.executeQuery();//Run statement
            MealProgress mProgress = null; //Creating a MealProgress object to set returned value to
            //If we find User set create a new User using returned values
            if (result.next()) {
                mProgress = new MealProgress(Meal.find(result.getInt("mealID")),
                                             result.getDate("mealDate"),
                                             result.getInt("amount"),
                                             result.getInt("mealTime"));
            }
            
            return mProgress;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for mealProgress from mealProgressID: "
                    + mpID, ex);
        }
    }
    
    /**
     * Method to Find MealProgress information for member
     * @param memberID member we are looking at ID
     * @return ArrayList of Meal Progress
     * @throws ServletException 
     */
    public static ArrayList<MealProgress> findAll(int memberID) throws ServletException{
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run, where ? is email address
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM mealProgress WHERE (memberID = ?)");
            ps.setInt(1, memberID);
            ResultSet result = ps.executeQuery();//Run statement
            ArrayList<MealProgress> mpList = new ArrayList<>(); //Creating a User object to set returned value to
            //If we find User set create a new User using returned values
            while (result.next()) {
                MealProgress mProgress = new MealProgress(Meal.find(result.getInt("mealID")),
                                             result.getDate("mealDate"),
                                             result.getInt("amount"),
                                             result.getInt("mealTime"));
                
                mpList.add(mProgress);
            }            
            return mpList;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for mealProgess for memberID: "
                    + memberID, ex);
        }
    }
    
    
    /**
     * Method to add information to the mealProgress table
     * @param memberID ID of member that we are adding information for
     * @throws ServletException 
     */
    public void persist(int memberID) throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO mealProgress (memberID, mealID, mealTime, mealDate, amount) VALUES(?, ?, ?, ?, ?)");
            
            ps.setInt(1, memberID);

            //http://stackoverflow.com/questions/530012/how-to-convert-java-util-date-to-java-sql-date
            
            ps.setInt(2, meal.getID());
            ps.setInt(3, mealTime.ordinal());
            ps.setDate(4, new java.sql.Date(date.getTime()));
            ps.setInt(5, amount);

            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            throw new ServletException(
                    "Persist Problem: persisting mealProgress details, memberID: " + memberID, ex);
        }
    }
    
    /**
     * Method to sort Meal Progress into order using date
     * if date are same use Meal Time
     * @param t MealProgress Object
     * @return 1,0,-1
     */
    @Override
    public int compareTo(MealProgress t) {
        if( t.date.compareTo(t.date) == 0){
            if(this.mealTime.equals(t.mealTime)) {
                return 0;
            }
            else if (this.mealTime.value > t.mealTime.value) {
                return 1;
            }
            else {
                return -1;
            }
        } else {
            return 0;
        } 
    }
}
