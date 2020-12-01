/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import service.game.GameMode;

/**
 *
 * @author Tapio Salonen
 */
public class GameCore {
    protected GameMode gameMode;
    protected String user;
    protected int upperCount;
    protected int bonusPoints;
    
    protected GameCore() {
        upperCount = 0;
    }
}
