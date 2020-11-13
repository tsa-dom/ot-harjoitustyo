/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Properties;

/**
 *
 * @author Tapio Salonen
 */
public class GameMode {
    String gameName;
    Long reRollWarningTime;
    int reRollCount;
    boolean storeReRolls;
    int minDiceNum;
    int maxDiceNum;
    String diceRandomType;
    String objectiveType;
    String extraType;
    String controller;
    boolean diceLockMode;
    
    public GameMode(Properties properties) {
        this.gameName = properties.getProperty("game_name");
        this.reRollWarningTime = Long.valueOf(properties.getProperty("reroll_warning_time"));
        this.reRollCount = Integer.valueOf(properties.getProperty("reroll_count"));
        this.storeReRolls = Boolean.valueOf(properties.getProperty("store_rerolls"));
        this.minDiceNum = Integer.valueOf(properties.getProperty("minimum_dice_number"));
        this.maxDiceNum = Integer.valueOf(properties.getProperty("maximum_dice_number"));
        this.diceRandomType = properties.getProperty("dice_random_type");
        this.objectiveType = properties.getProperty("objective_type");
        this.extraType = properties.getProperty("extra_objective_type");
        this.controller = properties.getProperty("controller");
        this.diceLockMode = Boolean.valueOf(properties.getProperty("dice_lock_mode"));
    }
    public String getName() {
        return this.gameName;
    }
    public long getReRollTime() {
        return this.reRollWarningTime;
    }
    public int getReRollCount() {
        return this.reRollCount;
    }
    public boolean getStoreStatus() {
        return this.storeReRolls;
    }
    public int getMinDiceNum() {
        return this.minDiceNum;
    }
    public int getMaxDiceNum() {
        return this.maxDiceNum;
    }
    public String getRandomType() {
        return this.diceRandomType;
    }
    public String getObjectiveType() {
        return this.objectiveType;
    }
    public String getExtraType() {
        return this.extraType;
    }
    public String getController() {
        return this.controller;
    }
    public boolean getLockStatus() {
        return this.diceLockMode;
    }
    @Override
    public String toString() {
        return this.gameName;
    }
}