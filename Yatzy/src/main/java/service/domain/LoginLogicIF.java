/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

/**
 *
 * @author Tapio Salonen
 */
public interface LoginLogicIF {
    
    void setUser(String user);
    
    String correctInput(String username, String password);

}
