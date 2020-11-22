/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import core.Core;
import service.SignUpLogic;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class SignUpController implements Initializable{
    @FXML private Label infoLabel;
    @FXML private TextField username;
    @FXML private PasswordField password;
    private SignUpLogic signUpLogic;
    @FXML
    private void backToLogin() {
        try {
            YatzyUi.setRoot("login");
        } catch (IOException ex) {
            
        }
    }
    @FXML
    private void signUp() {
        if(signUpLogic.correctInput(username, password, infoLabel)) {
            try {
                if(signUpLogic.setUser(username, password, infoLabel, "Programfiles/")){
                    YatzyUi.setRoot("menu");
                }
            } catch (IOException ex) {
                infoLabel.setText("Failed to sign up");
            }
        }
        username.clear();
        password.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        signUpLogic = new SignUpLogic();
    }
}
