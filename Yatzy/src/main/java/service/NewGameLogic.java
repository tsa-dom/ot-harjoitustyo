/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import core.Core;
import service.game.GameMode;

/**
 *
 * @author Tapio Salonen
 */
public class NewGameLogic {
    public void setGameMode(GameMode gameMode) {
        Core.setGameMode(gameMode);
    }
    public String getController() {
        return Core.getGameMode().getController();
    }
}
