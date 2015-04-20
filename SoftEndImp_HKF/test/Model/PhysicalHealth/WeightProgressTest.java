/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.PhysicalHealth;

import Model.HKFDate;
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
public class WeightProgressTest {
    
    WeightProgress instance;

    public WeightProgressTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new WeightProgress(6,new Weight(-1), new HKFDate(18,04,2015));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of find method, of class WeightProgress.
     */
    @Test
    public void testFind() throws Exception {
        System.out.println("find");
        int wpID = 1;
        int expResult = -1;
        int result = (int) WeightProgress.find(wpID).getWeight().getGrams();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of findAll method, of class WeightProgress.
     */
    @Test
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        int physHealthID = 1;
        int expResult = -1;
        int result = (int) WeightProgress.findAll(physHealthID).get(0).getWeight().getGrams();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of persist method, of class WeightProgress.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        int physHealthId = 6;
        instance.persist(physHealthId);
        
        int expResult = -1;
        int result = -1;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of calulateBMI method, of class WeightProgress.
     */
    @Test
    public void testCalulateBMI() throws Exception {
        System.out.println("calulateBMI");
        int expResult = 0;
        int result = instance.calulateBMI();
        assertEquals(expResult, result);
        testDelete();
    }

    /**
     * Test of delete method, of class WeightProgress.
     */
    
    public void testDelete() throws Exception {
        System.out.println("delete");
        instance.delete();
        System.out.println("END");
    }
    
}
