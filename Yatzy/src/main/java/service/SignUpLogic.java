/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import core.Core;
import javafx.scene.control.*;

/**
 *
 * @author Tapio Salonen
 */
public class SignUpLogic {
    
    public boolean correctInput(TextField username, PasswordField password, Label infoLabel) {
        if (username.getText().length() > 20) {
            infoLabel.setText("Username is too long");
        } else if (username.getText().length() < 5) {
            infoLabel.setText("Username is too short");
        } else if (password.getText().length() > 20) {
            infoLabel.setText("Password is too long");
        } else if (password.getText().length() < 5) {
            infoLabel.setText("Password is too short");
        } else {
            return true;
        }
        return false;
    }
    
    public boolean createUser(String givenUsername, String givenPassword) {
        String sql = "INSERT INTO users (username,password) VALUES ('" + givenUsername + "','" + givenPassword + "');";
        return Core.sqlAsker().executeStatement(sql, "data");
    }
    
    public boolean setUser(TextField username, PasswordField password, Label infoLabel) {
        if (createUser(username.getText(), password.getText())) {
            Core.setUser(username.getText());
            return true;
        }
        infoLabel.setText("This username already exists");
        return false;
    }
}
