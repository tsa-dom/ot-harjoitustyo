/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import core.Core;
import service.domain.SceneLogicIF;

/**
 *
 * @author Tapio Salonen
 */
public class SceneLogic implements SceneLogicIF {
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

    @Override
    public int newReRollCount() {
        if (Core.getGameMode().getStoreStatus()) {
            reRollCount = Core.getGameMode().getReRollCount() + reRollCount;
        } else {
            reRollCount = Core.getGameMode().getReRollCount();
        }
        return reRollCount;
    }
    
    @Override
    public int getReRollStatus() {
        if (reRollCount <= 0) {
            return 0;
        } else if (System.currentTimeMillis() - lastReroll > reRollTime) {
            return 1;
        } else {
            return 2;
        }
    }
    @Override
    public int getReRollCount() {
        return reRollCount;
    }
    @Override
    public long getWaitingTime() {
        return reRollTime / 1000 - (System.currentTimeMillis() - lastReroll) / 1000;
    }
    @Override
    public void upDateReRollCount() {
        lastReroll = System.currentTimeMillis();
        reRollCount--;
    }
}
