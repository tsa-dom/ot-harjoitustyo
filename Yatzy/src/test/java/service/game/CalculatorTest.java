/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import service.node.PropertiesNode;

/**
 *
 * @author Tapio Salonen
 */
public class CalculatorTest {
    Calculator calculator;
    List<Integer> dices;
    PropertiesNode properties;
    public CalculatorTest() {
    }
    
    @Before
    public void setUp() {
        Core core = new Core();
        core.install("Test/");
        properties = new PropertiesNode();
        properties.loadGameModes("classic", "cluster1", "Test/Cluster/");
        Core.setGameMode(new GameMode(properties.getInGameModes()));
        calculator = new Calculator();
        dices = new ArrayList<>();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void StraightTest() {
        dices.addAll(Arrays.asList(313, 312, 311, 310, 309, 308, 307));
        assertEquals(2170, calculator.straight("313A307", dices));
        assertEquals(0, calculator.straight("313A306", dices));
        assertEquals(0, calculator.straight("314A307", dices));
        assertEquals(1246, calculator.straight("313A310", dices));
        assertEquals(1234, calculator.straight("310A307", dices));
        assertEquals(310, calculator.straight("310A310", dices));
    }
    @Test
    public void RandomTest() {
        dices.addAll(Arrays.asList(313, 31, 30, 11, 9, 8, 3));
        assertEquals(405, calculator.random("7", dices));
        assertEquals(385, calculator.random("4", dices));
        assertEquals(313, calculator.random("1", dices));
    }
    @Test
    public void upperSectionTest() {
        dices.addAll(Arrays.asList(6, 6, 5, 5, 5, 4, 3, 3, 3, 3, 2, 1));
        assertEquals(12, calculator.upperSection("6", dices));
        assertEquals(15, calculator.upperSection("5", dices));
        assertEquals(4, calculator.upperSection("4", dices));
        assertEquals(12, calculator.upperSection("3", dices));
        assertEquals(1, calculator.upperSection("1", dices));
    }
    @Test
    public void timesTest() {
        dices.addAll(Arrays.asList(61, 61, 52, 52, 52, 43, 34, 34, 34, 34, 25, 16, 16));
        assertEquals(122, calculator.times("2", dices));
        assertEquals(136, calculator.times("4", dices));
        assertEquals(0, calculator.times("5", dices));
        assertEquals(104, calculator.times("2", dices));
        assertEquals(32, calculator.times("2", dices));
        assertEquals(0, calculator.times("2", dices));
        assertEquals(43, calculator.times("1", dices));
    }
    @Test
    public void timesTest2() {
        dices.addAll(Arrays.asList(61, 61, 52, 52, 52, 49, 34, 34, 34, 34, 29, 16, 16));
        assertEquals(61, calculator.times("1", dices));
        assertEquals(136, calculator.times("4", dices));
        assertEquals(0, calculator.times("5", dices));
        assertEquals(52, calculator.times("1", dices));
        assertEquals(32, calculator.times("2", dices));
        assertEquals(49, calculator.times("1", dices));
        assertEquals(29, calculator.times("1", dices));
    }
    @Test
    public void calculateTest() {
        dices.addAll(Arrays.asList(61, 61, 52, 52, 52, 49, 34, 34, 34, 34, 17, 16, 16));
        assertEquals(449, calculator.calculate("x4", 313, dices));
        assertEquals(173, calculator.calculate("y52", 17, dices));
        assertEquals(175, calculator.calculate("r3", 1, dices));
        assertEquals(233, calculator.calculate("d17A16", 200, dices));
    }
    @Test
    public void customOrNotTest() {
        assertEquals(313, calculator.customOrNot("m", 313, true));
        assertEquals(-1, calculator.customOrNot("m", 313, false));
        assertEquals(313, calculator.customOrNot("c/313", 100, true));
        assertEquals(-1, calculator.customOrNot("c/313", 100, false));
        assertEquals(-1, calculator.customOrNot("j", 13, true));
    }
}
