/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class SetUpManager {
    
    public static String currentUser;
    public static long lastReroll;
    
    public void executeSetUp() {
        createUsers("database");
        createScoreTable("database");
        createObjectives("database");
    }
    private boolean createUsers(String givenDatabase) {
        String sql = "CREATE TABLE users (username TEXT UNIQUE,password TEXT);";
        return YatzyUi.databaseManager.executeStatement(sql, givenDatabase);
    }
    private boolean createScoreTable(String givenDatabase) {
        String sql = "CREATE TABLE score (username TEXT,score INTEGER,gamemode TEXT,maxScore INTEGER);";
        return YatzyUi.databaseManager.executeStatement(sql, givenDatabase);
    }
    private boolean createObjectives(String givenDatabase) {
        List<String> objectiveStatements = searchStatements("objectives");
        return YatzyUi.databaseManager.executeStatements(objectiveStatements, givenDatabase);
    }
    private List<String> searchStatements(String givenPath) {
        InputStream inputStream = SetUpManager.class.getResourceAsStream(givenPath + ".sql");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String statement = null;
        List<String> statementList = new ArrayList<>();
        try {
            while ((statement = reader.readLine()) != null) {
                statementList.add(statement);
            }
        } catch (IOException ex) {
            return null;
        }
        return statementList;
    }
}
