/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Controllers.DatabaseAccess;
import Model.PhysicalHealth.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;

/**
 *
 * @author xmw13bzu
 */
public class DietLogger {
    int memberID;
    ArrayList<MealProgress> mealLog;
    
    public DietLogger() { 
        this.memberID = -1;
        this.mealLog = new ArrayList<>();
    }
    
    public DietLogger(int memberID) { 
        this.memberID = memberID;
        this.mealLog = new ArrayList<>();
    }
    
    public DietLogger(int memberID, ArrayList<MealProgress> mealLog) {
        this.memberID = memberID;
        this.mealLog = mealLog;
    }

    public ArrayList<MealProgress> getMealLog() {
        return mealLog;
    }

    public void setMealLog(ArrayList<MealProgress> mealLog) {
        this.mealLog = mealLog;
    }
    
    public void addMealProgress(MealProgress ep) {
        mealLog.add(ep);
    }
    
    
    /**
     * Method to find a Member's DietLogger using their id
     *
     * @param memberID ID of Member to find the physicalHealth data for
     * @return PhysicalHealth object, if found
     * @throws ServletException Exception, PhysicalHealth was not found for member
     */
    public static DietLogger find(int memberID) throws ServletException {
        DietLogger exlog = new DietLogger(memberID);
        mealLog.setMealLog(MealProgress.findAll(memberID));
        return exlog;
    }
    
    /**
     * Enter for the details for this DietLogger in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        for (MealProgress mealProg : mealLog) {
            mealProg.persist(memberID); //passing this memberid
        }
    }
    
    /**
     * Log a new exercise progress in the database.
     *
     * @throws ServletException
     */
    public void persist(MealProgress mealProg) throws ServletException {
        mealProg.persist(memberID); //passing this memberid
    }
    
}
