/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import generator.LoginGenerator;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import manager.SetUpManager;
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
    public void logIn() {
        LoginGenerator loginGenerator = new LoginGenerator();
        if (loginGenerator.logIn(username.getText(), password.getText())) {
            try {
                SetUpManager.currentUser = username.getText();
                YatzyUi.setRoot("menu");
            } catch (IOException ex) {
                
            }
        }
        username.clear();
        password.clear();
        infoLabel.setText("Wrong username or password!");
    }
    @FXML
    public void signUp() throws IOException {
        YatzyUi.setRoot("signup");
    }
    @FXML
    public void quit() {
        System.exit(0);
    }
}
