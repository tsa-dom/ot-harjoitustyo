/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setup;

import java.util.ArrayList;
import java.util.List;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class ObjectivesSetUp {
    
    public boolean launchSetUp(){
        List<String> objectiveList = new ArrayList<>();
        objectiveList = new ArrayList<>();
        objectiveList.add("Test1/50/xxxxx");
        objectiveList.add("Test2/50/xxxxy");
        objectiveList.add("Test3/50/xxxyy");
        objectiveList.add("Test4/50/xxyyy");
        objectiveList.add("Test5/50/xyyyy");
        objectiveList.add("Test6/50/yyyyy");
        objectiveList.add("Test7/50/yyxxa");
        return execute(objectiveList);
    }
    boolean execute(List<String> objectiveList){
        List<String> statements = new ArrayList<>();
        for (int i=0;i<objectiveList.size();i++){
            String objective = objectiveList.get(i);
            String[] element = objective.split("/");
            String sql = "INSERT INTO objectives (name,maxScore,requirements) VALUES ('"+element[0]+"','"+element[1]+"','"+element[2]+"');";
            statements.add(sql);
        }
        return YatzyUi.databaseManager.executeStatements(statements, "database");
    }
}
