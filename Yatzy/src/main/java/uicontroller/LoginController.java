/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import generator.LoginGenerator;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button signup;
    @FXML
    private Button quit;
    @FXML
    public void logIn(){
        LoginGenerator loginGenerator = new LoginGenerator();
        if (loginGenerator.login(username.getText(), password.getText())){
            try {
                YatzyUi.setRoot("menu");
            } catch (IOException ex) {
                username.clear();
                password.clear();
            }
        }else{
            username.clear();
            password.clear();
        }
    }
    @FXML
    public void signUp() throws IOException{
        YatzyUi.setRoot("signup");
    }
    @FXML
    public void quit(){
        System.exit(0);
    }
}
