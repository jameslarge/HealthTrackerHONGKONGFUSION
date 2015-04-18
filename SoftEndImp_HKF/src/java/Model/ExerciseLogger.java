/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;

/**
 *
 * @author xmw13bzu
 */
public class ExerciseLogger {
    int memberID;
    ArrayList<ExerciseProgress> exerciseLog;
    
    public ExerciseLogger() { 
        this.memberID = -1;
        this.exerciseLog = new ArrayList<>();
    }
    
    public ExerciseLogger(int memberID) { 
        this.memberID = memberID;
        this.exerciseLog = new ArrayList<>();
    }
    
    public ExerciseLogger(int memberID, ArrayList<ExerciseProgress> exerciseLog) {
        this.memberID = memberID;
        this.exerciseLog = exerciseLog;
    }

    public ArrayList<ExerciseProgress> getExerciseLog() {
        return exerciseLog;
    }

    public void setExerciseLog(ArrayList<ExerciseProgress> exerciseLog) {
        this.exerciseLog = exerciseLog;
    }
    
    public void addExerciseProgress(ExerciseProgress ep) {
        exerciseLog.add(ep);
    }
    
    public int findTotalCalsBurned() {
        //TODO
        return 1;
    }
    
    public double findAverageWeeklyCalsBurned() {
        //TODO
        return 1;
    }
    
    public int findTotalCalsBurnedThisWeek() {
        //todo
        return 1;
    }
   
    public double findAverageDailyCalsBurned() {
        //TODO
        return 1;
    }
    
    public int findTotalCalsBurnedToday() {
        //TODO
        return 1;
    }
    
    public int findExerciseTimeBetweenDates(HKFDate start, HKFDate end) {
        int totalTime = 0;
        
        for (ExerciseProgress ep : exerciseLog) {
            if (ep.getDate().compareTo(start) >= 0 && ep.getDate().compareTo(start) <= 0)
                totalTime += ep.getDuration();
        }
        
        return totalTime;
    }
    
    
    /**
     * Method to Sort information so that Date is in ascending order
     * @return Sorted ArrayList of ExerciseProgress
     */
    public ArrayList<ExerciseProgress> sortDate() {
        Collections.sort(exerciseLog);        
        return exerciseLog;
    }
    
    /**
     * Method to find User using email address
     *
     * @param memberID ID of Member to find the physicalHealth data for
     * @return PhysicalHealth object, if found
     * @throws ServletException Exception, PhysicalHealth was not found for member
     */
    public static ExerciseLogger find(int memberID) throws ServletException {
        ExerciseLogger exlog = new ExerciseLogger(memberID);
        exlog.setExerciseLog(ExerciseProgress.findAll(memberID));
        return exlog;
    }
    
    /**
     * Enter for the details for this ExerciseLogger in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        for (ExerciseProgress exProg : exerciseLog) {
            exProg.persist(memberID); //passing this psyhicalHealthId
        }
    }
    
    /**
     * Log a new exercise progress in the database.
     *
     * @throws ServletException
     */
    public void persist(ExerciseProgress exProg) throws ServletException {
        exProg.persist(memberID); //passing this psyhicalHealthId
    }
    
}
