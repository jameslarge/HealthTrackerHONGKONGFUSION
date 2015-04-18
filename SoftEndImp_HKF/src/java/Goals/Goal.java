package Goals;

import Controllers.DatabaseAccess;
import Model.*;
import Model.PhysicalHealth.PhysicalHealth;
import Model.PhysicalHealth.Weight;
import Model.PhysicalHealth.WeightProgress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;

/**
 *
 * @author xmw13bzu
 */
public class Goal implements Comparable<Goal> {

    int goalID;
    HKFDate dateSet;
    HKFDate startDate;
    HKFDate endDate;
    GoalType goalType;
    int target;
    boolean notified;

    public enum GoalType {

        WEIGHT_HIGH(0), WEIGHT_LOW(1), CALORIES_BURNED(2), ACTIVITY_TIME(3), CALORIES_CONSUMED_HIGH(4), CALORIES_CONSUMED_LOW(5);
        final int value;

        GoalType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static GoalType toGoalType(int i) {
            return GoalType.values()[i];
        }

        @Override
        public String toString() {
            switch (value) {
                case 0:
                    return "Weight Gain";
                case 1:
                    return "Weight Loss";
                case 2:
                    return "Calorie Burn";
                case 3:
                    return "Exercise Time";
                case 4:
                    return "Min Calorie Consumption";
                default:
                    return "Max Calorie Consumption";
            }
        }
    }

    //All data available: getting goal from database
    public Goal(int goalID, HKFDate dateSet, HKFDate startDate, HKFDate endDate, GoalType goalType, int target, boolean notified) {
        this.goalID = goalID;
        this.dateSet = dateSet;
        this.startDate = startDate;
        this.endDate = endDate;
        this.goalType = goalType;
        this.target = target;
        this.notified = notified;
    }

    //User creating a goal
    public Goal(HKFDate startDate, HKFDate endDate, GoalType goalType, int target) {
        this.goalID = -1;
        this.dateSet = new HKFDate(); //today's date
        this.startDate = startDate;
        this.endDate = endDate;
        this.goalType = goalType;
        this.target = target;
        this.notified = false;
    }

    public int getGoalID() {
        return goalID;
    }

    public void setGoalID(int goalID) {
        this.goalID = goalID;
    }

    public HKFDate getDateSet() {
        return dateSet;
    }

    public void setDateSet(HKFDate dateSet) {
        this.dateSet = dateSet;
    }

    public HKFDate getStartDate() {
        return startDate;
    }
    
    public boolean isNotified () {
        return notified;
    }


    public void setStartDate(HKFDate startDate) {
        this.startDate = startDate;
    }

    public HKFDate getEndDate() {
        return endDate;
    }

    public void setEndDate(HKFDate endDate) {
        this.endDate = endDate;
    }

    public GoalType getGoalType() {
        return goalType;
    }

    public void setGoalType(GoalType goalType) {
        this.goalType = goalType;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
    
    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    @Override
    public String toString() {
        return "Goal{" + "goalID=" + goalID + ", dateSet=" + dateSet + ", startDate=" + startDate + ", endDate=" + endDate + ", goalType=" + goalType + ", target=" + target + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Goal) {
            Goal g = (Goal) o;

            return goalID == g.goalID
                    && dateSet == g.dateSet
                    && startDate == g.startDate
                    && endDate == g.endDate
                    && goalType == g.goalType
                    && target == g.target
                    && notified == g.notified;
        }

        return false;
    }

    /**
     * Method to find Goal from database using
     *
     * @param goalID
     * @return
     * @throws ServletException
     */
    public static Goal find(int goalID) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //Search through Exercise Table inside Database
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM goal WHERE (id = ?)");
            ps.setInt(1, goalID);
            ResultSet result = ps.executeQuery();//Run statement
            Goal goal = null; //Creating a Goal object to set returned value to
            //If we find Goal set create a new Goal using returned values
            if (result.next()) {
                goal = new Goal(goalID,
                        new HKFDate(result.getString("goalDate")),
                        new HKFDate(result.getString("goalStart")),
                        new HKFDate(result.getString("goalDeadline")),
                        GoalType.toGoalType(result.getInt("goalType")),
                        result.getInt("target"),
                        result.getBoolean("notified"));
            }

