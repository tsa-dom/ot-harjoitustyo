/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.game;

import java.util.List;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import core.Core;

/**
 *
 * @author Tapio Salonen
 */
public class ObjectiveLogic {
    private ObservableList<Objective> objectives;
    private ObservableList<Objective> objectiveNames;
    private final DiceLogic diceLogic;
    private final CalculatorCandidate calculator;
    
    public ObjectiveLogic() {
        diceLogic = new DiceLogic();
        calculator = new CalculatorCandidate();
    }
    
    public void updateObjectives(ComboBox objectivesLeft, TableView<Objective> objectivesTable, Label[] dices) {
        Objective objective = (Objective) objectivesLeft.getSelectionModel().getSelectedItem();
        objectives.get(objective.getId()).setPoints(String.valueOf(calculator.getPoints(objective, diceLogic.createDiceList(dices))));
        objectivesLeft.getItems().remove(objectivesLeft.getSelectionModel().getSelectedItem());
        objectivesTable.refresh();
        objectivesLeft.getSelectionModel().clearSelection();
    }
    
    public void setObjectives(String folder) {
        objectives = FXCollections.observableArrayList();
        objectiveNames = FXCollections.observableArrayList();
        String sql = "SELECT * FROM objectives WHERE gamemode='" + Core.getGameMode().getObjectiveType() + "';";
        List<String> names = Core.sqlAsker().selectFrom(sql, "data", "name", folder);
        List<String> requirements = Core.sqlAsker().selectFrom(sql, "data", "requirements", folder);
        Objective.nextId = 0;
        for (int i = 0; i < names.size(); i++) {
            Objective object = new Objective(names.get(i), requirements.get(i));
            objectives.add(object);
            if (requirements.get(i).charAt(0) != 'b') {
                objectiveNames.add(object);
            }
        }
    }
    
    public void objectivesToTable(TableColumn<Objective, String> name, TableColumn<Objective, String> points, TableView<Objective> objectivesTable, ComboBox objectivesLeft) {
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        name.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        points.setCellFactory(TextFieldTableCell.forTableColumn());
        points.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPoints()));
        objectivesTable.setItems(objectives);
        objectivesLeft.setItems(objectiveNames);
    }
    
    public void updateScore() {
        int score = 0;
        for (Objective objective:objectives) {
            if (objective.getPoints().equals("---") == false) {
                score += Integer.valueOf(objective.getPoints());
            }
        }
        Core.getGameMode().setScore(score);
    }
    
    public void clearObjectives() {
        objectiveNames.clear();
        objectives.clear();
    }
    
    public boolean namesIsEmpty() {
        return objectiveNames.isEmpty();
    }
}
