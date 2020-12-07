/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import core.Core;
import service.domain.LoginLogicIF;

/**
 *
 * @author Tapio Salonen
 */
public class LoginLogic implements LoginLogicIF {
    @Override
    public void setUser(String user) {
        Core.setUser(user);
    }
    @Override
    public String correctInput(String username, String password) {
        if (username.length() > 20) {
            return "Username is too long";
        } else if (username.length() < 5) {
            return "Username is too short";
        } else if (password.length() > 20) {
            return "Password is too long";
        } else if (password.length() < 5) {
            return "Password is too short";
        }
        return "";
    }
}
