/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

public interface LoginLogicIF {
    /** Sets new user to the game.
     * @param user Given user
     */
    void setUser(String user);
    /** Tests if username and password inputs are correct.
     * @param username Given username
     * @param password Given password
     * @return Returns possible error message
     */
    String correctInput(String username, String password);

}
