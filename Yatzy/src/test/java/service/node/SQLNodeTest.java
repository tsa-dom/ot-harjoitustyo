/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.node;

import core.Core;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import service.domain.GameModeIF;
import service.domain.PropertiesNodeIF;
import service.game.GameMode;

/**
 *
 * @author Tapio Salonen
 */
public class SQLNodeTest {
    
    public SQLNodeTest() {
    }
    SQLNode sql;
    PropertiesNodeIF properties;
    
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
}
