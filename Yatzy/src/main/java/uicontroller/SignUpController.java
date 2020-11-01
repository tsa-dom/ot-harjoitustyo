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
public class SignUpController {
    @FXML
    public void backToLogin() throws IOException{
        YatzyUi.setRoot("login");
    }
}
