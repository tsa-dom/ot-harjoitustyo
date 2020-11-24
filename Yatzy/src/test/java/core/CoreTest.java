/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.File;
import java.io.IOException;
import service.game.GameMode;
import java.util.List;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.io.FileUtils;


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
        Core.sqlAsker().executeStatement("DROP TABLE score;", "data", "Test/");
        Core.sqlAsker().executeStatement("DROP TABLE objectives;", "data", "Test/");
    }

    @Test
    public void sqlAskerTest() {
        assertTrue(Core.sqlAsker().executeStatement("INSERT INTO users (username,password) VALUES ('alaskjd', 'renlsnes')", "data", "Test/"));
        assertTrue(Core.sqlAsker().executeStatement("INSERT INTO objectives (name,maxScore,requirements,gamemode) VALUES ('skjd LKJ','2532','5x6xm','test')", "data", "Test/"));
        assertTrue(Core.sqlAsker().executeStatement("INSERT INTO score (username,score,gamemode,maxScore) VALUES ('alaskjd', '293', 'lskjfd', 534)", "data", "Test/"));
    }
    @Test
    public void sqlLoaderTest() {
        List<String> statements = Core.sqlLoader().searchStatements("objectives");
        Core.sqlAsker().executeStatements(statements, "data", "Test/");
        assertEquals("x2Zx2Zm",Core.sqlAsker().selectFrom("SELECT * FROM objectives", "data", "requirements", "Test/").get(8));
    }
    @Test
    public void getUserTest() {
        Core.setUser("testeri123");
        assertEquals("testeri123", Core.getUser());
    }
    @Test
    public void getGameModeTest() {
        Properties properties = Core.properties().loadProperties("dicelock");
        Core.setGameMode(new GameMode(properties));
        GameMode gameMode = Core.getGameMode();
        assertEquals("Dice Lock",gameMode.getName());
        assertEquals("classic",gameMode.getController());
        assertEquals(2000,gameMode.getReRollTime());
    }
    @Test
    public void getGameModesTest() {
        Core.loadGameModes("Test/");
        ObservableList<GameMode> gameModes = Core.getGameModes();
        GameMode gameMode = gameModes.get(3);
        assertEquals("Two dices lockpick",gameMode.getName());
        assertEquals("lockpick2",gameMode.getController());
        assertEquals(2000,gameMode.getReRollTime());
    }
    @Test
    public void propertiesTest() {
        Properties properties = Core.properties().loadProperties("default_cluster");
        assertEquals("3", properties.get("reroll_count"));
        assertEquals("classic", properties.get("controller"));
        assertEquals("false", properties.get("enabled"));
    }
    @Test
    public void clearGameModesTest() {
        Core.loadGameModes("Test/");
        Core.clearGameModes();
        ObservableList<GameMode> gameModes = FXCollections.observableArrayList();
        assertEquals(gameModes, Core.getGameModes());
    }
    @Test
    public void getPahtTest() {
        assertEquals(System.getProperty("user.dir") + "/", Core.getPath());
    }
}
