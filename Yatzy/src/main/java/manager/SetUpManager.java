/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import setup.ObjectivesSetUp;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class SetUpManager {
    
    public static String currentUser;
    public static long lastReroll;
    
    public void executeSetUp(){
        createUsers("database");
        createScoreTable("database");
        if (createObjectives("database")){
            ObjectivesSetUp objetivesSetUp = new ObjectivesSetUp();
            objetivesSetUp.launchSetUp();
        };
    }
    private boolean createUsers(String givenDatabase){
        String sql = "CREATE TABLE users (username TEXT UNIQUE,password TEXT);";
        return YatzyUi.databaseManager.executeStatement(sql, givenDatabase);
    }
    private boolean createScoreTable(String givenDatabase){
        String sql = "CREATE TABLE score (username TEXT,score INTEGER,gamemode TEXT,maxScore INTEGER);";
        return YatzyUi.databaseManager.executeStatement(sql, givenDatabase);
    }
    private boolean createObjectives(String givenDatabase){
        String sql = "CREATE TABLE objectives (name TEXT UNIQUE,maxScore INTEGER,requirements TEXT);";
        return YatzyUi.databaseManager.executeStatement(sql, givenDatabase);
    }
}
