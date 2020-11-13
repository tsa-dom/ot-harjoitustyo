/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import game.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import manager.GameManager;
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
    @FXML private TableView topTable;
    @FXML private ComboBox objectivesLeft;
    @FXML private TableView<Objective> objectivesTable;
    @FXML public TableColumn<Objective, String> name, points;
    private Game game;
    private Label[] dices, status;
    private Button[] select;
    
    @FXML
    public void backToMenu() throws IOException {
        game.endSession();
        YatzyUi.setRoot("menu");
    }
    @FXML
    public void reRoll() throws IOException {
        reRollWarning.setText("");
        if (game.reRollCount <= 0) {
            reRollWarning.setText("0 rerolls left");
        } else if (System.currentTimeMillis() - GameManager.lastReroll > GameManager.currentGameMode.getReRollTime()) {
            executeReRoll();
        } else {
            long maxWaitingTime = GameManager.currentGameMode.getReRollTime() / 1000;
            long waitingTime = maxWaitingTime - (System.currentTimeMillis() - GameManager.lastReroll) / 1000;
            reRollWarning.setText("You have to wait " + waitingTime + " seconds");
        }
    }
    @FXML
    public void makeChoice() {
        try {
            updateObjectives();
            score.setText("Your score: " + game.getScore());
            game.executeIfEnd();
            clearDices();
            game.reRollCount = game.numberOfReRolls(game.reRollCount);
            reRollsLeft.setText("Rerolls left: " + game.reRollCount);
            diceSum.setText("Dice sum: 0");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    @FXML
    public void giveUp() throws IOException {
        game.endSession();
        YatzyUi.setRoot("classic");
    }
    @FXML
    public void select(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();
        int id = Integer.valueOf(pressedButton.getId().split("select")[1]) - 1;
        String[] sessionStatus = game.getStatus(pressedButton.getText());
        select[id].setText(sessionStatus[0]);
        status[id].setText(sessionStatus[1]);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dices = new Label[]{dice1, dice2, dice3, dice4, dice5};
        status = new Label[]{status1, status2, status3, status4, status5};
        select = new Button[]{select1, select2, select3, select4, select5};
        game = new Game();
        game.setRandom();
        setGameReady();
    }
    public void executeReRoll() {
        int sum = 0;
        for (int i = 0; i < dices.length; i++) {
            dices[i].setText(game.getDices(status[i].getText(), dices[i].getText()));
            sum += Integer.valueOf(dices[i].getText());
        }
        diceSum.setText("Dice sum: " + sum);
        GameManager.lastReroll = System.currentTimeMillis();
        game.reRollCount--;
        reRollsLeft.setText("Rerolls left: " + game.reRollCount);
    }
    public void setGameReady() {
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        points.setCellFactory(TextFieldTableCell.forTableColumn());
        points.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPoints()));
        game.setObjectives();
        objectivesTable.setItems(game.objectives);
        objectivesLeft.setItems(game.objectiveNames);
        player.setText("Player: " + GameManager.currentUser);
        gameInfo.setText("Gamemode: " + GameManager.currentGameMode.getName());
        reRollsLeft.setText("Rerolls left: " + game.reRollCount);
    }
    public void clearDices() {
        for (int i = 0; i < dices.length; i++) {
            dices[i].setText("-");
            if (status[i].getText().equals("Selected") || status[i].getText().equals("Locked")) {
                game.openLocks = true;
                select[i].fire();
            }
        }
    }
    public void updateObjectives() {
        Objective objective = (Objective) objectivesLeft.getSelectionModel().getSelectedItem();
        game.setReady(objective.getId(), game.getPoints(objective, dices));
        objectivesLeft.getItems().remove(objectivesLeft.getSelectionModel().getSelectedItem());
        objectivesTable.refresh();
        objectivesLeft.getSelectionModel().clearSelection();
    }
}