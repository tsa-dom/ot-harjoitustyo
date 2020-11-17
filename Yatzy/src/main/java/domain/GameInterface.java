/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Properties;

/**
 *
 * @author Tapio Salonen
 */
public interface GameInterface {

    Properties loadProperties(String givenPath);
    
    Properties loadFromPath(String givenPath);
    
    boolean loadGameModes(Properties properties);
    
    boolean loadGameMode(Properties gameProperties);
}
