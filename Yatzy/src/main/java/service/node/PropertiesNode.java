/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.node;

import core.Core;
import java.util.Properties;
import service.domain.PropertiesNodeIF;

public class PropertiesNode implements PropertiesNodeIF {
    private static Properties inGameModes;
    private static Properties outGameModes;
    
    @Override
    public void loadGameModes(String inGameName, String outGameName, String path) {
        inGameModes = Core.properties().loadProperties(inGameName);
        outGameModes = Core.properties().loadFromPath(Core.getPath() + path + outGameName);
    }
    @Override
    public Properties getInGameModes() {
        return inGameModes;
    }
    @Override
    public Properties getOutGameModes() {
        return outGameModes;
    }
    @Override
    public Properties getGameModes(String id) {
        return Core.properties().loadProperties(id);
    }
}
