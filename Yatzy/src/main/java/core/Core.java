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
    public static PropertiesLoader properties() {
        return daoCore.propertiesLoader;
    }
    public static String getPath() {
        return System.getProperty("user.dir") + "/";
    }
    public static void addUpperCount() {
        gameCore.upperCount++;
    }
    public static void clearUpperCount() {
        gameCore.upperCount = 0;
    }
    public static int getUpperCount() {
        return gameCore.upperCount;
    }
    public static void setBonusPoints(int points) {
        gameCore.bonusPoints = points;
    }
    public static int getBonusPoints() {
        return gameCore.bonusPoints;
    }
}
