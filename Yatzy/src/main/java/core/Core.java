/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.dao.PropertiesLoader;
import core.dao.SQLLoader;
import core.dao.DatabaseAccess;
import service.game.GameMode;
import javafx.collections.ObservableList;

/**
 *
 * @author Tapio Salonen
 */
public class Core {
    private static GameCore gameCore;
    private static DaoCore daoCore;
    private static InstallCore installCore;
    
    public Core() {
        gameCore = new GameCore();
        daoCore = new DaoCore();
        installCore = new InstallCore();
    }
    
    public void install(String folder) {
        installCore.executeInstall(folder);
    }
    public static DatabaseAccess sqlAsker() {
        return daoCore.dbAccess;
    }
    public static SQLLoader sqlLoader() {
        return daoCore.sqlLoader;
    }
    public static String getUser() {
        return gameCore.user;
    }
    public static void setUser(String user) {
        gameCore.user = user;
    }
    public static GameMode getGameMode() {
        return gameCore.gameMode;
    }
    public static void setGameMode(GameMode gameMode) {
        gameCore.gameMode = gameMode;
    }
    public static void clearGameModes() {
        gameCore.gameModes.clear();
    }
    public static void loadGameModes(String folder) {
        gameCore.loadGameModes(folder);
    }
    public static ObservableList<GameMode> getGameModes() {
        return gameCore.gameModes;
    }
    public static PropertiesLoader properties() {
        return daoCore.propertiesLoader;
    }
    public static String getPath() {
        return System.getProperty("user.dir") + "/";
    }
}
