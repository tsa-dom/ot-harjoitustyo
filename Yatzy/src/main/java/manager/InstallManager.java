/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class InstallManager {
    
    public void executeSetUp() {
        programFilesFolder();
        clusterFolder();
        gameProperties();
        createClusters();
        createUsers("data");
        createScoreTable("data");
        createObjectives("data");
    }
    public static String getJarPath() {
        String currentPath = System.getProperty("user.dir");
        return currentPath + "/";
    }
    private boolean programFilesFolder() {
        if (Files.exists(Paths.get(getJarPath() + "Programfiles/"))){
            return false;
        }
        File file = new File(getJarPath() + "Programfiles");
        return file.mkdir();
    }
    private boolean clusterFolder() {
        if (Files.exists(Paths.get(getJarPath() + "Programfiles/Cluster/"))){
            return false;
        }
        File file = new File(getJarPath() + "Programfiles/" + "Cluster");
        return file.mkdir();
    }
    private boolean gameProperties() {
        try {
            if (Files.exists(Paths.get(getJarPath() + "Programfiles/game.properties"))){
                return false;
            }
            File file = new File(getJarPath() + "Programfiles/" + "game.properties");
            writeProperties(file, "default_game");
            return file.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }
    private boolean createClusters() {
        for (int i = 1; i <= 5; i++) {
            String clusterName = "cluster" + String.valueOf(i);
            clusterProperties(clusterName);
        }
        return true;
    }
    private boolean clusterProperties(String clusterName) {
        try {
            if (Files.exists(Paths.get(getJarPath() + "Programfiles/Cluster/" + clusterName + ".properties"))){
                return false;
            }
            File file = new File(getJarPath() + "Programfiles/Cluster/" + clusterName + ".properties");
            writeProperties(file, "default_cluster");
            return file.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }
    private boolean writeProperties(File givenFile, String propertiesName) {
        try {
            FileWriter writer = new FileWriter(givenFile);
            InputStream inputStream = InstallManager.class.getResourceAsStream(propertiesName + ".properties"); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String rule = null;
            while ((rule = reader.readLine()) != null) {
                writer.write(rule + "\n");
            }
            writer.flush();
            writer.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
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
        InputStream inputStream = InstallManager.class.getResourceAsStream(givenPath + ".sql");
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
