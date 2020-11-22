/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import core.Core;
import service.LoginLogic;
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
public class LoginController implements Initializable{
    @FXML private Label infoLabel;
    @FXML private TextField username;
    @FXML private PasswordField password;
    private LoginLogic loginLogic;
    @FXML
    private void logIn() throws IOException {
        if (loginLogic.logIn(username, password, "Programfiles/")) {
            YatzyUi.setRoot("menu");    
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginLogic = new LoginLogic();
    }
}
