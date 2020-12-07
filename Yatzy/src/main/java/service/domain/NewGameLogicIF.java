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
public interface NewGameLogicIF {
    
    void setGameMode(GameModeIF gameMode);
    
    String getController();
    
}
