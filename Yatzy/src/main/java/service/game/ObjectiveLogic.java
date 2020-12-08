/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import java.util.List;
import core.Core;
import service.domain.CalculatorIF;
import service.domain.ObjectiveIF;
import service.domain.ObjectiveLogicIF;

public class ObjectiveLogic implements ObjectiveLogicIF {
    private final CalculatorIF calculator;
    
    /** Creates access to objective logic.
     */
    public ObjectiveLogic() {
        calculator = new Calculator();
    }
 
    @Override
    public void addPoints(int points) {
        Core.getGameMode().setScore(Core.getGameMode().getScore() + points);
    }
    @Override
    public int getScore() {
        return Core.getGameMode().getScore();
    }

    @Override
    public int getPoints(ObjectiveIF objective, List<Integer> dices) {
        return calculator.getPoints(objective, dices);
    }
    @Override
    public boolean getUpperStatus() {
        return calculator.getUpperStatus();
    }
}
