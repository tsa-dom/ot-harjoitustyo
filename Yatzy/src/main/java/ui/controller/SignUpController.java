/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.LoginLogic;
import service.node.SQLNode;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class SignUpController implements Initializable {
    @FXML private Label infoLabel;
    @FXML private TextField username;
    @FXML private PasswordField password;
    private LoginLogic loginLogic;
    private SQLNode sql;
    @FXML
    private void backToLogin() {
        try {
            YatzyUi.setRoot("login");
        } catch (IOException ex) {
            
        }
    }
    @FXML
    private void signUp() {
        try {
            if (loginLogic.correctInput(username.getText(), password.getText()).equals("")) {
                if (sql.createUser(username.getText(), password.getText(), "Programfiles/")) {
                    loginLogic.setUser(username.getText());
                    YatzyUi.setRoot("menu");
                }
                infoLabel.setText("This username already exists");
            } else {
                infoLabel.setText(loginLogic.correctInput(username.getText(), password.getText()));
            }
        } catch (Exception ex) {
            infoLabel.setText("Failed to sign up");
        }
        username.clear();
        password.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loginLogic = new LoginLogic();
        sql = new SQLNode();
    }
}
