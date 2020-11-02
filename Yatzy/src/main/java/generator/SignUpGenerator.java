/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class SignUpGenerator {
    public boolean signUp(String givenUsername,String givenPassword){
        String sql = "INSERT INTO users (username,password) VALUES ('"+givenUsername+"','"+givenPassword+"');";
        if (YatzyUi.databaseManager.executeStatement(sql, "database")){
            return true;
        }
        return false;
    }
}
