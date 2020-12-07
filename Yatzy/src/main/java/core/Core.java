/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import core.domain.DataIF;
import core.domain.PropertiesIF;
import core.domain.SQLIF;
import service.domain.GameModeIF;

/**
 *
 * @author Tapio Salonen
 * 
 * Softaware static core methods are here
 */
public class Core {
    private static GameCore gameCore;
    private static DaoCore daoCore;
    private static InstallCore installCore;
    
    /**
     * Initializing subcores and maincore
     */
    public Core() {
        gameCore = new GameCore();
        daoCore = new DaoCore();
        installCore = new InstallCore();
    }
    /**
    * Executes install to given folder.
    *
    * @param folder given install folder.
    */
    public void install(String folder) {
        installCore.executeInstall(folder);
    }
    /**
     * Asking for database access.
     * 
     * @return Asked access to database
     */
    public static DataIF sqlAsker() {
        return daoCore.dbAccess;
    }
    /**
     * Asking SQL-loader
     * 
     * @return Asked access to SQL-loader
     */
    public static SQLIF sqlLoader() {
        return daoCore.sqlLoader;
    }
    /**
     * Asking current user
     * 
     * @return Current game user
     */
    public static String getUser() {
        return gameCore.user;
    }
    /**
     * Setting up new user to the game
     * 
     * @param user Given user
     */
    public static void setUser(String user) {
        gameCore.user = user;
    }
    /**
     * Asking infomation about current gamemode
     * 
     * @return Current gamemode
     */
    public static GameModeIF getGameMode() {
        return gameCore.gameMode;
    }
    /**
     * Setting up new gamemode to the game
     * 
     * @param gameMode Given gamemode
     */
    public static void setGameMode(GameModeIF gameMode) {
        gameCore.gameMode = gameMode;
    }
    /**
     * Asking properties loader
     * 
     * @return Asked properties loader
     */
    public static PropertiesIF properties() {
        return daoCore.propertiesLoader;
    }
    /**
     * Asking current software path
     * 
     * @return Software path
     */
    public static String getPath() {
        return System.getProperty("user.dir") + "/";
    }
    /**
     * Increases the number of completed objectives in the upper section by one
     */
    public static void addUpperCount() {
        gameCore.upperCount++;
    }
    /**
     * Resets upper section completed count
     */
    public static void clearUpperCount() {
        gameCore.upperCount = 0;
    }
    /**
     * Asking for current upper section complete count
     * 
     * @return Upper section complete count
     */
    public static int getUpperCount() {
        return gameCore.upperCount;
    }
    /**
     * Setting number of points given when player achieves bonus
     * @param points 
     */
    public static void setBonusPoints(int points) {
        gameCore.bonusPoints = points;
    }
    /**
     * Asking for given points when bonus requirement is completed
     * 
     * @return Gamemode bonus points
     */
    public static int getBonusPoints() {
        return gameCore.bonusPoints;
    }
}
