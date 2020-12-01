/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import java.util.List;
import core.Core;

/**
 *
 * @author Tapio Salonen
 */
public class ObjectiveLogic {
    private final Calculator calculator;
    
    public ObjectiveLogic() {
        calculator = new Calculator();
    }
 
    public void addPoints(int points) {
        Core.getGameMode().setScore(Core.getGameMode().getScore() + points);
    }
    public int getScore() {
        return Core.getGameMode().getScore();
    }

    public int getPoints(Objective objective, List<Integer> dices) {
        return calculator.getPoints(objective, dices);
    }
    public boolean getUpperStatus() {
        return calculator.getUpperStatus();
    }
}
