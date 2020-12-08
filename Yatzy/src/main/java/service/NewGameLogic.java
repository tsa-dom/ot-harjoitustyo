/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import core.Core;
import service.domain.GameModeIF;
import service.domain.NewGameLogicIF;

public class NewGameLogic implements NewGameLogicIF {
    @Override
    public void setGameMode(GameModeIF gameMode) {
        Core.setGameMode(gameMode);
    }
    @Override
    public String getController() {
        return Core.getGameMode().getController();
    }
}
