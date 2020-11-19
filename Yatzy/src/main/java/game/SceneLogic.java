/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import core.Core;
import javafx.scene.control.*;

/**
 *
 * @author Tapio Salonen
 */
public class SceneLogic {
    private final DiceLogic diceLogic;
    private final long reRollTime;
    private long lastReroll;
    private int reRollCount;
    
    public SceneLogic() {
        diceLogic = new DiceLogic();
        reRollCount = Core.getGameMode().getReRollCount();
        lastReroll = System.currentTimeMillis();
        reRollTime = Core.getGameMode().getReRollTime();
    }
    
    public void setStartSituation(Label player, Label gameInfo, Label reRollsLeft) {
        player.setText("Player: " + Core.getUser());
        gameInfo.setText("Gamemode: " + Core.getGameMode().getName());
        reRollsLeft.setText("Rerolls left: " + reRollCount);
    }
    
    public void updateSituation(Label score, Label reRollsLeft, Label diceSum) {
        score.setText("Your score: " + Core.getGameMode().getScore());
        if (Core.getGameMode().getStoreStatus()) {
            reRollCount = Core.getGameMode().getReRollCount() + reRollCount;
        } else {
            reRollCount = Core.getGameMode().getReRollCount();
        }
        reRollsLeft.setText("Rerolls left: " + reRollCount);
        diceSum.setText("Dice sum: 0");
    }
    
    public void setDiceStatus(Button button, Label[] status, Button[] select) {
        int id = Integer.valueOf(button.getId().split("select")[1]) - 1;
        if (Core.getGameMode().getLockStatus()) {
            select[id].setText("---");
            status[id].setText("Locked");
        } else if (button.getText().equals("Select")) {
            select[id].setText("Unselect");
            status[id].setText("Selected");
        } else {
            select[id].setText("Select");
            status[id].setText("");
        }
    }
    
    public void beginReRollEvent(Label[] dices, Label[] status, Label reRollWarning, Label diceSum, Label reRollsLeft) {
        if (reRollCount <= 0) {
            reRollWarning.setText("0 rerolls left");
        } else if (System.currentTimeMillis() - lastReroll > reRollTime) {
            diceLogic.newDiceValues(dices, status);
            lastReroll = System.currentTimeMillis();
            reRollCount--;
            reRollWarning.setText("");
            diceSum.setText("Dice sum: " + diceLogic.getDicesSum(dices));
            reRollsLeft.setText("Rerolls left: " + reRollCount);
        } else {
            long waitingTime = reRollTime / 1000 - (System.currentTimeMillis() - lastReroll) / 1000;
            reRollWarning.setText("You have to wait " + waitingTime + " seconds");
        }
    }
}
