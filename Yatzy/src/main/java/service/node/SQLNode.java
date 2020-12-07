/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.node;

import core.Core;
import java.util.List;
import service.domain.SQLNodeIF;

/**
 *
 * @author Tapio Salonen
 */
public class SQLNode implements SQLNodeIF {
    private static List<String> objNames;
    private static List<String> objRequirements;
    private static List<String> statPlayers;
    private static List<String> statScores;
    private static List<String> statGameModes;
    private static List<String> statMaxScores;
    
    @Override
    public void loadObj(String folder) {
        String sql = "SELECT * FROM objectives WHERE gamemode='" + Core.getGameMode().getObjectiveType() + "';";
        objNames = Core.sqlAsker().selectFrom(sql, "data", "name", folder);
        objRequirements = Core.sqlAsker().selectFrom(sql, "data", "requirements", folder);
    }
    @Override
    public void loadStat(String folder) {
        String sql = "SELECT * FROM scoreboard ORDER BY score DESC;";
        statPlayers = Core.sqlAsker().selectFrom(sql, "data", "username", folder);
        statScores = Core.sqlAsker().selectFrom(sql, "data", "score", folder);
        statGameModes = Core.sqlAsker().selectFrom(sql, "data", "gamemode", folder);
        statMaxScores = Core.sqlAsker().selectFrom(sql, "data", "maxScore", folder);
    }
    @Override
    public void loadTopStat(String folder) {
        String sql = "SELECT * FROM scoreboard WHERE gamemode='" + Core.getGameMode().getName() + "' ORDER BY score DESC LIMIT 3;";
        statPlayers = Core.sqlAsker().selectFrom(sql, "data", "username", folder);
        statScores = Core.sqlAsker().selectFrom(sql, "data", "score", folder);
    }
    @Override
    public List<String> getLogin(String username, String folder) {
        String sql = "SELECT password FROM users WHERE username='" + username + "';";
        return Core.sqlAsker().selectFrom(sql, "data", "password", folder);
    }
    @Override
    public boolean createUser(String username, String password, String folder) {
        String sql = "INSERT INTO users (username,password) VALUES ('" + username + "','" + password + "');";
        return Core.sqlAsker().executeStatement(sql, "data", folder);
    }
    @Override
    public List<String> getObjNames() {
        return objNames;
    }
    @Override
    public List<String> getObjRequirements() {
        return objRequirements;
    }
    @Override
    public List<String> getStatPlayers() {
        return statPlayers;
    }
    @Override
    public List<String> getStatScores() {
        return statScores;
    }
    @Override
    public List<String> getStatGameModes() {
        return statGameModes;
    }
    @Override
    public List<String> getStatMaxScores() {
        return statMaxScores;
    }
}
