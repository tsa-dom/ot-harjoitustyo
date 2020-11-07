/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.YatzyUi;
import uicontroller.ClassicController;

/**
 *
 * @author Tapio Salonen
 */
public class Classic extends ClassicController{
    public ObservableList<Objective> objectives;
    public ObservableList<Objective> objectiveNames;
    
    public void setObjectives(){
        this.objectives = FXCollections.observableArrayList();
        this.objectiveNames = FXCollections.observableArrayList();
        String sql = "SELECT * FROM objectives WHERE gamemode='classic';";
        List<String> names = YatzyUi.databaseManager.selectFrom(sql, "database", "name");
        List<String> requirements = YatzyUi.databaseManager.selectFrom(sql, "database", "requirements");
        for(int i=0;i<names.size();i++){
            Objective object = new Objective(names.get(i),requirements.get(i));
            this.objectives.add(object);
            if (names.get(i).equals("Bonus")==false){
               this.objectiveNames.add(object);
            }
        }
    }
    public void setReady(int givenId,int givenPoints){
        this.objectives.get(givenId).setPoints(String.valueOf(givenPoints));
    }
    public void endSession(){
        Objective.nextId = 0;
        this.objectiveNames.clear();
        this.objectives.clear();
    }
    public int getPoints(int givenId, String givenDices){
        String requirement = this.objectives.get(givenId).getRequirement();
        
        
        
        return 1;
    }
}