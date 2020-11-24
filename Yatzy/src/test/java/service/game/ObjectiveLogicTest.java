/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.testfx.framework.junit.ApplicationTest;

/**
 *
 * @author Tapio Salonen
 */
public class ObjectiveLogicTest extends ApplicationTest{
    private ObjectiveLogic objectiveLogic;
    private Core core;
    private TableColumn<Objective, String> name;
    private TableColumn<Objective, String> points;
    private TableView<Objective> objectivesTable;
    private ComboBox objectivesLeft;
    private Label[] dices;
    public ObjectiveLogicTest() {
    }
    
    @Before
    public void setUp() {
        core = new Core();
        core.install("Test/");
        objectiveLogic = new ObjectiveLogic();
        Core.loadGameModes("Test/");
        Core.setGameMode(Core.getGameModes().get(0));
        objectiveLogic.setObjectives("Test/");
        name = new TableColumn<>();
        points = new TableColumn<>();
        objectivesTable = new TableView<>();
        objectivesLeft = new ComboBox();
        objectiveLogic.objectivesToTable(name, points, objectivesTable, objectivesLeft);
    }
    
    @After
    public void tearDown() {
        objectiveLogic.clearObjectives();
    }

    @Test
    public void updateScoreTest() {
        objectiveLogic.updateScore();
        objectivesLeft.getSelectionModel().select(4);
        dices = new Label[]{new Label("13"), new Label("5"), new Label("313"), new Label("5"), new Label("23"), new Label("50")};
        objectiveLogic.updateObjectives(objectivesLeft, objectivesTable, dices);
        assertEquals(0, Core.getGameMode().getScore());
        objectiveLogic.updateScore();
        assertEquals(10, Core.getGameMode().getScore());
        objectivesLeft.getSelectionModel().select(12);
        objectiveLogic.updateObjectives(objectivesLeft, objectivesTable, dices);
        objectiveLogic.updateScore();
        assertEquals(414, Core.getGameMode().getScore());
    }
    @Test
    public void nameIsEmptyTest() {
        assertFalse(objectiveLogic.namesIsEmpty());
        objectiveLogic.clearObjectives();
        assertTrue(objectiveLogic.namesIsEmpty());
    }
}
