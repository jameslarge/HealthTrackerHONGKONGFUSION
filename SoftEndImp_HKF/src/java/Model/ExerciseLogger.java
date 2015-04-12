/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Controllers.DatabaseAccess;
import Model.PhysicalHealth.Height;
import Model.PhysicalHealth.PhysicalHealth;
import Model.PhysicalHealth.Weight;
import Model.PhysicalHealth.WeightProgress;
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
    
    /**
     * Method to find User using email address
     *
     * @param memberID ID of Member to find the physicalHealth data for
     * @return PhysicalHealth object, if found
     * @throws ServletException Exception, PhysicalHealth was not found for member
     */
    public static ExerciseLogger find(int memberID) throws ServletException {
        try {
            ExerciseLogger exlog = new ExerciseLogger(memberID);
            exlog.setExerciseLog(ExerciseProgress.findAll(memberID));
            return exlog;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: searching for exerciseLog from memberid: " + memberID, ex);
        }
    }
    
    /**
     * Enter for the details for this ExerciseLogger in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        try {
            for (ExerciseProgress exProg : exerciseLog) {
                exProg.persist(memberID); //passing this psyhicalHealthId
            }
        } catch (SQLException e) {
            throw new ServletException("Persist Problem: persisting physicalhealth details", e);
        }
    }
    
    /**
     * Log a new exercise progress in the database.
     *
     * @throws ServletException
     */
    public void persist(ExerciseProgress exProg) throws ServletException {
        try {
            exProg.persist(memberID); //passing this psyhicalHealthId
        } catch (SQLException e) {
            throw new ServletException("Persist Problem: persisting physicalhealth details", e);
        }
    }
    
}
