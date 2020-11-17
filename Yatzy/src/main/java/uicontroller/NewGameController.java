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
import javafx.fxml.*;
import javafx.scene.control.*;
import manager.GameManager;
import service.Core;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class NewGameController implements Initializable{
    @FXML private ComboBox gameModes;
    @FXML private Label warning;
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
                GameManager.currentGameMode = (GameMode) gameModes.getSelectionModel().getSelectedItem();
                YatzyUi.setRoot(GameManager.currentGameMode.getController());
            }
        } catch (Exception ex) {
            warning.setText("Failed to load this gamemode.\nCheck your cluster files.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Core.gameManager.gameModes.clear();
        Core.gameManager.loadGameModes(Core.gameManager.loadProperties("gamemode"));
        gameModes.setItems(Core.gameManager.gameModes);
    }
}
