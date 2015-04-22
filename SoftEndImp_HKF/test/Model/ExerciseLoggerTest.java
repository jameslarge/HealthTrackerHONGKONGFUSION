/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xmw13bzu
 */
public class ExerciseLoggerTest {
    
    ExerciseLogger exLog;
    
    public ExerciseLoggerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Exercise exercise = new Exercise("Running", "time", 5);
        
        ExerciseProgress exProg1 = new ExerciseProgress(exercise, new HKFDate("2015-04-01"), 10);
        ExerciseProgress exProg2 = new ExerciseProgress(exercise, new HKFDate("2015-04-04"), 10);
        ExerciseProgress exProg3 = new ExerciseProgress(exercise, new HKFDate("2015-04-07"), 10);
        ExerciseProgress exProg4 = new ExerciseProgress(exercise, new HKFDate("2015-04-10"), 10);
        ExerciseProgress exProg5 = new ExerciseProgress(exercise, new HKFDate("2015-04-13"), 10);
        ExerciseProgress exProg6 = new ExerciseProgress(exercise, new HKFDate("2015-04-16"), 10);
        ExerciseProgress exProg7 = new ExerciseProgress(exercise, new HKFDate("2015-04-19"), 10);
        
        exLog = new ExerciseLogger();
        
        exLog.addExerciseProgress(exProg1);
        exLog.addExerciseProgress(exProg2);
        exLog.addExerciseProgress(exProg3);
        exLog.addExerciseProgress(exProg4);
        exLog.addExerciseProgress(exProg5);
        exLog.addExerciseProgress(exProg6);
        exLog.addExerciseProgress(exProg7);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addExerciseProgress method, of class ExerciseLogger.
     */
    @Test
    public void testAddExerciseProgress() {
        System.out.println("addExerciseProgress");
        ExerciseProgress ep = null;
        ExerciseLogger instance = new ExerciseLogger();
        instance.addExerciseProgress(ep);
        
        assertTrue(instance.getExerciseLog().size() == 1);
        assertTrue(instance.getExerciseLog().get(0) == null);
    }

    /**
     * Test of findTotalCalsBurned method, of class ExerciseLogger.
     */
    @Test
    public void testFindTotalCalsBurned() {
        System.out.println("findTotalCalsBurned - not done");
    }

    /**
     * Test of findAverageWeeklyCalsBurned method, of class ExerciseLogger.
     */
    @Test
    public void testFindAverageWeeklyCalsBurned() {
        System.out.println("findAverageWeeklyCalsBurned - not done");
    }

    /**
     * Test of findTotalCalsBurnedThisWeek method, of class ExerciseLogger.
     */
    @Test
    public void testFindTotalCalsBurnedThisWeek() {
        System.out.println("findTotalCalsBurnedThisWeek - not done");
    }

    /**
     * Test of findAverageDailyCalsBurned method, of class ExerciseLogger.
     */
    @Test
    public void testFindAverageDailyCalsBurned() {
        System.out.println("findAverageDailyCalsBurned - not done");  
    }
    /**
     * Test of findTotalCalsBurnedToday method, of class ExerciseLogger.
     */
    @Test
    public void testFindTotalCalsBurnedToday() {
        System.out.println("findTotalCalsBurnedToday - not done");
    }

    /**
     * Test of sortDate method, of class ExerciseLogger.
     */
    @Test
    public void testSortDate() {
        System.out.println("sortDate - uses HKFDate compare method");
    }

    /**
     * Test of find method, of class ExerciseLogger.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find - uses findAll method from ExerciseProgress");
    }

    /**
     * Test of persist method, of class DietLogger.
     */
    @Test
    public void testPersist_0args() throws Exception {
        System.out.println("persist - just uses ExerciseProgress persist method in for loop");
    }

    /**
     * Test of persist method, of class DietLogger.
     */
    @Test
    public void testPersist_MealProgress() throws Exception {
        System.out.println("persist - uses ExerciseProgress persist method");
    }
    
    /**
     * Test of delete method, of class DietLogger.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete - uses ExerciseProgress delete method");
    }
    
    /**
     * Test of deleteAll method, of class DietLogger.
     */
    @Test
    public void testDeleteAll() throws Exception {
        System.out.println("delete - uses ExerciseProgress delete method");
    }

    /**
     * Test of getExerciseLog method, of class ExerciseLogger.
     */
    @Test
    public void testGetExerciseLog() {
        System.out.println("getExerciseLog - simple method");
    }
    /**
     * Test of setExerciseLog method, of class ExerciseLogger.
     */
    @Test
    public void testSetExerciseLog() {
        System.out.println("setExerciseLog - simple method");
    }

    /**
     * Test of findAverageDailyActivityTime method, of class ExerciseLogger.
     */
    @Test
    public void testFindAverageDailyActivityTime() {
        System.out.println("findAverageDailyActivityTime - Just returning Map");
    }

    /**
     * Test of findActivityTimePerDay method, of class ExerciseLogger.
     */
    @Test
    public void testFindActivityTimePerDay() throws Exception {
        System.out.println("findActivityTimePerDay - Just returning Map");
    }

    /**
     * Test of findCalsBurnedPerDay method, of class ExerciseLogger.
     */
    @Test
    public void testFindCalsBurnedPerDay() throws Exception {
        System.out.println("findCalsBurnedPerDay - Just returning Map");
    }

    /**
     * Test of findProgressesBetweenDates method, of class ExerciseLogger.
     */
    @Test
    public void testFindProgressesBetweenDates() {
        System.out.println("findProgressesBetweenDates - simple method");
    }

    /**
     * Test of findExerciseTimeBetweenDates method, of class ExerciseLogger.
     */
    @Test
    public void testFindExerciseTimeBetweenDates() {
        System.out.println("findExerciseTimeBetweenDates - simple method");
    }

    /**
     * Test of findCalsBurnedBetweenDates method, of class ExerciseLogger.
     */
    @Test
    public void testFindCalsBurnedBetweenDates() {
        System.out.println("findCalsBurnedBetweenDates  - simple method");
    }
    /**
     * Test of persist method, of class ExerciseLogger.
     */
    @Test
    public void testPersist_ExerciseProgress() throws Exception {
        System.out.println("persist - uses exerciseprogress persist");
    }
    
}
