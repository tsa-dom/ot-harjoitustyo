/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import service.game.DiceLogic;
import core.Core;
import java.util.ArrayList;
import java.util.List;
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
public class DiceLogicTest extends ApplicationTest{
    DiceLogic diceLogic;
    Label[] dices;
    Label[] status;
    Button[] select;
    
    public DiceLogicTest() {
    }
    
    @Before
    public void setUp() {
        diceLogic = new DiceLogic();
        dices = new Label[] {new Label("4"), new Label("7"), new Label("-"), new Label("5"), new Label("-"), new Label("1")};
        status = new Label[] {new Label("Selected"), new Label(""), new Label(""), new Label("Locked"), new Label("Selected"), new Label("Locked")};
        select = new Button[] {new Button("Unselect"), new Button("Select"), new Button("Select"), new Button("---"), new Button("Unselect")};
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void getDicesSum() {
        dices = new Label[]{new Label("4"), new Label("7"), new Label("313"), new Label("5"), new Label("313"), new Label("1")};
        assertEquals(643, diceLogic.getDicesSum(dices));
    }
    @Test
    public void newDiceValuesTest() {
        Core.loadGameModes("Test/");
        Core.setGameMode(Core.getGameModes().get(2));
        diceLogic.newDiceValues(dices, status);
        assertEquals("4",dices[0].getText());
        assertEquals("5",dices[3].getText());
        boolean test = true;
        if (dices[2].getText().equals("-") || dices[1].getText().equals("7") || dices[4].getText().equals("-")) {
            test = false;
        }
        assertTrue(test);
    }
    @Test
    public void clearDicesTest() {
        diceLogic.clearDices(dices, status, select);
        for(int i = 0; i<dices.length; i++) {
            assertEquals("-", dices[i].getText());
            if (i<5) {
                assertEquals("", status[i].getText());
                assertEquals("Select", select[i].getText());
            }
        }
        assertEquals("Locked", status[5].getText());
    }
    @Test
    public void createDiceListTest() {
        dices = new Label[]{new Label("4"), new Label("7"), new Label("3"), new Label("5"), new Label("3"), new Label("1")};
        List<Integer> diceList = diceLogic.createDiceList(dices);
        List<Integer> list = new ArrayList<>();
        list.add(55); list.add(53); list.add(52); list.add(51); list.add(51); list.add(49);
        assertEquals(list, diceList);
    }

    
}
