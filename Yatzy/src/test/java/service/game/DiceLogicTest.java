/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import service.node.PropertiesNode;

/**
 *
 * @author Tapio Salonen
 */
public class DiceLogicTest {
    
    public DiceLogicTest() {
    }
    private PropertiesNode properties;
    private DiceLogic diceLogic;
    
    @Before
    public void setUp() {
        diceLogic = new DiceLogic();
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
    public void getSelectStatusTest() {
        String[] status = diceLogic.getSelectStatus("Select");
        assertEquals("Unselect", status[0]);
        assertEquals("Selected", status[1]);
        status = diceLogic.getSelectStatus("asdjlfakjd");
        assertEquals("Select", status[0]);
        assertEquals("", status[1]);
        properties.loadGameModes("dicelock", "cluster1", "Test/Cluster/");
        Core.setGameMode(new GameMode(properties.getInGameModes()));
        status = diceLogic.getSelectStatus("Select");
        assertEquals("---", status[0]);
        assertEquals("Locked", status[1]);
    }
    
}
