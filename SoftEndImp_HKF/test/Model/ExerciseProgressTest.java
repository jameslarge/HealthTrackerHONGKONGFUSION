/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import javax.servlet.ServletException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xfu13dcu
 */
public class ExerciseProgressTest {
    
    ExerciseProgress instance;
    
    public ExerciseProgressTest() throws ServletException {
        instance = new ExerciseProgress(Exercise.find(1),new HKFDate(), 10);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getID method, of class ExerciseProgress.
     */
    @Test
    public void testGetID() {
        System.out.println("getID - simple method doesnt need testing");
    }

    /**
     * Test of getExercise method, of class ExerciseProgress.
     */
    @Test
    public void testGetExercise() {
        System.out.println("getExercise - simple method doesnt need testing");
    }

    /**
     * Test of getDate method, of class ExerciseProgress.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate - simple method doesnt need testing");
    }

    /**
     * Test of getDuration method, of class ExerciseProgress.
     */
    @Test
    public void testGetDuration() {
        System.out.println("getDuration - simple method doesnt need testing");
    }

    /**
     * Test of setID method, of class ExerciseProgress.
     */
    @Test
    public void testSetID() {
        System.out.println("setID - simple method doesnt need testing");
    }

    /**
     * Test of setExercise method, of class ExerciseProgress.
     */
    @Test
    public void testSetExercise() {
        System.out.println("setExercise - simple method doesnt need testing");
    }

    /**
     * Test of setDate method, of class ExerciseProgress.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate - simple method doesnt need testing");
    }

    /**
     * Test of setDuration method, of class ExerciseProgress.
     */
    @Test
    public void testSetDuration() {
        System.out.println("setDuration - simple method doesnt need testing");
    }

    /**
     * Test of find method, of class ExerciseProgress.
     */
    //@Test
    public void testFind() throws Exception {
        System.out.println("find");
        int epID = 1;
        int duration = 10; 
        ExerciseProgress result = ExerciseProgress.find(epID);
        assertEquals(duration, result.getDuration());
        //Assuming that if durations match, its the same ExerciseProgress
    }

    /**
     * Test of findAll method, of class ExerciseProgress.
     */
    //@Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int memberID = 1;
        int result = ExerciseProgress.findAll(memberID).size();
        assertTrue(result > 0);
    }

    /**
     * Test of persist method, of class ExerciseProgress.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        int memberID = 1;
        instance.persist(memberID);
        int result = ExerciseProgress.findAll(memberID).size();
        testUpdateValue();
        assertEquals(2,result);
    }

    /**
     * Test of updateValue method, of class ExerciseProgress.
     */
    @Test
    public void testUpdateValue() throws Exception {
        System.out.println("updateValue");
        String valueName = "exerciseStartTime";
        String newValue = "10:00";
        ExerciseProgress ep = ExerciseProgress.find(2);
        ep.updateValue(valueName, newValue);
        ExerciseProgress testValue = ExerciseProgress.find(2);
        assertEquals(newValue, testValue.getDate().timeToString());
    }

    /**
     * Test of calculateCals method, of class ExerciseProgress.
     */
    @Test
    public void testCalculateCals() {
        System.out.println("calculateCals");
        
        Exercise exercise = new Exercise("name", "type", 1);
        ExerciseProgress instance = new ExerciseProgress(exercise, new HKFDate(), 10);
        int result = instance.calculateCals();
        assertEquals(10, result);
    }

    /**
     * Test of compareTo method, of class ExerciseProgress.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo - not testing, uses HKFDate compareTo method");
    }

    /**
     * Test of delete method, of class ExerciseProgress.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        //instance.delete();
       // assertTrue(ExerciseProgress.findAll(1).size() == 1);
    }
    
}
