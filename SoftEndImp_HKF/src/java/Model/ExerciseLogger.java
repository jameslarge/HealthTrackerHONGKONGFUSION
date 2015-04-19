/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controllers.DatabaseAccess;
import Goals.Goal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import java.util.TreeMap;
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

    public TreeMap<String, Integer> findActivityTimePerDay() throws ServletException {
        TreeMap<String, Integer> result = new TreeMap<String, Integer>();

        //build keys/intialise values
        for (ExerciseProgress ep : exerciseLog) {
            result.put(ep.getDate().toString(), 0);
        }

        //build values
        for (ExerciseProgress ep : exerciseLog) {
            result.put(ep.getDate().toString(), result.get(ep.getDate().toString()) + ep.getDuration());
        }

        return result;
    }



public TreeMap<String, Integer> findCalsBurnedPerDay() throws ServletException {
                TreeMap<String, Integer> result = new TreeMap<>();

                //build keys/intialise values
                for (ExerciseProgress ep : exerciseLog) {
                    result.put(ep.getDate().toString(), 0);
                }

                //build values
                for (ExerciseProgress ep : exerciseLog) {
                    result.put(ep.getDate().toString(), result.get(ep.getDate().toString()) + ep.calculateCals());
                }

                return result;
            }

            public ArrayList<ExerciseProgress> findProgressesBetweenDates(HKFDate start, HKFDate end) {
                ArrayList<ExerciseProgress> eps = new ArrayList<>();

                for (ExerciseProgress ep : exerciseLog) {
                    if (ep.getDate().compareTo(start) >= 0 && ep.getDate().compareTo(end) <= 0) {
                        eps.add(ep);
                    }
                }

                return eps;
            }

            public int findExerciseTimeBetweenDates(HKFDate start, HKFDate end) {
                int totalTime = 0;

                for (ExerciseProgress ep : exerciseLog) {
                    if (ep.getDate().compareTo(start) >= 0 && ep.getDate().compareTo(end) <= 0) {
                        totalTime += ep.getDuration();
                    }
                }

                return totalTime;
            }

            public int findCalsBurnedBetweenDates(HKFDate start, HKFDate end) {
                int totalCals = 0;

                for (ExerciseProgress ep : exerciseLog) {
                    if (ep.getDate().compareTo(start) >= 0 && ep.getDate().compareTo(start) <= 0) {
                        totalCals += ep.calculateCals();
                    }
                }

                return totalCals;
            }

            /**
             * Method to Sort information so that Date is in ascending order
             *
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
             * @throws ServletException Exception, PhysicalHealth was not found
             * for member
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

            /**
             * Method to find specific ExerciseProgress Object and delete it
             *
             * @param epID ID of ExerciseProgress we are going to Delete
             * @throws ServletException
             */
            public void delete(int epID) throws ServletException {

                for (int i = 0; i < exerciseLog.size(); i++) {
                    if (exerciseLog.get(i).getID() == epID) {
                        exerciseLog.get(i).delete();
                        exerciseLog.remove(i);
                    }
                }
            }

            /**
             * Method to Delete all ExerciseProgress
             *
             * @throws ServletException
             */
            public void deleteAll() throws ServletException {
                for (ExerciseProgress exProg : exerciseLog) {
                    exProg.delete();
                }
                exerciseLog.clear();
            }

        }
