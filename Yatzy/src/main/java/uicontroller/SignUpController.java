/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import generator.SignUpGenerator;
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
public class SignUpController {
    @FXML
    private Label infoLabel;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    public void backToLogin() {
        try {
            YatzyUi.setRoot("login");
        } catch (IOException ex) {
            
        }
    }
    @FXML
    public void signUp() {
        SignUpGenerator signUpGenerator = new SignUpGenerator();
        if (username.getText().length() > 20) {
            infoLabel.setText("Username is too long");
        } else if (username.getText().length() < 5) {
            infoLabel.setText("Username is too short");
        } else if (password.getText().length() > 20) {
            infoLabel.setText("Password is too long");
        } else if (password.getText().length() < 5) {
            infoLabel.setText("Password is too short");
        } else {
            if (signUpGenerator.signUp(username.getText(), password.getText())) {
                try {
                    SetUpManager.currentUser = username.getText();
                    YatzyUi.setRoot("menu");
                } catch (IOException ex) {
                    infoLabel.setText("Failed to sign up");
                }
            }
            infoLabel.setText("This username already exists");
        }
        username.clear();
        password.clear();
    }
}
