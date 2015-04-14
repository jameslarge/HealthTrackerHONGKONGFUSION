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
public class DateTest {
        
    public DateTest() {
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
     * Test of setDay method, of class Date.
     */
    @Test
    public void testSetDay() {
        System.out.println("setDay");
        int day = -3;
        Date instance = new Date();
        instance.setDay(day);        
    }

    /**
     * Test of setMonth method, of class Date.
     */
    @Test
    public void testSetMonth() {
        System.out.println("setMonth");
        int month = 13;
        Date instance = new Date();
        instance.setMonth(month);
    }

    /**
     * Test of setYear method, of class Date.
     */
    @Test
    public void testSetYear() {
        System.out.println("setYear");
        int year = 2013;
        Date instance = new Date();
        instance.setYear(year);
    }

    /**
     * Test of monthSize method, of class Date.
     */
    @Test
    public void testMonthSize() {
        System.out.println("monthSize");
        Date instance = new Date(1,1,1111);
        int expResult = 31;
        int result = instance.monthSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Date.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Date instance = new Date(2,3,2015);
        String expResult = "2015-3-2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Date.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Date t = new Date(5,3,2014);
        Date instance = new Date(3,3,2014);
        int expResult = 0;
        int result = instance.compareTo(t);
        System.out.println(result);
    }
    
}
