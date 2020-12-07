/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import service.LoginLogic;
import service.node.SQLNode;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.domain.LoginLogicIF;
import service.domain.SQLNodeIF;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class LoginController implements Initializable {
    @FXML private Label infoLabel;
    @FXML private TextField username;
    @FXML private PasswordField password;
    private LoginLogicIF loginLogic;
    private SQLNodeIF sql;
    @FXML
    private void logIn() throws IOException {
        try {
            String foundPassword = sql.getLogin(username.getText(), "Programfiles/").get(0);
            if (password.getText().equals(foundPassword)) {
                loginLogic.setUser(sql.getLogin(username.getText(), "Programfiles/").get(0));
                YatzyUi.setRoot("menu");
            }
        } catch (Exception e) {
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
        sql = new SQLNode();
    }
}
