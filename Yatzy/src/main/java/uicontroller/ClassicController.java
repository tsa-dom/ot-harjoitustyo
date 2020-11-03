/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import java.io.IOException;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import manager.SetUpManager;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class ClassicController {
    @FXML
    private Label first;
    @FXML
    private Label second;
    @FXML
    private Label third;
    @FXML
    private Label fourth;
    @FXML
    private Label fifth;
    @FXML
    private Label reRollWarning;
    public void backToMenu() throws IOException{
        //Tieto onko pelitilannse tallennettu
        YatzyUi.setRoot("menu");
    }
    @FXML
    public void reRoll() throws IOException{
        reRollWarning.setText("");
        if(System.currentTimeMillis()-SetUpManager.lastReroll>3000){
            Random rand = new Random();
            first.setText(String.valueOf(rand.nextInt(6)+1));
            second.setText(String.valueOf(rand.nextInt(6)+1));
            third.setText(String.valueOf(rand.nextInt(6)+1));
            fourth.setText(String.valueOf(rand.nextInt(6)+1));
            fifth.setText(String.valueOf(rand.nextInt(6)+1));
            SetUpManager.lastReroll = System.currentTimeMillis();
        }else{
            long waitingTime = 3-(System.currentTimeMillis()-SetUpManager.lastReroll)/1000;
            reRollWarning.setText("You have to wait "+waitingTime+" seconds");
        }
    }
    @FXML
    public void initialize() {
        first.setText("-");
        second.setText("-");
        third.setText("-");
        fourth.setText("-");
        fifth.setText("-");
        SetUpManager.lastReroll = System.currentTimeMillis();
    }
}
