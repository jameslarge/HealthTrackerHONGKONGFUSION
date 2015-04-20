/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;


import Model.Meal.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import javax.servlet.ServletException;

/**
 *
 * @author xmw13bzu
 */
public class DietLogger {
    private int memberID;
    private ArrayList<MealProgress> mealLog;
    
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
   
    public int findAverageDailyCalsConsumed() {
        double result = 0;
        
        for (MealProgress mp : mealLog)
            result += mp.calcCalories();
        
        return (int) (result/mealLog.size());
    }
    
    public void findTotalCalsConsumedToday() {
        //TODO
    }
    
    public int findCalsConsumedBetweenDates(HKFDate start, HKFDate end) {
        int totalCals = 0;
        
        for (MealProgress mp : mealLog) {
            if (mp.getDate().compareTo(start) >= 0 && mp.getDate().compareTo(end) <= 0)
                totalCals += mp.calcCalories();
        }
        
        return totalCals;
    }
    
    public ArrayList<MealProgress> findProgressesBetweenDates(HKFDate start, HKFDate end) {
       ArrayList<MealProgress> mps = new ArrayList<>();

       for (MealProgress mp : mealLog)
            if (mp.getDate().compareToWithoutTime(start) >= 0 && mp.getDate().compareToWithoutTime(end) <= 0)
               mps.add(mp);

       return mps;
    }
    
    public TreeMap<HKFDate, Integer> findCalsConsumedPerDay() throws ServletException {
        TreeMap<HKFDate, Integer> result = new TreeMap<>();
        
        //build keys/intialise values
        for (MealProgress ep : mealLog)
            result.put(ep.getDate(), 0);
        
        //build values
        for (MealProgress ep : mealLog)
            result.put(ep.getDate(), result.get(ep.getDate()) + ep.calcCalories());
        
        return result;
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
    
        /**
     * Method to find specific ExerciseProgress Object and delete it
     * @param epID ID of ExerciseProgress we are going to Delete
     * @throws ServletException 
     */
    public void delete(int mpID) throws ServletException {
             
        for(int i = 0; i < mealLog.size(); i++) {
            if(mealLog.get(i).getID() == mpID) {
                mealLog.get(i).delete();
                mealLog.remove(i);
            }
        }
    }
    
    /**
     * Method to Delete all ExerciseProgress
     * @throws ServletException 
     */
    public void deleteAll() throws ServletException {
        for(MealProgress mProg : mealLog) {
            mProg.delete();
        }
        mealLog.clear();
    }
    
    
}
