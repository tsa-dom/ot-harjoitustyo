/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class SetUpManager {
    public void executeSetUpTest(){
        System.out.println(createTable("database"));
    }
    private boolean createTable(String givenDatabase){
        String sql = "CREATE TABLE users (username TEXT UNIQUE,password TEXT);";
        return YatzyUi.databaseManager.executeStatement(sql, givenDatabase);
    }
}
