/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

/**
 *
 * @author Tapio Salonen
 */
public interface GameModeIF {
    
    void setScore(int score);
    
    int getScore();
    
    String getName();
    
    long getReRollTime();
    
    int getReRollCount();
    
    boolean getStoreStatus();
    
    int getMinDiceNum();
    
    int getMaxDiceNum();
    
    String getObjectiveType();
    
    String getExtraType();
    
    String getController();
    
    boolean getLockStatus();
    
    int getBonusRequirement();
    
}
