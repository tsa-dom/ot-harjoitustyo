/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import manager.GameManager;
import ui.YatzyUi;
import uicontroller.GameController;

/**
 *
 * @author Tapio Salonen
 */
public class Game extends GameController {
    public ObservableList<Objective> objectives;
    public ObservableList<Objective> objectiveNames;
    public Calculator classicCalc;
    public Random random;
    public int reRollCount;
    public boolean openLocks;
    
    public void setRandom() {
        this.random = new Random();
    }
    
    public void setObjectives() {
        this.classicCalc = new Calculator();
        this.reRollCount = GameManager.currentGameMode.reRollCount;
        this.objectives = FXCollections.observableArrayList();
        this.objectiveNames = FXCollections.observableArrayList();
        String sql = "SELECT * FROM objectives WHERE gamemode='" + GameManager.currentGameMode.getObjectiveType() + "';";
        List<String> names = YatzyUi.databaseManager.selectFrom(sql, "database", "name");
        List<String> requirements = YatzyUi.databaseManager.selectFrom(sql, "database", "requirements");
        for (int i = 0; i < names.size(); i++) {
            Objective object = new Objective(names.get(i), requirements.get(i));
            this.objectives.add(object);
            if (requirements.get(i).charAt(0) != 'b') {
                this.objectiveNames.add(object);
            }
        }
    }
    public void setReady(int givenId, int givenPoints) {
        this.objectives.get(givenId).setPoints(String.valueOf(givenPoints));
    }
    public String[] getStatus(String givenText) {
        String[] status = new String[2];
        if (GameManager.currentGameMode.diceLockMode && openLocks == false) {
            status[0] = "---";
            status[1] = "Locked";
        } else if (givenText.equals("Select")) {
            status[0] = "Unselect";
            status[1] = "Selected";
        } else {
            status[0] = "Select";
            status[1] = "";
        }
        openLocks = false;
        return status;
    }
    public String getDices(String givenStatus, String givenDice) {
        if (givenStatus.equals("") || givenDice.equals("-")) {
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
        List<Integer> diceList = new ArrayList<>();
        for (int i = 0; i < dices.length; i++) {
            diceList.add(Integer.valueOf(dices[i].getText().subSequence(0, 1).charAt(0)));
        }
        Collections.sort(diceList, Collections.reverseOrder());
        return this.classicCalc.getPoints(givenObjective, diceList);
    }
    public int getScore() {
        int score = 0;
        for (Objective objective:this.objectives) {
            if (objective.getPoints().equals("---") == false) {
                score += Integer.valueOf(objective.getPoints());
            }
        }
        return score;
    }
    public int numberOfReRolls(int oldCount) {
        reRollCount = GameManager.currentGameMode.getReRollCount();
        if (GameManager.currentGameMode.getStoreStatus()) {
            return GameManager.currentGameMode.getReRollCount() + oldCount;
        }
        return GameManager.currentGameMode.getReRollCount();
    }
    public void executeIfEnd() throws IOException{
        if (this.objectiveNames.isEmpty()) {
            YatzyUi.setRoot("login");
        }
    }
}