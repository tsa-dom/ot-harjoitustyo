/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generator;

import java.util.List;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class LoginGenerator {
    public boolean logIn(String givenUsername,String givenPassword){
        String sql = "SELECT password FROM users WHERE username='"+givenUsername+"';";
        List<String> columnList = YatzyUi.databaseManager.selectFrom(sql, "database","password");
        try{
            String foundPassword = columnList.get(0);
            if(givenPassword.equals(foundPassword)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
}
