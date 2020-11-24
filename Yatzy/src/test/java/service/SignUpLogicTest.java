/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import core.Core;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tapio Salonen
 */
public class SignUpLogicTest {
    private SignUpLogic signLogic;
    private Core core;
    public SignUpLogicTest() {
    }
    
    @Before
    public void setUp() {
        core = new Core();
        signLogic = new SignUpLogic();
        Core.sqlAsker().executeStatement("CREATE TABLE score (username TEXT,score INTEGER,gamemode TEXT,maxScore INTEGER);", "data", "Test/");
    }
    
    @After
    public void tearDown() {
        Core.sqlAsker().executeStatement("DROP TABLE users;", "data", "Test/");
    }
    
    @Test
    public void correctInputTest() {
        TextField username = new TextField("akhsjdfkhsafkalhsfkjshafkjalshfa");
        PasswordField password = new PasswordField();
        Label infoLabel = new Label();
        password.setText("asldfj");
        assertFalse(signLogic.correctInput(username, password, infoLabel));
        username.setText("aösdkfja");
        password.setText("kahlkjfdhslkfjahsdflkjhsafkjhlaksjf");
        assertFalse(signLogic.correctInput(username, password, infoLabel));
        username.setText("skd");
        password.setText("kahlkjfd");
        assertFalse(signLogic.correctInput(username, password, infoLabel));
        username.setText("skdasdf");
        password.setText("kad");
        assertFalse(signLogic.correctInput(username, password, infoLabel));
        username.setText("skdafs");
        password.setText("kahlkjfd");
        assertTrue(signLogic.correctInput(username, password, infoLabel));
    }
    @Test
    public void createUserTest() {
        assertTrue(signLogic.createUser("Tester", "31313", "Test/"));
        assertFalse(signLogic.createUser("Tester", "31313", "Test/"));
        assertTrue(signLogic.createUser("TesterSecond", "wuwuwuwuw", "Test/"));
    }
}
