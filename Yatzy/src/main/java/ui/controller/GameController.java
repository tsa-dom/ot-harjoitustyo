/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import core.Core;
import service.game.DiceLogic;
import service.game.ObjectiveLogic;
import service.game.SceneLogic;
import service.domain.DiceLogicIF;
import service.domain.ObjectiveLogicIF;
import service.domain.SceneLogicIF;
import service.domain.ObjectiveIF;
import service.domain.StatisticIF;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.YatzyUi;

public class GameController implements Initializable {
    // All @FXML annotated variables below are read from .fxml files when initializing game.
    @FXML private Label dice1, dice2, dice3, dice4, dice5, dice6;
    @FXML private Label status1, status2, status3, status4, status5, status6;
    @FXML private Button select1, select2, select3, select4, select5, select6;
    @FXML private Label reRollWarning, diceSum, score, player, reRollsLeft, gameInfo;
    @FXML private TableView<StatisticIF> topTable;
    @FXML private TableColumn<StatisticIF, String> topPlayer, topScore;
    @FXML private ComboBox objectivesLeft;
    @FXML private TableView<ObjectiveIF> objectivesTable;
    @FXML private TableColumn<ObjectiveIF, String> name, points;
    
    private Label[] dices;
    private Label[] status;
    private Button[] select;
    private DiceLogicIF diceLogic;
    private ObjectiveLogicIF objectiveLogic;
    private SceneLogicIF sceneLogic;
    
    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        ItemNode.clearObjectives();
        YatzyUi.setRoot("menu");
    }
    @FXML 
    private void reRoll(ActionEvent event) throws IOException {
        if (sceneLogic.getReRollStatus() == 0) {
            reRollWarning.setText("0 rerolls left");
        } else if (sceneLogic.getReRollStatus() == 1) {
            int sum = getSum();
            sceneLogic.upDateReRollCount();
            reRollWarning.setText("");
            diceSum.setText("Dice sum: " + sum);
            reRollsLeft.setText("Rerolls left: " + sceneLogic.getReRollCount());
        } else {
            reRollWarning.setText("You have to wait " + sceneLogic.getWaitingTime() + " seconds");
        }
    }
    @FXML
    private void makeChoice(ActionEvent event) throws IOException {
        try {
            addPoints();
            if (ItemNode.namesIsEmpty()) {
                YatzyUi.setRoot("endgame");
            }
            for (int i = 0; i < dices.length; i++) {
                setClear(i);
            }
            score.setText("Your score: " + objectiveLogic.getScore());
            reRollsLeft.setText("Rerolls left: " + sceneLogic.newReRollCount());
            diceSum.setText("Dice sum: 0");
        } catch (Exception ex) {
            // Nothing to happen here! If making a choice fails for any reason, then nothing should happen.
        }
    }
    @FXML
    private void select(ActionEvent event) {
        Button button = (Button) event.getSource();
        int id = Integer.valueOf(button.getId().split("select")[1]) - 1;
        String[] eventStatus = diceLogic.getSelectStatus(button.getText());
        select[id].setText(eventStatus[0]);
        status[id].setText(eventStatus[1]);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeLogic();
        loadFactories();
        ItemNode.setTopStatistics();
        topTable.setItems(ItemNode.statistics);
        dices = new Label[]{dice1, dice2, dice3, dice4, dice5, dice6};
        status = new Label[]{status1, status2, status3, status4, status5, status6};
        select = new Button[]{select1, select2, select3, select4, select5, select6};
        ItemNode.setObjectives();
        objectivesTable.setItems(ItemNode.objectives);
        objectivesLeft.setItems(ItemNode.objectiveNames);
        player.setText("Player: " + Core.getUser());
        gameInfo.setText("Gamemode: " + Core.getGameMode().getName());
        reRollsLeft.setText("Rerolls left: " + sceneLogic.getReRollCount());
    }
    
    private void setClear(int id) {
        try {
            dices[id].setText("-");
            try {
                select[id].setText("Select");
                status[id].setText("");
            } catch (Exception ex) {
            }
        } catch (Exception ex) {
            // Nothing to happen here.
        }
    }
    private List<Integer> createDiceList() {
        List<Integer> diceList = new ArrayList<>();
        try {
            for (Label dice : dices) {
                diceList.add(Integer.valueOf(dice.getText()));
            }
        } catch (Exception ex) {
        }
        Collections.sort(diceList, Collections.reverseOrder());
        return diceList;
    }
    private void loadFactories() {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        topPlayer.setCellValueFactory(new PropertyValueFactory<>("player"));
        topScore.setCellValueFactory(new PropertyValueFactory<>("score"));
    }
    private void initializeLogic() {
        diceLogic = new DiceLogic();
        objectiveLogic = new ObjectiveLogic();
        sceneLogic = new SceneLogic();
    }
    private void addPoints() throws Exception {
        ObjectiveIF objective = (ObjectiveIF) objectivesLeft.getSelectionModel().getSelectedItem();
        int points = objectiveLogic.getPoints(objective, createDiceList());
        if (objectiveLogic.getUpperStatus() && ItemNode.upperId > -1) {
            ItemNode.objectives.get(ItemNode.upperId).setPoints(String.valueOf(ItemNode.getBonusPoinst()));
            ItemNode.upperId = -1;
        }
        ItemNode.objectives.get(objective.getId()).setPoints(String.valueOf(points));
        objectivesLeft.getItems().remove(objectivesLeft.getSelectionModel().getSelectedItem());
        objectivesTable.refresh();
        objectiveLogic.addPoints(points);
        objectivesLeft.getSelectionModel().clearSelection();
    }
    private int getSum() {
        int sum = 0;
        try {
            for (int i = 0; i < dices.length; i++) {
                if (status[i].getText().equals("") || dices[i].getText().equals("-")) {
                    dices[i].setText(diceLogic.newDiceValue());
                }
                sum += Integer.valueOf(dices[i].getText());
            }
        } catch (Exception ex) {
            // Nothing to happen here!
        }
        return sum;
    }
}