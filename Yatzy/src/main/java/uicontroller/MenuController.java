/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uicontroller;

import java.io.IOException;
import javafx.fxml.FXML;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class MenuController {
    @FXML
    public void newGame() throws IOException{
        YatzyUi.setRoot("newgame");
    }
    @FXML
    public void loadGame() throws IOException{
        YatzyUi.setRoot("loadgame");
    }
    @FXML
    public void statistics(){
        
    }
    @FXML
    public void logOut() throws IOException{
        YatzyUi.setRoot("login");
    }
    @FXML
    public void quit(){
        System.exit(0);
    }
}