            con.close(); //Close Connection
            return goal;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for Goal, id: " + goalID, ex);
        }
    }

    /**
     * Method to find a Goal's ID in the case that it hasn't been set in the bean already.
     * Intended for when a new Goal has just been persisted, and we need to find the id 
     * generated by the database during that persist, so that the new Goal's id can be stored
     * in it's associated member_goals
     *
     * @param goal
     * @return id of goal, if found 
     * @throws ServletException Exception, Goal was not found
     */
    public static int findID(Goal goal) throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //SQL Statement to run
            PreparedStatement ps = con.prepareStatement(
                    "SELECT id FROM goal WHERE (goalType = ? AND goalDate = ? AND goalStart = ? AND goalDeadline = ? AND target = ? AND notified = ?)");
            ps.setInt(1, goal.goalType.ordinal());
            ps.setString(2, goal.dateSet.toString());
            ps.setString(3, goal.startDate.toString());
            ps.setString(4, goal.endDate.toString());
            ps.setInt(5, goal.target);
            ps.setBoolean(6, goal.notified);
            
            ResultSet result = ps.executeQuery();//Run statement
            int id = -1;
            
            if (result.next())
                id = result.getInt("id");
            
            con.close();
            
            return id;
        } catch (SQLException ex) {
            throw new ServletException(
                    "Find Problem: searching for goalID in Goal.findID, "
                            + goal, ex);
        }
    }
    
    /**
     * Method to find all Goals from database
     *
     * @param memberID 
     * @return ArrayList of Goals
     * @throws ServletException
     */
    public static ArrayList<Goal> findAll() throws ServletException {
        try {
            //Connect to Database
            Connection con = DatabaseAccess.getConnection();
            //Get all data from exercise Table                
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM goal");

            ResultSet result = ps.executeQuery();//Run statement
            ArrayList<Goal> goalList = new ArrayList<>();
            //If we find Goal set create a new Meal using returned values
            while (result.next()) {
                Goal goal = new Goal(result.getInt("id"),
                        new HKFDate(result.getString("goalDate")),
                        new HKFDate(result.getString("goalStart")),
                        new HKFDate(result.getString("goalDeadline")),
                        GoalType.toGoalType(result.getInt("goalType")),
                        result.getInt("target"),
                        result.getBoolean("notified"));
                goalList.add(goal);
            }

            con.close(); //Close Connection
            return goalList;
        } catch (SQLException ex) {
            throw new ServletException("Find Problem: Searching for all Goals", ex);
        }
    }

    /**
     * Method to add information to the goal table
     * @param memberID ID of member that we are adding information for
     * @throws ServletException 
     */
    public void persist() throws ServletException {
        try {
            Connection con = DatabaseAccess.getConnection();

            PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO goal (goalType, goalDate, goalStart, goalDeadline, target, notified) VALUES(?, ?, ?, ?, ?, ?)");
            
            ps.setInt(1, goalType.ordinal());
            ps.setString(2, dateSet.toString());
            ps.setString(3, startDate.toString());
            ps.setString(4, endDate.toString());
            ps.setInt(5, target);
            ps.setBoolean(6, notified);

            ps.executeUpdate();
            
            con.close();
            
        } catch (SQLException ex) {
            throw new ServletException(
                    "Persist Problem: persisting goal details", ex);
        }
    }
    
    /**
     * Compares based on endDate
     * @param g
     * @return 
     * @throws ServletException 
     */
    @Override
    public int compareTo(Goal g) {
        return endDate.compareTo(g.endDate);
    }
    
    public int checkProgress(PhysicalHealth physHealth, DietLogger dietLog, ExerciseLogger exLog) {
            double progressPercent = 0;

            switch (goalType) {
                    case WEIGHT_HIGH: {
                        physHealth.sortDate();
                        Weight currentWeight = physHealth.getMostRecentWeight();
                        Weight startweight = physHealth.findWeightOnDate(startDate);
                        
                        if (currentWeight.getGrams() >= target)
                            return 100; //100% complete
                        else {
                            int targetDifference = startweight.getGrams() - target;
                            int actualDifference = currentWeight.getGrams() - target;
                            progressPercent = actualDifference / targetDifference;
                        }
                    }
                    case WEIGHT_LOW: {
                        physHealth.sortDate();
                        Weight currentWeight = physHealth.getMostRecentWeight();
                        Weight startweight = physHealth.findWeightOnDate(startDate);
                        
                        if (currentWeight.getGrams() <= target)
                            return 100; //100% complete
                        else {
                            int targetDifference = startweight.getGrams() - target;
                            int actualDifference = currentWeight.getGrams() - target;
                            progressPercent = (double)actualDifference / (double)targetDifference;
                        }
                    }
                    case CALORIES_BURNED:

                    case ACTIVITY_TIME:
                        int time = exLog.findExerciseTimeBetweenDates(startDate, endDate);
                        progressPercent = (double)time / (double)target;
                        
                    case CALORIES_CONSUMED_HIGH:
                    case CALORIES_CONSUMED_LOW:
                        calorieseaten = 0
                        for each meal since startdate
                                calorieseaten += mealcalories
                        progresspercent = calorieseaten / target
                        //result interpreted depending on whether target is low (calorie cutting)
                        //or high (bulking)
                        //if low, goal is FAILED if 100% is reached
                        //if high, goal is SUCCEEDED if 100% is reached
            }

            return (int)(progressPercent*100);
    }
}
