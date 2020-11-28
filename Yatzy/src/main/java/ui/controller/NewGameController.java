/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import service.NewGameLogic;
import service.game.GameMode;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class NewGameController implements Initializable {
    @FXML private ComboBox gameModes;
    @FXML private Label warning;
    private NewGameLogic newGameLogic;
    @FXML
    private void backToMenu() throws IOException {
        YatzyUi.setRoot("menu");
    }
    @FXML
    private void startGame() throws IOException {
        try {
            if (gameModes.getSelectionModel().isEmpty()) {
                warning.setText("Choose gamemode");
            } else {
                newGameLogic.setGameMode((GameMode) gameModes.getSelectionModel().getSelectedItem());
                YatzyUi.setRoot(newGameLogic.getController());
            }
        } catch (Exception ex) {
            warning.setText("Failed to load this gamemode.\nCheck your cluster files.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newGameLogic = new NewGameLogic();
        ItemNode.clearGameModes();
        ItemNode.setGameModes("Programfiles/");
        gameModes.setItems(ItemNode.gameModes);
    }
}
