/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import core.Core;
import java.util.List;
import javafx.scene.control.*;

/**
 *
 * @author Tapio Salonen
 */
public class LoginLogic {
    public boolean logIn(TextField username, PasswordField password) {
        String sql = "SELECT password FROM users WHERE username='" + username.getText() + "';";
        List<String> columnList = Core.sqlAsker().selectFrom(sql, "data", "password");
        try {
            String foundPassword = columnList.get(0);
            if (password.getText().equals(foundPassword)) {
                Core.setUser(username.getText());
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
