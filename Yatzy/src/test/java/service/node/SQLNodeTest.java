/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.node;

import core.Core;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import service.domain.GameModeIF;
import service.game.GameMode;

/**
 *
 * @author Tapio Salonen
 */
public class SQLNodeTest {
    
    public SQLNodeTest() {
    }
    SQLNode sql;
    PropertiesNode properties;
    
    @Before
    public void setUp() {
        Core core = new Core();
        core.install("Test/");
        Core.sqlAsker().executeStatement("INSERT INTO scoreboard (username, score, gamemode, maxScore) VALUES ('Meg', '313', 'XX', '5')", "data", "Test/");
        Core.sqlAsker().executeStatement("INSERT INTO scoreboard (username, score, gamemode, maxScore) VALUES ('MegDeg', '333', 'Classic', '1')", "data", "Test/");
        Core.sqlAsker().executeStatement("INSERT INTO scoreboard (username, score, gamemode, maxScore) VALUES ('DegMeg', '231', 'Classic', '2')", "data", "Test/");
        Core.sqlAsker().executeStatement("INSERT INTO scoreboard (username, score, gamemode, maxScore) VALUES ('DegDeg', '133', 'Classic', '3')", "data", "Test/");
        Core.sqlAsker().executeStatement("INSERT INTO scoreboard (username, score, gamemode, maxScore) VALUES ('MegMeg', '213', 'Classic', '4')", "data", "Test/");
        properties = new PropertiesNode();
        properties.loadGameModes("classic", "cluster1", "Test/Cluster/");
        Core.setGameMode((GameModeIF) new GameMode(properties.getInGameModes()));
        sql = new SQLNode();
    }
    
    @After
    public void tearDown() {
        Core.sqlAsker().executeStatement("DROP TABLE scoreboard;", "data", "Test/");
    }
    
    @Test
    public void loadStatTest() {
        sql.loadStat("Test/");
        assertEquals("4", sql.getStatMaxScores().get(3));
        assertEquals("DegMeg", sql.getStatPlayers().get(2));
        assertEquals("Classic", sql.getStatGameModes().get(0));
        assertEquals("313", sql.getStatScores().get(1));
    }
    @Test
    public void loadTopStatTest() {
        sql.loadTopStat("Test/");
        assertEquals("MegMeg" ,sql.getStatPlayers().get(2));
        assertEquals("333" ,sql.getStatScores().get(0));
        assertEquals("231" ,sql.getStatScores().get(1));
    }
    @Test
    public void loadObjTest() {
        sql.loadObj("Test/");
        assertEquals("One Pair", sql.getObjNames().get(7));
        assertEquals("Yatzy", sql.getObjNames().get(15));
        assertEquals("Threes", sql.getObjNames().get(2));
        assertEquals("r5Zm", sql.getObjRequirements().get(14));
        assertEquals("d6A2Zm", sql.getObjRequirements().get(12));
        assertEquals("y5Zm", sql.getObjRequirements().get(4));
    }

    /**
     * Test of loadObj method, of class SQLNode.
     */
    @Test
    public void testLoadObj() {
        System.out.println("loadObj");
        String folder = "";
        SQLNode instance = new SQLNode();
        instance.loadObj(folder);
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadStat method, of class SQLNode.
     */
    @Test
    public void testLoadStat() {
        System.out.println("loadStat");
        String folder = "";
        SQLNode instance = new SQLNode();
        instance.loadStat(folder);
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadTopStat method, of class SQLNode.
     */
    @Test
    public void testLoadTopStat() {
        System.out.println("loadTopStat");
        String folder = "";
        SQLNode instance = new SQLNode();
        instance.loadTopStat(folder);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogin method, of class SQLNode.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        String username = "";
        String folder = "";
        SQLNode instance = new SQLNode();
        List<String> expResult = null;
        List<String> result = instance.getLogin(username, folder);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUser method, of class SQLNode.
     */
    @Test
    public void testCreateUser() {
        System.out.println("createUser");
        String username = "";
        String password = "";
        String folder = "";
        SQLNode instance = new SQLNode();
        boolean expResult = false;
        boolean result = instance.createUser(username, password, folder);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjNames method, of class SQLNode.
     */
    @Test
    public void testGetObjNames() {
        System.out.println("getObjNames");
        SQLNode instance = new SQLNode();
        List<String> expResult = null;
        List<String> result = instance.getObjNames();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObjRequirements method, of class SQLNode.
     */
    @Test
    public void testGetObjRequirements() {
        System.out.println("getObjRequirements");
        SQLNode instance = new SQLNode();
        List<String> expResult = null;
        List<String> result = instance.getObjRequirements();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatPlayers method, of class SQLNode.
     */
    @Test
    public void testGetStatPlayers() {
        System.out.println("getStatPlayers");
        SQLNode instance = new SQLNode();
        List<String> expResult = null;
        List<String> result = instance.getStatPlayers();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatScores method, of class SQLNode.
     */
    @Test
    public void testGetStatScores() {
        System.out.println("getStatScores");
        SQLNode instance = new SQLNode();
        List<String> expResult = null;
        List<String> result = instance.getStatScores();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatGameModes method, of class SQLNode.
     */
    @Test
    public void testGetStatGameModes() {
        System.out.println("getStatGameModes");
        SQLNode instance = new SQLNode();
        List<String> expResult = null;
        List<String> result = instance.getStatGameModes();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatMaxScores method, of class SQLNode.
     */
    @Test
    public void testGetStatMaxScores() {
        System.out.println("getStatMaxScores");
        SQLNode instance = new SQLNode();
        List<String> expResult = null;
        List<String> result = instance.getStatMaxScores();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
}
