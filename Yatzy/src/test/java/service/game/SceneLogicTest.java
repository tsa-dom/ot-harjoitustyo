/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author Tapio Salonen
 */
public class SceneLogicTest extends ApplicationTest{
    Core core;
    SceneLogic sceneLogic;
    Label player;
    Label gameInfo;
    Label reRollsLeft;
    Label score;
    Label diceSum;
    public SceneLogicTest() {
    }
    
    @Before
    public void setUp() {
        core = new Core();
        core.install("Test/");
        Core.setUser("Tester313");
        Core.loadGameModes("Test/");
        Core.setGameMode(Core.getGameModes().get(0));
        Core.getGameMode().setScore(313);
        sceneLogic = new SceneLogic();
        player = new Label("Tester");
        gameInfo = new Label("GameInfo");
        reRollsLeft = new Label("Reroolllls");
        score = new Label("score");
        diceSum = new Label("dice");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void setStartSitutationTest() {
        sceneLogic.setStartSituation(player, gameInfo, reRollsLeft);
        assertEquals("Player: Tester313", player.getText());
        assertEquals("Gamemode: Classic", gameInfo.getText());
        assertEquals("Rerolls left: 3", reRollsLeft.getText());
    }
    @Test
    public void updateSituationTest() {
        sceneLogic.updateSituation(score, reRollsLeft, diceSum);
        assertEquals("Your score: 313", score.getText());
        assertEquals("Rerolls left: 3", reRollsLeft.getText());
        assertEquals("Dice sum: 0", diceSum.getText());
    }
    @Test
    public void updateSituationTest2() {
        Core.setGameMode(Core.getGameModes().get(4));
        Core.getGameMode().setScore(1337);
        sceneLogic = new SceneLogic();
        sceneLogic.updateSituation(score, reRollsLeft, diceSum);
        assertEquals("Your score: 1337", score.getText());
        assertEquals("Rerolls left: 6", reRollsLeft.getText());
        assertEquals("Dice sum: 0", diceSum.getText());
    }
    @Test
    public void setDiceStatusTest() {
        Label[] status = new Label[] {new Label("Selected"), new Label(""), new Label(""), new Label("Locked"), new Label("Selected"), new Label("Locked")};
        Button[] select = new Button[] {new Button("Unselect"), new Button("Select"), new Button("Select"), new Button("---"), new Button("Unselect")};
        Button button = new Button("Unselect");
        button.setId("select1");
        sceneLogic.setDiceStatus(button, status, select);
        assertEquals("Select", select[0].getText());
        button = new Button("Select");
        button.setId("select3");
        sceneLogic.setDiceStatus(button, status, select);
        assertEquals("Unselect", select[2].getText());
        assertEquals("Selected", status[2].getText());
        button = new Button("Select");
        button.setId("select2");
        sceneLogic.setDiceStatus(button, status, select);
        assertEquals("Unselect", select[1].getText());
        assertEquals("Selected", status[1].getText());
    }
    @Test
    public void setDiceStatusTest2() {
        Label[] status = new Label[] {new Label("Selected"), new Label(""), new Label(""), new Label("Locked"), new Label("Selected"), new Label("Locked")};
        Button[] select = new Button[] {new Button("Unselect"), new Button("Select"), new Button("Select"), new Button("---"), new Button("Unselect")};
        Button button = new Button("---");
        button.setId("select4");
        Core.setGameMode(Core.getGameModes().get(3));
        sceneLogic.setDiceStatus(button, status, select);
        assertEquals("Locked", status[3].getText());
        assertEquals("---", select[3].getText());
        button = new Button("Select");
        button.setId("select2");
        sceneLogic.setDiceStatus(button, status, select);
        assertEquals("Locked", status[1].getText());
        assertEquals("---", select[1].getText());
    }
}
