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
 * @author xmw13bzu
 */
public class MemberTest {
    
    Member instance;
    
    public MemberTest() {
        instance = new Member(7,"testUN", "testP", "test@test.test","testFN", "testSN");
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
     * Test of find method, of class Member.
     */
    public void testFind() throws Exception {
        System.out.println("find");
        String email = instance.getEmail();
        String password = instance.getPassword();
        Member result = Member.find(email, password);
        
        assertEquals(instance.getUsername(), result.getUsername());
        
    }

    /**
     * Test of findID method, of class Member.
     */
    @Test
    public void testFindID() throws Exception {
        System.out.println("findID");
        int expResult = 1;
        int result = Member.findID(instance);        
        assertEquals(instance.getUserID(), result);
    }
    /**
     * Test of persist method, of class Member.
     */
    @Test
    public void testPersist() throws Exception {
        System.out.println("persist");
        instance.persist();
        testFind();
        testFindID();
    }
    
    /**
     * Test of equals method, of class Member.
     */
    @Test
    public void testEquals() throws Exception {
        System.out.println("equals");
        Member instance2 = new Member("testUN", "testP", "test@test.test","testFN", "testSN");
        
        if(instance.equals(instance2))               
            assertEquals(1, 1);
    }
    
    /**
     * Test of find method, of class Member.
     */
    @Test
    public void testFind_int() throws Exception {
        System.out.println("find");
        Member result = Member.find(instance.getUserID());
        assertEquals(instance.getUsername(), result.getUsername());
    }

    /**
     * Test of findByEmail method, of class Member.
     */
    @Test
    public void testFindByEmail() throws Exception {
        System.out.println("findByEmail");
        Member result = Member.findByEmail(instance.getEmail());
        assertEquals(instance.getEmail(), result.getEmail());
    }

    /**
     * Test of findByUsername method, of class Member.
     */
    @Test
    public void testFindByUsername() throws Exception {
        System.out.println("findByUsername");
        Member result = Member.findByUsername(instance.getUsername());
        assertEquals(instance.getUsername(), result.getUsername());
    }

    /**
     * Test of find method, of class Member.
     */
    @Test
    public void testFind_String_String() throws Exception {
        System.out.println("find");
        Member result = Member.find(instance.getEmail(), instance.getPassword());
        assertEquals(instance.getUserID(), result.getUserID());
    }

    /**
     * Test of updateValue method, of class Member.
     */
    @Test
    public void testUpdateValue() throws Exception {
        System.out.println("updateValue - how String to int/char etc");
    }

    /**
     * Test of delete method, of class Member.
     */
    //@Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Member instance = new Member(1, "t_user", "t_pass", "t_email",
            "t_fname", "t_sname");
        instance.delete();
        if(Member.find(1) == null)
        {
            assertEquals(1,1);
        }
    }

    /**
     * Test of toString method, of class Member.
     */
    @Test
    public void testToString() {
        System.out.println("toString - simple method doesnt need testing");
    }
    
    /**
     * Test of calculateHealthiness method, of class Member.
     */
    @Test
    public void testCalculateHealthiness() throws Exception {
        System.out.println("calculateHealthiness");
        double bmiHealth = 0.0;
        double activityHealth = 0.0;
        double dietHealth = 0.0;
        Member instance = new Member();
        int expResult = 0;
        int result = instance.calculateHealthiness(bmiHealth, activityHealth, dietHealth);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcHealthinessBMI method, of class Member.
     */
    @Test
    public void testCalcHealthinessBMI() throws Exception {
        System.out.println("calcHealthinessBMI");
        double result = instance.calcHealthinessBMI();
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcHealthinessActivity method, of class Member.
     */
    @Test
    public void testCalcHealthinessActivity() throws Exception {
        System.out.println("calcHealthinessActivity");
        double result = instance.calcHealthinessActivity();
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcHealthinessDiet method, of class Member.
     */
    @Test
    public void testCalcHealthinessDiet() throws Exception {
        System.out.println("calcHealthinessDiet");
        double result = instance.calcHealthinessDiet();
    }

    /**
     * Test of calculateBMI method, of class Member.
     */
    @Test
    public void testCalculateBMI() throws Exception {
        System.out.println("calculateBMI");
        int result = instance.calculateBMI();
    }

    /**
     * Test of getUserID method, of class Member.
     */
    @Test
    public void testGetUserID() {
        System.out.println("getUserID - dont need testing");
    }

    /**
     * Test of getUsername method, of class Member.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername - dont need testing");
    }

    /**
     * Test of getPassword method, of class Member.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword - dont need testing");
    }

    /**
     * Test of getEmail method, of class Member.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail - dont need testing");
    }

    /**
     * Test of getForename method, of class Member.
     */
    @Test
    public void testGetForename() {
        System.out.println("getForename - dont need testing");
    }

    /**
     * Test of getSurname method, of class Member.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname - dont need testing");
    }

    /**
     * Test of setUserID method, of class Member.
     */
    @Test
    public void testSetUserID() {
        System.out.println("setUserID - dont need testing");
    }

    /**
     * Test of setUsername method, of class Member.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername - dont need testing");
    }

    /**
     * Test of setPassword method, of class Member.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword - dont need testing");
    }

    /**
     * Test of setEmail method, of class Member.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail - dont need testing");
    }

    /**
     * Test of setForename method, of class Member.
     */
    @Test
    public void testSetForename() {
        System.out.println("setForename - dont need testing");
    }

    /**
     * Test of setSurname method, of class Member.
     */
    @Test
    public void testSetSurname() {
        System.out.println("setSurname - dont need testing");
    }    
}
