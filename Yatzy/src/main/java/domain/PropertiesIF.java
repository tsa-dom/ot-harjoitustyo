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
public interface PropertiesIF {
    
    Properties loadProperties(String givenPath);
    
    Properties loadFromPath(String givenPath);
    
}
