/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tapio Salonen
 */
public class ObjectiveTest {
    
    Objective objective;
    Objective objective2;
    Objective objective3;
    
    public ObjectiveTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Objective.nextId = 313;
        objective = new Objective("Test Object", "asjdfhse");
        objective2 = new Objective("Test", "sdlfj");
        objective3 = new Objective("Object", "1122142");
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void getCorrectName(){
        assertEquals("Test Object", objective.getName());
    }
    @Test
    public void getCorrectId(){
        assertEquals(315, objective3.getId());
    }
    @Test
    public void getCorrectPoints(){
        assertEquals("---", objective.getPoints());
    }
    @Test
    public void getCorrectPoints2(){
        objective.setPoints("500");
        assertEquals("500", objective.getPoints());
    }
    @Test
    public void getCorrectRequirement(){
        assertEquals("sdlfj", objective2.getRequirement());
    }
    @Test
    public void correctStringToString(){
        assertEquals("Object", objective3.toString());
    }
}
