/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

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
    public void newGame() {
        try {
            YatzyUi.setRoot("newgame");
        } catch (Exception ex) {
            
        }
    }
    @FXML
    public void loadGame() {
        try {
            YatzyUi.setRoot("loadgame");
        } catch (Exception ex) {
            
        }
    }
    @FXML
    public void statistics() {
        try {
            YatzyUi.setRoot("scoreboard");
        } catch (Exception ex) {
            
        }
    }
    @FXML
    public void logOut() {
        try {
            SetUpManager.currentUser = null;
            YatzyUi.setRoot("login");
        } catch (Exception ex) {
            
        }
    }
    @FXML
    public void quit(){
        System.exit(0);
    }
    public void initialize(){
        userLabel.setText("Current user:\n-> "+SetUpManager.currentUser);
    }
}
