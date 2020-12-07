/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.domain;

import java.util.List;

/**
 *
 * @author Tapio Salonen
 */
public interface SQLNodeIF {
    
    void loadObj(String folder);
    
    void loadStat(String folder);
    
    void loadTopStat(String folder);
    
    List<String> getLogin(String username, String folder);
    
    boolean createUser(String username, String password, String folder);
    
    List<String> getObjNames();
    
    List<String> getObjRequirements();
    
    List<String> getStatPlayers();
    
    List<String> getStatScores();
    
    List<String> getStatGameModes();
    
    List<String> getStatMaxScores();
    
}
