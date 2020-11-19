/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import core.Core;
import game.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import service.*;
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
    @FXML private TableColumn<Objective, String> name, points;
    private Label[] dices, status;
    private Button[] select;
    private DiceLogic diceLogic;
    private ObjectiveLogic objectiveLogic;
    private SceneLogic sceneLogic;
    
    @FXML
    private void backToMenu(ActionEvent event) throws IOException {
        objectiveLogic.clearObjectives();
        YatzyUi.setRoot("menu");
    }
    @FXML
    private void reRoll(ActionEvent event) throws IOException {
        sceneLogic.beginReRollEvent(dices, status, reRollWarning, diceSum, reRollsLeft);
    }
    @FXML
    private void makeChoice(ActionEvent event) throws IOException {
        try {
            objectiveLogic.updateObjectives(objectivesLeft, objectivesTable, dices);
            objectiveLogic.updateScore();
            if (objectiveLogic.namesIsEmpty()) {
                YatzyUi.setRoot("endgame");
            }
            diceLogic.clearDices(dices, status, select);
            sceneLogic.updateSituation(score, reRollsLeft, diceSum);
        } catch (Exception ex) {
            
        }
    }
    @FXML
    private void giveUp(ActionEvent event) throws IOException {
    }
    @FXML
    private void select(ActionEvent event) {
        sceneLogic.setDiceStatus((Button) event.getSource(), status, select);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        diceLogic = new DiceLogic();
        objectiveLogic = new ObjectiveLogic();
        sceneLogic = new SceneLogic();
        dices = new Label[]{dice1, dice2, dice3, dice4, dice5};
        status = new Label[]{status1, status2, status3, status4, status5};
        select = new Button[]{select1, select2, select3, select4, select5};
        objectiveLogic.setObjectives();
        objectiveLogic.objectivesToTable(name, points, objectivesTable, objectivesLeft);
        sceneLogic.setStartSituation(player, gameInfo, reRollsLeft);
    }
}