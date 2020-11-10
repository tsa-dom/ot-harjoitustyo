/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.SetUpManager;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class MenuController {
    @FXML
    private Label userLabel;
    @FXML
    public void newGame() throws IOException {
        YatzyUi.setRoot("newgame");
    }
    @FXML
    public void loadGame() throws IOException {
        YatzyUi.setRoot("loadgame");
    }
    @FXML
    public void statistics() throws IOException {
        YatzyUi.setRoot("scoreboard");
    }
    @FXML
    public void logOut() throws IOException {
        SetUpManager.currentUser = null;
        YatzyUi.setRoot("login");
    }
    @FXML
    public void quit() {
        System.exit(0);
    }
    public void initialize() {
        userLabel.setText("Current user:\n-> " + SetUpManager.currentUser);
    }
}
