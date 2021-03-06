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

public class MenuController implements Initializable {
    @FXML private Label userLabel;
    @FXML
    private void newGame() throws IOException {
        YatzyUi.setRoot("newgame");
    }
    @FXML
    private void objective() throws IOException {
        YatzyUi.setRoot("objective");
    }
    @FXML
    private void statistics() throws IOException {
        YatzyUi.setRoot("scoreboard");
    }
    @FXML
    private void logOut() throws IOException {
        Core.setUser(null);
        YatzyUi.setRoot("login");
    }
    @FXML
    private void quit() {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userLabel.setText("Current user:\n-> " + Core.getUser());
    }
}
