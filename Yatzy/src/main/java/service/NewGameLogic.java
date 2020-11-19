/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import core.Core;
import game.GameMode;
import javafx.scene.control.*;

/**
 *
 * @author Tapio Salonen
 */
public class NewGameLogic {
    public boolean setGameMode(Label warning, ComboBox gameModes) {
        if (gameModes.getSelectionModel().isEmpty()) {
            warning.setText("Choose gamemode");
        } else {
            Core.setGameMode((GameMode) gameModes.getSelectionModel().getSelectedItem());
            return true;
        }
        return false;
    }
}
