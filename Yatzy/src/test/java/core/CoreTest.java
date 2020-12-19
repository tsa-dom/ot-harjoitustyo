/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.util.List;
import java.util.Properties;
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
public class CoreTest {
    Core core;
    
    public CoreTest() {
    }
    
    @Before
    public void setUp() {
        core = new Core();
        core.install("Test/");
    }
    
    @After
    public void tearDown() {
        Core.sqlAsker().executeStatement("DROP TABLE users;", "data", "Test/");
        Core.sqlAsker().executeStatement("DROP TABLE scoreboard;", "data", "Test/");
        Core.sqlAsker().executeStatement("DROP TABLE objectives;", "data", "Test/");
    }

    @Test
    public void sqlAskerWorksTest() {
        assertTrue(Core.sqlAsker().executeStatement("INSERT INTO users (username,password) VALUES ('alaskjd', 'renlsnes')", "data", "Test/"));
        assertTrue(Core.sqlAsker().executeStatement("INSERT INTO objectives (name,maxScore,requirements,gamemode) VALUES ('skjd LKJ','2532','5x6xm','test')", "data", "Test/"));
        assertTrue(Core.sqlAsker().executeStatement("INSERT INTO scoreboard (username,score,gamemode,maxScore) VALUES ('alaskjd', '293', 'lskjfd', 534)", "data", "Test/"));
    }
    @Test
    public void sqlLoaderWorksTest() {
        List<String> statements = Core.sqlLoader().searchStatements("objectives");
        Core.sqlAsker().executeStatements(statements, "data", "Test/");
        assertEquals("x2Zx2Zm",Core.sqlAsker().selectFrom("SELECT * FROM objectives", "data", "requirements", "Test/").get(8));
    }
    @Test
    public void getUserGivesCorrectUserTest() {
        Core.setUser("testeri123");
        assertEquals("testeri123", Core.getUser());
    }
    @Test
    public void getGameModeGivesCorrectGamemodeTest() {
        Properties properties = Core.properties().loadProperties("dicelock");
        Core.setGameMode((GameModeIF) new GameMode(properties));
        GameModeIF gameMode = Core.getGameMode();
        assertEquals("Dice Lock",gameMode.getName());
        assertEquals("classic",gameMode.getController());
        assertEquals(2000,gameMode.getReRollTime());
    }
    @Test
    public void propertiesGivesCorrectPropertiesTest() {
        Properties properties = Core.properties().loadProperties("default_cluster");
        assertEquals("3", properties.get("reroll_count"));
        assertEquals("classic", properties.get("controller"));
        assertEquals("false", properties.get("enabled"));
    }
    @Test
    public void getPahtGivesCorrectPathTest() {
        assertEquals(System.getProperty("user.dir") + "/", Core.getPath());
    }
    @Test
    public void addUpperCountAddsCountCorrectTest() {
        for (int i = 0; i < 313; i++) {
            Core.addUpperCount();
        }
        assertEquals(313, Core.getUpperCount());
    }
    @Test 
    public void upperCountClearingWorksTest() {
        Core.addUpperCount();
        assertEquals(1, Core.getUpperCount());
        Core.clearUpperCount();
        assertEquals(0, Core.getUpperCount());
    }
    @Test
    public void setAndGetBonusPointsWorksTest() {
        Core.setBonusPoints(313);
        assertEquals(313, Core.getBonusPoints());
        Core.setBonusPoints(13);
        assertEquals(13, Core.getBonusPoints());
    }
    
}
