/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import service.Login;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import manager.GameManager;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class LoginController {
    @FXML
    private Label infoLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private void logIn() {
        Login loginGenerator = new Login();
        if (loginGenerator.logIn(username.getText(), password.getText())) {
            try {
                GameManager.currentUser = username.getText();
                YatzyUi.setRoot("menu");
            } catch (IOException ex) {
                
            }
        }
        username.clear();
        password.clear();
        infoLabel.setText("Wrong username or password!");
    }
    @FXML
    private void signUp() throws IOException {
        YatzyUi.setRoot("signup");
    }
    @FXML
    private void quit() {
        System.exit(0);
    }
}
