/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

import java.util.List;
import service.game.Objective;

/**
 *
 * @author Tapio Salonen
 */
public interface CalculatorIF {
    
    int getPoints(ObjectiveIF objective, List<Integer> dices);
    
    int customOrNot(String requirement, int points, boolean ready);
    
    int calculate(String requirement, int points, List<Integer> dices);
    
    int times(String requirement, List<Integer> dices);
    
    int upperSection(String requirement, List<Integer> dices);
    
    int random(String requirement, List<Integer> dices);
    
    int straight(String requirement, List<Integer> dices);
    
    void upperCountCheck(int points);
    
    boolean getUpperStatus();

}
