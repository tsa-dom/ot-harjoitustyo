/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import manager.GameManager;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class EndGameController implements Initializable {
    @FXML private Label score, gameMode;
    @FXML
    public void newGame() throws IOException {
        YatzyUi.setRoot("newgame");
    }
    @FXML
    public void statistics() throws IOException {
        YatzyUi.setRoot("scoreboard");
    }
    @FXML
    public void backToMenu() throws IOException {
        YatzyUi.setRoot("menu");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(GameManager.currentGameMode.getScore());
        System.out.println(GameManager.currentGameMode.getName());
        score.setText("Your score: " + GameManager.currentGameMode.getScore());
        gameMode.setText(GameManager.currentGameMode.getName());
        GameManager.currentGameMode = null;
    }
}
