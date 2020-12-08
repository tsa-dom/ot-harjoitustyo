/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

public interface NewGameLogicIF {
    /** Sets new gamemode to play.
     * @param gameMode Given gamemode
     */
    void setGameMode(GameModeIF gameMode);
    /** Gets current gamemode controller name.
     * @return Returns controller name
     */
    String getController();
    
}
