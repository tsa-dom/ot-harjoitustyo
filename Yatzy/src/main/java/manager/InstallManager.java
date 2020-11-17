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
import service.Core;

/**
 *
 * @author Tapio Salonen
 */
public class InstallManager {
    
    public void executeInstall() {
        programFilesFolder();
        clusterFolder();
        gameProperties();
        createClusters();
        createUsers("data");
        createScoreTable("data");
        createObjectives("data");
    }
    private boolean programFilesFolder() {
        if (Files.exists(Paths.get(Core.getPath()+ "Programfiles/"))){
            return false;
        }
        File file = new File(Core.getPath()+ "Programfiles");
        return file.mkdir();
    }
    private boolean clusterFolder() {
        if (Files.exists(Paths.get(Core.getPath()+ "Programfiles/Cluster/"))){
            return false;
        }
        File file = new File(Core.getPath()+ "Programfiles/" + "Cluster");
        return file.mkdir();
    }
    private boolean gameProperties() {
        try {
            if (Files.exists(Paths.get(Core.getPath()+ "Programfiles/game.properties"))){
                return false;
            }
            File file = new File(Core.getPath()+ "Programfiles/" + "game.properties");
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
            if (Files.exists(Paths.get(Core.getPath()+ "Programfiles/Cluster/" + clusterName + ".properties"))){
                return false;
            }
            File file = new File(Core.getPath()+ "Programfiles/Cluster/" + clusterName + ".properties");
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
        return Core.sqlAsker().executeStatement(sql, givenDatabase);
    }
    private boolean createScoreTable(String givenDatabase) {
        String sql = "CREATE TABLE score (username TEXT,score INTEGER,gamemode TEXT,maxScore INTEGER);";
        return Core.sqlAsker().executeStatement(sql, givenDatabase);
    }
    private boolean createObjectives(String givenDatabase) {
        List<String> objectiveStatements = searchStatements("objectives");
        return Core.sqlAsker().executeStatements(objectiveStatements, givenDatabase);
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
