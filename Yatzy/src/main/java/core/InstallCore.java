/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



/** Includes all private methods needed to installing.
 */
public class InstallCore {
    protected void executeInstall(String folder) {
        programFilesFolder(folder);
        clusterFolder(folder);
        createClusters(folder);
        createUsers(folder, "data");
        createScoreTable(folder, "data");
        createObjectives(folder, "data");
    }
    private boolean programFilesFolder(String folder) {
        if (Files.exists(Paths.get(Core.getPath() + folder))) {
            return false;
        }
        File file = new File(Core.getPath() + folder);
        return file.mkdir();
    }
    private boolean clusterFolder(String folder) {
        if (Files.exists(Paths.get(Core.getPath() + folder + "Cluster/"))) {
            return false;
        }
        File file = new File(Core.getPath() + folder + "Cluster");
        return file.mkdir();
    }
    private boolean createClusters(String folder) {
        for (int i = 1; i <= 5; i++) {
            String clusterName = "cluster" + String.valueOf(i);
            clusterProperties(folder, clusterName);
        }
        return true;
    }
    private boolean clusterProperties(String folder, String clusterName) {
        try {
            if (Files.exists(Paths.get(Core.getPath() + folder + "Cluster/" + clusterName + ".properties"))) {
                return false;
            }
            File file = new File(Core.getPath() + folder + "Cluster/" + clusterName + ".properties");
            writeProperties(file, "default_cluster");
            return file.createNewFile();
        } catch (IOException ex) {
            return false;
        }
    }
    private boolean writeProperties(File givenFile, String propertiesName) {
        try {
            FileWriter writer = new FileWriter(givenFile);
            InputStream inputStream = Core.class.getResourceAsStream(propertiesName + ".properties"); 
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
    private boolean createUsers(String folder, String database) {
        String sql = "CREATE TABLE users (username TEXT UNIQUE,password TEXT);";
        return Core.sqlAsker().executeStatement(sql, database, folder);
    }
    private boolean createScoreTable(String folder, String database) {
        String sql = "CREATE TABLE scoreboard (username TEXT,score INTEGER,gamemode TEXT,maxScore INTEGER);";
        return Core.sqlAsker().executeStatement(sql, database, folder);
    }
    private boolean createObjectives(String folder, String database) {
        List<String> objectiveStatements = Core.sqlLoader().searchStatements("objectives");
        return Core.sqlAsker().executeStatements(objectiveStatements, database, folder);
    }
}
