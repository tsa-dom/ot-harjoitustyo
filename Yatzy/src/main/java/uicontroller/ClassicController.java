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
import manager.SetUpManager;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class ClassicController implements Initializable {
    @FXML private Label dice1, dice2, dice3, dice4, dice5;
    @FXML private Label status1, status2, status3, status4, status5;
    @FXML private Button select1, select2, select3, select4, select5;
    Label[] dices;
    Label[] status;
    Button[] select;
    @FXML private Label reRollWarning;
    @FXML private Label diceSum;
    @FXML private Label score;
    @FXML private Label player;
    @FXML private Label reRollsLeft;
    @FXML private TableView topTable;
    @FXML private ComboBox objectivesLeft;
    @FXML private TableView<Objective> objectivesTable;
    @FXML public TableColumn<Objective, String> name;
    @FXML public TableColumn<Objective, String> points;
    private Classic classic;
    
    @FXML
    public void backToMenu() throws IOException {
        this.classic.endSession();
        YatzyUi.setRoot("menu");
    }
    @FXML
    public void reRoll() throws IOException {
        reRollWarning.setText("");
        if (classic.reRollCount == 3) {
            reRollWarning.setText("0 rerolls left");
        } else if (System.currentTimeMillis() - SetUpManager.lastReroll > 2000) {
            int sum = 0;
            for (int i = 0; i < dices.length; i++) {
                dices[i].setText(classic.getDices(status[i].getText(), dices[i].getText()));
                sum += Integer.valueOf(dices[i].getText());
            }
            diceSum.setText("Dice sum: " + sum);
            SetUpManager.lastReroll = System.currentTimeMillis();
            classic.reRollCount++;
            reRollsLeft.setText("Rerolls left: " + (3 - classic.reRollCount));
        } else {
            long waitingTime = 2 - (System.currentTimeMillis() - SetUpManager.lastReroll) / 1000;
            reRollWarning.setText("You have to wait " + waitingTime + " seconds");
        }
    }
    @FXML
    public void makeChoice() {
        try {
            Objective objective = (Objective) objectivesLeft.getSelectionModel().getSelectedItem();
            classic.setReady(objective.getId(), classic.getPoints(objective, dices));
            objectivesLeft.getItems().remove(objectivesLeft.getSelectionModel().getSelectedItem());
            objectivesTable.refresh();
            objectivesLeft.getSelectionModel().clearSelection();
            score.setText("Your score: " + classic.getScore());
            for (int i = 0; i < dices.length; i++) {
                dices[i].setText("-");
                if (status[i].getText().equals("Selected")) {
                    select[i].fire();
                }
            }
            classic.reRollCount = 0;
            reRollsLeft.setText("Rerolls left: " + (3 - classic.reRollCount));
            diceSum.setText("Dice sum: 0");
        } catch (Exception ex) {
        }
    }
    @FXML
    public void giveUp() throws IOException {
        classic.endSession();
        YatzyUi.setRoot("menu");
    }
    @FXML
    public void select(ActionEvent event) {
        Button pressedButton = (Button) event.getSource();
        int id = Integer.valueOf(pressedButton.getId().split("select")[1]) - 1;
        String[] sessionStatus = classic.getStatus(pressedButton.getText());
        select[id].setText(sessionStatus[0]);
        status[id].setText(sessionStatus[1]);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dices = new Label[]{dice1, dice2, dice3, dice4, dice5};
        status = new Label[]{status1, status2, status3, status4, status5};
        select = new Button[]{select1, select2, select3, select4, select5};
        classic = new Classic();
        player.setText("Player: " + YatzyUi.setUpManager.currentUser);
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        points.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        points.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPoints()));
        classic.setObjectives();
        objectivesTable.setItems(classic.objectives);
        objectivesLeft.setItems(classic.objectiveNames);
        classic.setRandom();
    }
}
