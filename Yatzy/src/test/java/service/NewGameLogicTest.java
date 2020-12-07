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
import service.domain.GameModeIF;
import service.game.GameMode;
import service.node.PropertiesNode;

/**
 *
 * @author Tapio Salonen
 */
public class NewGameLogicTest {
    
    public NewGameLogicTest() {
    }
    private NewGameLogic newGameLogic;
    private PropertiesNode properties;
    
    @Before
    public void setUp() {
        newGameLogic = new NewGameLogic();
        properties = new PropertiesNode();
        properties.loadGameModes("lockpick", "cluster1", "Test/Cluster/");
        newGameLogic.setGameMode((GameModeIF) new GameMode(properties.getInGameModes()));
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void setGameModeTest() {
        assertEquals("Lockpick", Core.getGameMode().getName());
        assertEquals("classic", Core.getGameMode().getObjectiveType());
    }
    @Test
    public void getControllerTest() {
        assertEquals("lockpick", newGameLogic.getController());
    }
}
