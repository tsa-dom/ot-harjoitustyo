/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import ui.YatzyUi;
import uicontroller.ClassicController;

/**
 *
 * @author Tapio Salonen
 */
public class Classic extends ClassicController {
    public ObservableList<Objective> objectives;
    public ObservableList<Objective> objectiveNames;
    public ClassicCalculator classicCalc;
    public Random random;
    public int reRollCount;
    
    public void setRandom() {
        this.random = new Random();
    }
    
    public void setObjectives() {
        this.classicCalc = new ClassicCalculator();
        this.reRollCount = 0;
        this.objectives = FXCollections.observableArrayList();
        this.objectiveNames = FXCollections.observableArrayList();
        String sql = "SELECT * FROM objectives WHERE gamemode='classic';";
        List<String> names = YatzyUi.databaseManager.selectFrom(sql, "database", "name");
        List<String> requirements = YatzyUi.databaseManager.selectFrom(sql, "database", "requirements");
        for (int i = 0; i < names.size(); i++) {
            Objective object = new Objective(names.get(i), requirements.get(i));
            this.objectives.add(object);
            if (names.get(i).equals("Bonus") == false) {
                this.objectiveNames.add(object);
            }
        }
    }
    public void setReady(int givenId, int givenPoints) {
        this.objectives.get(givenId).setPoints(String.valueOf(givenPoints));
    }
    public String[] getStatus(String givenText) {
        String[] status = new String[2];
        if (givenText.equals("Select")) {
            status[0] = "Unselect";
            status[1] = "Selected";
        } else {
            status[0] = "Select";
            status[1] = "";
        }
        return status;
    }
    public String getDices(String givenStatus, String givenDice) {
        if (givenStatus.equals("")) {
            return String.valueOf(this.random.nextInt(6) + 1);
        }
        if (givenDice.equals("-")) {
            return String.valueOf(this.random.nextInt(6) + 1);
        }
        return givenDice;
    }
    public void endSession() {
        Objective.nextId = 0;
        this.objectiveNames.clear();
        this.objectives.clear();
    }
    public int getPoints(Objective givenObjective, Label[] dices) {
        return this.classicCalc.getPoints(givenObjective, dices);
    }
    public int getScore() {
        int sum = 0;
        for (Objective objective:this.objectives) {
            if (objective.getPoints().equals("---") == false) {
                sum += Integer.valueOf(objective.getPoints());
            }
        }
        return sum;
    }
}