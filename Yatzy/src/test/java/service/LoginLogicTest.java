/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import core.Core;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tapio Salonen
 */
public class LoginLogicTest {
    public LoginLogicTest() {
        
    }
    private LoginLogic loginLogic;
    
    @Before
    public void setUp() {
        Core core = new Core();
        core.install("Test/");
        loginLogic = new LoginLogic();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void setUserTest() {
        loginLogic.setUser("313Tester313");
        assertEquals("313Tester313", Core.getUser());
    }
    @Test
    public void correctInputTest() {
        assertEquals("Username is too short", loginLogic.correctInput("dfd", "asalkfdjsal"));
        assertEquals("Username is too long", loginLogic.correctInput("dfasdfasdfsadfsdfsdfsdfsdfsdfsdfd", "asalkfdjsal"));
        assertEquals("Password is too short", loginLogic.correctInput("dfdadsf", "sal"));
        assertEquals("Password is too long", loginLogic.correctInput("dfasdfd", "asalkfdjasdfasdfasdfasdfasfddasfsal"));
        assertEquals("", loginLogic.correctInput("dfasdfd", "asalkfdjsal"));
    }
}
