/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;

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

    public int newReRollCount() {
        if (Core.getGameMode().getStoreStatus()) {
            reRollCount = Core.getGameMode().getReRollCount() + reRollCount;
        } else {
            reRollCount = Core.getGameMode().getReRollCount();
        }
        return reRollCount;
    }
    
    public int getReRollStatus() {
        if (reRollCount <= 0) {
            return 0;
        } else if (System.currentTimeMillis() - lastReroll > reRollTime) {
            return 1;
        } else {
            return 2;
        }
    }
    public int getReRollCount() {
        return reRollCount;
    }
    public long getWaitingTime() {
        return reRollTime / 1000 - (System.currentTimeMillis() - lastReroll) / 1000;
    }
    public void upDateReRollCount() {
        lastReroll = System.currentTimeMillis();
        reRollCount--;
    }
}
