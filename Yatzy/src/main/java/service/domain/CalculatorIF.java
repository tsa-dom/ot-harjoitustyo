/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

import java.util.List;

/**
 *
 * @author Tapio Salonen
 * 
 * Game calculator methods are stored here
 */
public interface CalculatorIF {
    /**
     * Requesting points depending choosed objective given and all dices
     * 
     * @param objective Given objective interface
     * @param dices List of dices
     * @return Points that the player could receive
     */
    int getPoints(ObjectiveIF objective, List<Integer> dices);
    /**
     * Tests if player could reseive custom points
     * @param requirement Requirement to receive points
     * @param points Current points
     * @param ready Param that include information about "Times" cases status. True, if player can reseive custom points.
     * @return Points that the player could receive
     */
    int customOrNot(String requirement, int points, boolean ready);
    /**
     * Checks the type of given requirement
     * @param requirement Requirement to receive points
     * @param points Current points
     * @param dices List of dices
     * @return Points that the player could receive
     */
    int calculate(String requirement, int points, List<Integer> dices);
    /**
     * Calculates points depending 
     * @param requirement
     * @param dices
     * @return 
     */
    int times(String requirement, List<Integer> dices);
    
    int upperSection(String requirement, List<Integer> dices);
    
    int random(String requirement, List<Integer> dices);
    
    int straight(String requirement, List<Integer> dices);
    
    void upperCountCheck(int points);
    
    boolean getUpperStatus();

}
