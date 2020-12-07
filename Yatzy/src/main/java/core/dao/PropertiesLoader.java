/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.dao;

import core.Core;
import core.domain.PropertiesIF;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author Tapio Salonen
 */
public class PropertiesLoader implements PropertiesIF {
    
    @Override
    public Properties loadProperties(String givenPath) {
        try {
            Properties properties = new Properties();
            properties.load(Core.class.getResourceAsStream(givenPath + ".properties"));
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public Properties loadFromPath(String givenPath) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(givenPath + ".properties"));
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
}
