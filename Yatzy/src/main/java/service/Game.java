/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import game.Calculator;
import game.Objective;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.Label;
import manager.GameManager;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class Game {
    public ObservableList<Objective> objectives;
    public ObservableList<Objective> objectiveNames;
    public int reRollCount;
    public boolean openLocks;
    private Calculator classicCalc;
    private Random random;
    
    public void setRandom() {
        this.random = new Random();
    }
    
    public void setObjectives() {
        this.classicCalc = new Calculator();
        this.reRollCount = GameManager.currentGameMode.getReRollCount();
        this.objectives = FXCollections.observableArrayList();
        this.objectiveNames = FXCollections.observableArrayList();
        String sql = "SELECT * FROM objectives WHERE gamemode='" + GameManager.currentGameMode.getObjectiveType() + "';";
        List<String> names = Core.sqlAsker().selectFrom(sql, "data", "name");
        List<String> requirements = Core.sqlAsker().selectFrom(sql, "data", "requirements");
        Objective.nextId = 0;
        for (int i = 0; i < names.size(); i++) {
            Objective object = new Objective(names.get(i), requirements.get(i));
            this.objectives.add(object);
            if (requirements.get(i).charAt(0) != 'b') {
                this.objectiveNames.add(object);
            }
        }
        Objective.nextId = 0;
    }
    public void setReady(int givenId, int givenPoints) {
        this.objectives.get(givenId).setPoints(String.valueOf(givenPoints));
    }
    public String[] getStatus(String givenText) {
        String[] status = new String[2];
        if (GameManager.currentGameMode.getLockStatus() && openLocks == false) {
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
            try {
                int min = GameManager.currentGameMode.getMinDiceNum();
                int max = GameManager.currentGameMode.getMaxDiceNum();
                return String.valueOf(this.random.nextInt(max - min + 1) + min);
            } catch (Exception ex) {
                
            }
        }
        return givenDice;
    }
    public void endSession() {
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
    public void updateScore() {
        int score = 0;
        for (Objective objective:this.objectives) {
            if (objective.getPoints().equals("---") == false) {
                score += Integer.valueOf(objective.getPoints());
            }
        }
        GameManager.currentGameMode.setScore(score);
    }
    public int numberOfReRolls(int oldCount) {
        reRollCount = GameManager.currentGameMode.getReRollCount();
        if (GameManager.currentGameMode.getStoreStatus()) {
            return GameManager.currentGameMode.getReRollCount() + oldCount;
        }
        return GameManager.currentGameMode.getReRollCount();
    }
    public boolean bonusRequirement() {
        boolean current = false;
        for (Objective objective:this.objectives) {
            if (objective.getRequirement().charAt(0) == 121 && objective.getPoints().equals("---")) {
                current = true;
            }
        }
        return current;
    }
}