/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
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
public class ExerciseTest {
    
    public ExerciseTest() {
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
     * Test of getID method, of class Exercise.
     */
    @Test
    public void testGetID() {
        System.out.println("getID - simple method no testing needed");
    }

    /**
     * Test of getExerciseName method, of class Exercise.
     */
    @Test
    public void testGetExerciseName() {
        System.out.println("getExerciseName  - simple method no testing needed");
    }

    /**
     * Test of getExerciseType method, of class Exercise.
     */
    @Test
    public void testGetExerciseType() {
        System.out.println("getExerciseType  - simple method no testing needed");
    }

    /**
     * Test of getCalPerUnit method, of class Exercise.
     */
    @Test
    public void testGetCalPerUnit() {
        System.out.println("getCalPerUnit  - simple method no testing needed");
    }

    /**
     * Test of setID method, of class Exercise.
     */
    @Test
    public void testSetID() {
        System.out.println("setID  - simple method no testing needed");
    }

    /**
     * Test of setExcerciseName method, of class Exercise.
     */
    @Test
    public void testSetExcerciseName() {
        System.out.println("setExcerciseName  - simple method no testing needed");
    }

    /**
     * Test of setExerciseType method, of class Exercise.
     */
    @Test
    public void testSetExerciseType() {
        System.out.println("setExerciseType  - simple method no testing needed");
    }

    /**
     * Test of setCalPerUnit method, of class Exercise.
     */
    @Test
    public void testSetCalPerUnit() {
        System.out.println("setCalPerUnit  - simple method no testing needed");
    }

    /**
     * Test of find method, of class Exercise.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        Exercise expResult = new Exercise("t_name", "t_exercise", -1);
        Exercise result = Exercise.find(1);
        
        assertEquals(expResult.getExerciseName(), result.getExerciseName());
    }

    /**
     * Test of findAll method, of class Exercise.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        Exercise expResult = new Exercise("t_name", "t_exercise", -1);
        ArrayList<Exercise> result = Exercise.findAll();
        assertEquals(expResult.getExerciseName(), result.get(0).getExerciseName());
    }
    
}
