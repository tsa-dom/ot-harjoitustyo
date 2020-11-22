/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import core.Core;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class EndGameController implements Initializable {
    @FXML private Label score, gameMode;
    @FXML
    private void newGame() throws IOException {
        YatzyUi.setRoot("newgame");
    }
    @FXML
    private void statistics() throws IOException {
        YatzyUi.setRoot("scoreboard");
    }
    @FXML
    private void backToMenu() throws IOException {
        YatzyUi.setRoot("menu");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        score.setText("Your score: " + Core.getGameMode().getScore());
        gameMode.setText(Core.getGameMode().getName());
        Core.setUser(null);
    }
}
