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
public interface SceneLogicIF {
    
    int newReRollCount();
    
    int getReRollStatus();
    
    int getReRollCount();
    
    long getWaitingTime();
    
    void upDateReRollCount();
    
}
