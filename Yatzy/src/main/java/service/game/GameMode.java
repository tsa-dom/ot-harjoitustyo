/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import java.util.Properties;
import service.domain.GameModeIF;

public class GameMode implements GameModeIF {
    private int score;
    private final String gameName;
    private final Long reRollWarningTime;
    private final int reRollCount;
    private final boolean storeReRolls;
    private final int minDiceNum;
    private final int maxDiceNum;
    private final String objectiveType;
    private final String controller;
    private final boolean diceLockMode;
    private final int bonusRequirement;
    
    /** Creates new gamemode.
     * @param properties Given properties
     */
    public GameMode(Properties properties) {
        this.score = 0;
        this.gameName = properties.getProperty("game_name");
        this.reRollWarningTime = Long.valueOf(properties.getProperty("reroll_warning_time"));
        this.reRollCount = Integer.valueOf(properties.getProperty("reroll_count"));
        this.storeReRolls = Boolean.valueOf(properties.getProperty("store_rerolls"));
        this.minDiceNum = Integer.valueOf(properties.getProperty("minimum_dice_number"));
        this.maxDiceNum = Integer.valueOf(properties.getProperty("maximum_dice_number"));
        this.objectiveType = properties.getProperty("objective_type");
        this.controller = properties.getProperty("controller");
        this.diceLockMode = Boolean.valueOf(properties.getProperty("dice_lock_mode"));
        this.bonusRequirement = Integer.valueOf(properties.getProperty("bonus_required_points"));
    }
    @Override
    public void setScore(int score) {
        this.score = score;
    }
    @Override
    public int getScore() {
        return this.score;
    }
    @Override
    public String getName() {
        return this.gameName;
    }
    @Override
    public long getReRollTime() {
        return this.reRollWarningTime;
    }
    @Override
    public int getReRollCount() {
        return this.reRollCount;
    }
    @Override
    public boolean getStoreStatus() {
        return this.storeReRolls;
    }
    @Override
    public int getMinDiceNum() {
        return this.minDiceNum;
    }
    @Override
    public int getMaxDiceNum() {
        return this.maxDiceNum;
    }
    @Override
    public String getObjectiveType() {
        return this.objectiveType;
    }
    @Override
    public String getController() {
        return this.controller;
    }
    @Override
    public boolean getLockStatus() {
        return this.diceLockMode;
    }
    @Override
    public int getBonusRequirement() {
        return this.bonusRequirement;
    }
    @Override
    public String toString() {
        return this.gameName;
    }
}