/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
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
            YatzyUi.currentUser = null;
            YatzyUi.setRoot("login");
        } catch (Exception ex) {
            
        }
    }
    @FXML
    public void quit(){
        System.exit(0);
    }
    public void initialize(){
        userLabel.setText("Current user:\n-> "+YatzyUi.currentUser);
    }
}
