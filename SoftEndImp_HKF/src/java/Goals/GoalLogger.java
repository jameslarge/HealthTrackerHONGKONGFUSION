/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Goals;

import java.util.ArrayList;
import Model.*;
import Model.PhysicalHealth.PhysicalHealth;
import java.util.Collections;
import java.util.Objects;
import javax.servlet.ServletException;

/**
 *
 * @author xmw13bzu
 */
public class GoalLogger {
    int memberID;
    ArrayList<Goal> goalList;

    public GoalLogger(int memberID, ArrayList<Goal> goalList) {
        this.memberID = memberID;
        this.goalList = goalList;
    }
    
    public GoalLogger(int memberID) {
        this.memberID = memberID;
        this.goalList = new ArrayList<>();
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    public void setGoalList(ArrayList<Goal> goalList) {
        this.goalList = goalList;
    }
    
    public void addGoal(Goal mg) {
        goalList.add(mg);
    }

    
    
    //returns all goals for which the end date HAS PASSED
    //used to notify user, find result of each goal in separate function
    // ** need to add a boolean field to goal and in the db to indicate whether
    //the results of this goal have already been shown to the user
    //cant just say if the goal end date was yesterday, as user may not log in
    //for a few days and miss a goal entirely
    //and also cant just display every finished goal every time, that's not the point of this function
    public ArrayList<Goal> findFinishedGoals() {
            ArrayList<Goal> finishedGoals = new ArrayList<>();

            HKFDate today = new HKFDate();
            
            for (Goal goal : goalList)
                if (goal.getEndDate().compareTo(today) < 0 && !goal.isNotified()) 
                    finishedGoals.add(goal);

            return finishedGoals;
    }


    //returns all goals for which the end date is TODAY, goal will be 'FINISHED' the next day 
    //i.e user could log their progress that finishes the goal at 23:59 on the endDate and 
    //succeed the goal
    //used to notify user, find progress towards each goal in separate function
    public ArrayList<Goal> findDueGoals() {
            ArrayList<Goal> dueGoals = new ArrayList<>();

            HKFDate today = new HKFDate();
            
            for (Goal goal : goalList)
                    if (goal.getEndDate().equals(today))
                            dueGoals.add(goal);

            return dueGoals;
    }

    //returns all goals for which the end date is within the next week (but not today)
    //to be used to notify/remind user 
    public ArrayList<Goal> findUpcomingGoals() {
            ArrayList<Goal> upcomingGoals = new ArrayList<>();

            HKFDate today = new HKFDate();
            HKFDate nextWeek = new HKFDate();
            nextWeek.setDay(today.getDay() + 7);
            
            for (Goal goal : goalList)
                if (goal.getEndDate().compareTo(today) > 0 && goal.getEndDate().compareTo(nextWeek) <= 0)
                    upcomingGoals.add(goal);

            return upcomingGoals;
    }

    //check the progress of each goal in the given list 
    //and return a parallel array of integers corresponding to the percentage of completetion
    //of each
    //if progress returned is >= 100, goal is complete (100% or even surpassed it). using this instead of 
    //jsut bool a bool (in/complete) to allow progress to be shown on a graph etc
    public static ArrayList<Integer> checkProgress(ArrayList<Goal> goalList, int memberID) throws ServletException {
            
        ArrayList<Integer> completeness = new ArrayList<>();
        
        PhysicalHealth ph = PhysicalHealth.find(memberID);
        ExerciseLogger el = ExerciseLogger.find(memberID);
        DietLogger dl = DietLogger.find(memberID);
        
        for (Goal goal : goalList)
            completeness.add(Integer.valueOf(goal.checkProgressAsPercentage(ph, dl, el)));
        
        return completeness;
    }
       
    
    
    @Override
    public String toString() {
        return "GoalLogger{" + "memberID=" + memberID + ", goalList=" + goalList + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GoalLogger other = (GoalLogger) obj;
        if (this.memberID != other.memberID) {
            return false;
        }
        return Objects.equals(this.goalList, other.goalList);
    }
     
    
    /**
     * Method to Sort information so that Date is in ascending order
     * @return Sorted ArrayList of ExerciseProgress
     */
    public ArrayList<Goal> sortDate() {
        Collections.sort(goalList);        
        return goalList;
    }
    
    /**
     * Method to find User using email address
     *
     * @param memberID ID of Member to find the physicalHealth data for
     * @return PhysicalHealth object, if found
     * @throws ServletException Exception, PhysicalHealth was not found for member
     */
    public static GoalLogger find(int memberID) throws ServletException {
        GoalLogger goallog = new GoalLogger(memberID);
        goallog.setGoalList(MemberGoal.findAllGoalsForMember(memberID));
        return goallog;
    }
    
    /**
     * Enter for the details for this entire GoalLogger in the database.
     *
     * @throws ServletException
     */
    public void persist() throws ServletException {
        for (Goal goal : goalList) {
            new MemberGoal(memberID, goal).persist();
        }
    }
    
    /**
     * Log a new goal in the database. (without persisting everything in the log again)
     *
     * @param goal to add
     * @throws ServletException
     */
    public void persist(Goal goal) throws ServletException {
        new MemberGoal(memberID, goal).persist();
    }
    
}
