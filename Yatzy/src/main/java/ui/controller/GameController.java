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
import service.game.Objective;
import service.game.Statistic;
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

/**
 *
 * @author Tapio Salonen
 */
public class GameController implements Initializable {
    @FXML private Label dice1, dice2, dice3, dice4, dice5;
    @FXML private Label status1, status2, status3, status4, status5;
    @FXML private Button select1, select2, select3, select4, select5;
    @FXML private Label reRollWarning, diceSum, score, player, reRollsLeft, gameInfo;
    @FXML private TableView<Statistic> topTable;
    @FXML private TableColumn<Statistic, String> topPlayer, topScore;
    @FXML private ComboBox objectivesLeft;
    @FXML private TableView<Objective> objectivesTable;
    @FXML private TableColumn<Objective, String> name, points;
    private Label[] dices;
    private Label[] status;
    private Button[] select;
    private DiceLogic diceLogic;
    private ObjectiveLogic objectiveLogic;
    private SceneLogic sceneLogic;
    
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
            int sum = 0;
            for (int i = 0; i < dices.length; i++) {
                if (status[i].getText().equals("") || dices[i].getText().equals("-")) {
                    dices[i].setText(diceLogic.newDiceValue());
                }
                sum += Integer.valueOf(dices[i].getText());
            }
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
            Objective objective = (Objective) objectivesLeft.getSelectionModel().getSelectedItem();
            int points = objectiveLogic.getPoints(objective, createDiceList());
            ItemNode.objectives.get(objective.getId()).setPoints(String.valueOf(points));
            objectivesLeft.getItems().remove(objectivesLeft.getSelectionModel().getSelectedItem());
            objectivesTable.refresh();
            objectiveLogic.addPoints(points);
            objectivesLeft.getSelectionModel().clearSelection();
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
        }
    }
    @FXML
    private void giveUp(ActionEvent event) throws IOException {
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
        diceLogic = new DiceLogic();
        objectiveLogic = new ObjectiveLogic();
        sceneLogic = new SceneLogic();
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        points.setCellValueFactory(new PropertyValueFactory<>("points"));
        topPlayer.setCellValueFactory(new PropertyValueFactory<>("player"));
        topScore.setCellValueFactory(new PropertyValueFactory<>("score"));
        ItemNode.setTopStatistics();
        topTable.setItems(ItemNode.statistics);
        dices = new Label[]{dice1, dice2, dice3, dice4, dice5};
        status = new Label[]{status1, status2, status3, status4, status5};
        select = new Button[]{select1, select2, select3, select4, select5};
        ItemNode.setObjectives();
        objectivesTable.setItems(ItemNode.objectives);
        objectivesLeft.setItems(ItemNode.objectiveNames);
        player.setText("Player: " + Core.getUser());
        gameInfo.setText("Gamemode: " + Core.getGameMode().getName());
        reRollsLeft.setText("Rerolls left: " + sceneLogic.getReRollCount());
    }
    
    public void setClear(int id) {
        dices[id].setText("-");
        try {
            select[id].setText("Select");
            status[id].setText("");
        } catch (Exception ex) {
        }
    }
    public List<Integer> createDiceList() {
        List<Integer> diceList = new ArrayList<>();
        for (Label dice : dices) {
            diceList.add(Integer.valueOf(dice.getText()));
        }
        Collections.sort(diceList, Collections.reverseOrder());
        return diceList;
    }
}