/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

import java.util.Properties;

public interface PropertiesNodeIF {
    /** Loads gamemodes from given path.
     * @param inGameName Gamemode name inside jar
     * @param outGameName Gamemode name outside jar
     * @param path Given path
     */
    void loadGameModes(String inGameName, String outGameName, String path);
    /** Gets loaded gamemodes as properties.
     * @return Returns loaded gamemodes
     */
    Properties getInGameModes();
    /** Gets loaded gamemodes as properties.
     * @return Returns loaded gamemodes
     */
    Properties getOutGameModes();
    /** Searches for the desired gamemode.
     * @param id Given gamemode identifier
     * @return Gamemode as properties
     */
    Properties getGameModes(String id);
    
}
