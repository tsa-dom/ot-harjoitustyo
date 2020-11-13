/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import game.GameMode;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import manager.GameManager;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class NewGameController implements Initializable{
    @FXML private ComboBox gameModes;
    @FXML private Label warning;
    @FXML
    public void backToMenu() throws IOException {
        YatzyUi.setRoot("menu");
    }
    @FXML
    public void startGame() throws IOException {
        try {
            GameManager.currentGameMode = (GameMode) gameModes.getSelectionModel().getSelectedItem();
            YatzyUi.setRoot(GameManager.currentGameMode.getController());
        } catch (Exception ex) {
            warning.setText("Choose gamemode");
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        YatzyUi.gameManager.gameModes.clear();
        YatzyUi.gameManager.loadGameModes(YatzyUi.gameManager.loadProperties("gamemode"));
        gameModes.setItems(YatzyUi.gameManager.gameModes);
    }
}
