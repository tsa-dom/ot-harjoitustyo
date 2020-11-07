/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import game.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
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
    @FXML public Label first;
    @FXML public Label second;
    @FXML public Label third;
    @FXML public Label fourth;
    @FXML public Label fifth;
    @FXML public Label reRollWarning;
    @FXML private Label diceSum;
    @FXML private Label player;
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
        if(System.currentTimeMillis()-SetUpManager.lastReroll>3000){
            Random rand = new Random();
            this.first.setText(String.valueOf(rand.nextInt(6)+1));
            this.second.setText(String.valueOf(rand.nextInt(6)+1));
            this.third.setText(String.valueOf(rand.nextInt(6)+1));
            this.fourth.setText(String.valueOf(rand.nextInt(6)+1));
            this.fifth.setText(String.valueOf(rand.nextInt(6)+1));
            this.diceSum.setText("Dice sum: "+(Integer.valueOf(first.getText())
                +Integer.valueOf(second.getText())
                +Integer.valueOf(third.getText())
                +Integer.valueOf(fourth.getText())
                +Integer.valueOf(fifth.getText())));
            SetUpManager.lastReroll=System.currentTimeMillis();
        }else{
            long waitingTime = 3-(System.currentTimeMillis()-SetUpManager.lastReroll)/1000;
            reRollWarning.setText("You have to wait "+waitingTime+" seconds");
        }
    }
    @FXML
    public void makeChoice(){
        try{
            Objective objective = (Objective) this.objectivesLeft.getSelectionModel().getSelectedItem();
            this.classic.setReady(objective.getId(), 5);
            this.objectivesLeft.getItems().remove(this.objectivesLeft
                .getSelectionModel().getSelectedItem());
            this.objectivesTable.refresh();
            this.objectivesLeft.getSelectionModel().clearSelection();
        } catch (Exception ex) {
            System.out.println("Nothing choosen!");
        }
        
    }
    @FXML
    public void giveUp(){
        
    }
    @FXML
    public void selectFirst(){
        
    }
    @FXML
    public void selectSecond(){
        
    }
    @FXML
    public void selectThird(){
        
    }
    @FXML
    public void selectFourth(){
        
    }
    @FXML
    public void selectFifth(){
        
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
    }
}
