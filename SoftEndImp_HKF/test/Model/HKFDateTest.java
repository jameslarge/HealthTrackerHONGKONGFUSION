/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

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
public class HKFDateTest {
        
    public HKFDateTest() {
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
     * Test of setDay method, of class HKFDate.
     */
    @Test
    public void testSetDay() {
        System.out.println("setDay");
        int day = -3;
        HKFDate instance = new HKFDate();
        instance.setDay(day);        
    }

    /**
     * Test of setMonth method, of class HKFDate.
     */
    @Test
    public void testSetMonth() {
        System.out.println("setMonth");
        int month = 13;
        HKFDate instance = new HKFDate();
        instance.setMonth(month);
    }

    /**
     * Test of setYear method, of class HKFDate.
     */
    @Test
    public void testSetYear() {
        System.out.println("setYear");
        int year = 2013;
        HKFDate instance = new HKFDate();
        instance.setYear(year);
    }

    /**
     * Test of monthSize method, of class HKFDate.
     */
    @Test
    public void testMonthSize() {
        System.out.println("monthSize");
        HKFDate instance = new HKFDate(1,1,1111);
        int expResult = 31;
        int result = instance.monthSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class HKFDate.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HKFDate instance1 = new HKFDate(2,3,2015);
        String expResult1 = "2015-03-02";
        String result1 = instance1.toString();
        
        String expResult2 = "2015-05-27";
        HKFDate instance2 = new HKFDate(expResult2);
        String result2 = instance2.toString();
        
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of compareTo method, of class HKFDate.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        HKFDate t = new HKFDate(5,3,2014);
        HKFDate instance = new HKFDate(3,3,2014);
        int expResult = 0;
        int result = instance.compareTo(t);
        System.out.println(result);
    }
    
}
