/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import manager.*;
import service.ExceptionHandler;

/**
 *
 * @author Tapio Salonen
 */
public class Core {
    public static DatabaseManager databaseManager;
    public static InstallManager installManager;
    public static GameManager gameManager;
    public static ExceptionHandler exceptionHandler;
    
    public Core() {
        databaseManager = new DatabaseManager();
        gameManager = new GameManager();
        installManager = new InstallManager();
        exceptionHandler = new ExceptionHandler();
    }
    
    public void install() {
        installManager.executeInstall();
    }
    
    public static DatabaseManager sqlAsker() {
        return databaseManager;
    }
    public static String getPath() {
        String currentPath = System.getProperty("user.dir");
        return currentPath + "/";
    }
}
