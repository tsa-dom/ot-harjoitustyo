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
    @FXML private Label first;
    @FXML private Label second;
    @FXML private Label third;
    @FXML private Label fourth;
    @FXML private Label fifth;
    @FXML private Label reRollWarning;
    @FXML private Label diceSum;
    @FXML private Label score;
    @FXML private Label player;
    @FXML private Label reRollsLeft;
    @FXML private Label statusFirst;
    @FXML private Label statusSecond;
    @FXML private Label statusThird;
    @FXML private Label statusFourth;
    @FXML private Label statusFifth;
    @FXML private Button selectFirst;
    @FXML private Button selectSecond;
    @FXML private Button selectThird;
    @FXML private Button selectFourth;
    @FXML private Button selectFifth;
    @FXML private TableView topTable;
    @FXML private ComboBox objectivesLeft;
    @FXML private TableView<Objective> objectivesTable;
    @FXML public TableColumn<Objective, String> name;
    @FXML public TableColumn<Objective, String> points;
    private Classic classic;
    
    @FXML
    public void backToMenu() throws IOException{
        this.classic.endSession();
        YatzyUi.setRoot("menu");
    }
    @FXML
    public void reRoll() throws IOException{
        reRollWarning.setText("");
        if(this.classic.reRollCount==3){
            this.reRollWarning.setText("0 rerolls left");
        }else if(System.currentTimeMillis()-SetUpManager.lastReroll>2000){
            this.first.setText(this.classic.getDices(this.statusFirst.getText(), this.first.getText()));
            this.second.setText(this.classic.getDices(this.statusSecond.getText(), this.second.getText()));
            this.third.setText(this.classic.getDices(this.statusThird.getText(), this.third.getText()));
            this.fourth.setText(this.classic.getDices(this.statusFourth.getText(), this.fourth.getText()));
            this.fifth.setText(this.classic.getDices(this.statusFifth.getText(), this.fifth.getText()));
            this.diceSum.setText("Dice sum: "+(Integer.valueOf(first.getText())
                +Integer.valueOf(second.getText())
                +Integer.valueOf(third.getText())
                +Integer.valueOf(fourth.getText())
                +Integer.valueOf(fifth.getText())));
            SetUpManager.lastReroll=System.currentTimeMillis();
            this.classic.reRollCount++;
            this.reRollsLeft.setText("Rerolls left: "+(3-this.classic.reRollCount));
        }else{
            long waitingTime = 2-(System.currentTimeMillis()-SetUpManager.lastReroll)/1000;
            this.reRollWarning.setText("You have to wait "+waitingTime+" seconds");
        }
    }
    @FXML
    public void makeChoice(){
        try{
            Objective objective = (Objective) this.objectivesLeft.getSelectionModel().getSelectedItem();
            String dices = this.second.getText()+this.third.getText()+this.first.getText()
                    +this.fourth.getText()+this.fifth.getText();
            this.classic.setReady(objective.getId(), this.classic.getPoints(objective,dices));
            this.objectivesLeft.getItems().remove(this.objectivesLeft
                .getSelectionModel().getSelectedItem());
            this.objectivesTable.refresh();
            this.objectivesLeft.getSelectionModel().clearSelection();
            this.score.setText("Your score: "+this.classic.getScore());
            this.first.setText("-");
            this.second.setText("-");
            this.third.setText("-");
            this.fourth.setText("-");
            this.fifth.setText("-");
            this.classic.reRollCount = 0;
            this.reRollsLeft.setText("Rerolls left: "+(3-this.classic.reRollCount));
            if(this.statusFirst.getText().equals("Selected")) selectFirst();
            if(this.statusSecond.getText().equals("Selected")) selectSecond();
            if(this.statusThird.getText().equals("Selected")) selectThird();
            if(this.statusFourth.getText().equals("Selected")) selectFourth();
            if(this.statusFifth.getText().equals("Selected")) selectFifth();
            this.diceSum.setText("Dice sum: 0");
        } catch (Exception ex) {
        }
    }
    @FXML
    public void giveUp() throws IOException{
        this.classic.endSession();
        YatzyUi.setRoot("menu");
    }
    @FXML
    public void selectFirst(){
        String[] status = this.classic.getStatus(this.selectFirst.getText());
        this.selectFirst.setText(status[0]);
        this.statusFirst.setText(status[1]);
    }
    @FXML
    public void selectSecond(){
        String[] status = this.classic.getStatus(this.selectSecond.getText());
        this.selectSecond.setText(status[0]);
        this.statusSecond.setText(status[1]);
    }
    @FXML
    public void selectThird(){
        String[] status = this.classic.getStatus(this.selectThird.getText());
        this.selectThird.setText(status[0]);
        this.statusThird.setText(status[1]);
    }
    @FXML
    public void selectFourth(){
        String[] status = this.classic.getStatus(this.selectFourth.getText());
        this.selectFourth.setText(status[0]);
        this.statusFourth.setText(status[1]);
    }
    @FXML
    public void selectFifth(){
        String[] status = this.classic.getStatus(this.selectFifth.getText());
        this.selectFifth.setText(status[0]);
        this.statusFifth.setText(status[1]);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.classic = new Classic();
        this.player.setText("Player: "+YatzyUi.setUpManager.currentUser);
        this.name.setCellFactory(TextFieldTableCell.forTableColumn());
        this.points.setCellFactory(TextFieldTableCell.forTableColumn());
        this.name.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getName()));
        this.points.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPoints()));
        this.classic.setObjectives();
        this.objectivesTable.setItems(this.classic.objectives);
        this.objectivesLeft.setItems(this.classic.objectiveNames);
        this.classic.setRandom();
    }
}
