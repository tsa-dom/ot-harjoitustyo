/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import service.node.PropertiesNode;

/**
 *
 * @author Tapio Salonen
 */
public class SceneLogicTest {
    
    public SceneLogicTest() {
    }
    private PropertiesNode properties;
    private SceneLogic sceneLogic;
    
    @Before
    public void setUp() {
        sceneLogic = new SceneLogic();
        Core core = new Core();
        core.install("Test/");
        properties = new PropertiesNode();
        properties.loadGameModes("classic", "cluster1", "Test/Cluster/");
        Core.setGameMode(new GameMode(properties.getInGameModes()));
    }
    
    @After
    public void tearDown() {
        
    }
    @Test
    public void newReRollCountTest() {
        assertEquals(3, sceneLogic.newReRollCount());
        properties.loadGameModes("lockpick2", "cluster1", "Test/Cluster/");
        Core.setGameMode(new GameMode(properties.getInGameModes()));
        assertEquals(6, sceneLogic.newReRollCount());
        assertEquals(9, sceneLogic.newReRollCount());
    }
    @Test
    public void getReRollStatusTest() throws InterruptedException {
        assertEquals(2, sceneLogic.getReRollStatus());
        TimeUnit.SECONDS.sleep(3);
        assertEquals(1, sceneLogic.getReRollStatus());
    }
    @Test
    public void getReRollCountTest() {
        assertEquals(3, sceneLogic.getReRollCount());
    }
}