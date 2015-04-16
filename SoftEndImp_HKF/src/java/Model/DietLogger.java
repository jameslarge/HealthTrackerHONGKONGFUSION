/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


import Model.Meal.*;
import java.util.ArrayList;
import java.util.Collections;
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
    
    public void findTotalCalsConsumed() {
        //TODO
    }
    
    public void findAverageWeeklyCalsConsumed() {
        //TODO
    }
    
    public void findTotalCalsConsumedThisWeek() {
        //todo
    }
   
    public void findAverageDailyCalsConsumed() {
        //TODO
    }
    
    public void findTotalCalsConsumedToday() {
        //TODO
    }
    
    /**
     * Method to Sort information so that Date is in ascending order
     * @return Sorted ArrayList of Meal Progress
     */
    public ArrayList<MealProgress> sortDate() {
        Collections.sort(mealLog);
        return mealLog;
    }
    
    /**
     * Method to find a Member's DietLogger using their id
     *
     * @param memberID ID of Member to find the physicalHealth data for
     * @return PhysicalHealth object, if found
     * @throws ServletException Exception, PhysicalHealth was not found for member
     */
    public static DietLogger find(int memberID) throws ServletException {
        DietLogger dietlog = new DietLogger(memberID);
        dietlog.setMealLog(MealProgress.findAll(memberID));
        return dietlog;
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
     * @param mealProg
     * @throws ServletException
     */
    public void persist(MealProgress mealProg) throws ServletException {
        mealProg.persist(memberID); //passing this memberid
    }
    
}
