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

public class PropertiesLoader implements PropertiesIF {
    
    @Override
    public Properties loadProperties(String path) {
        try {
            Properties properties = new Properties();
            properties.load(Core.class.getResourceAsStream(path + ".properties"));
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public Properties loadFromPath(String path) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(path + ".properties"));
            return properties;
        } catch (Exception ex) {
            return null;
        }
    }
}
