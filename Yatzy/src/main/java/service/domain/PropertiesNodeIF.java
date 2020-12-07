/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

import java.util.Properties;

/**
 *
 * @author Tapio Salonen
 */
public interface PropertiesNodeIF {
    
    void loadGameModes(String inGameName, String outGameName, String path);
    
    Properties getInGameModes();
    
    Properties getOutGameModes();
    
    Properties getGameModes(String id);
    
}
