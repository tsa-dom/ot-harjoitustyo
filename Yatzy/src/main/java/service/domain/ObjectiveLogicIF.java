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
 */
public interface ObjectiveLogicIF {
    
    void addPoints(int points);
    
    int getScore();

    int getPoints(ObjectiveIF objective, List<Integer> dices);
    
    boolean getUpperStatus();
}
