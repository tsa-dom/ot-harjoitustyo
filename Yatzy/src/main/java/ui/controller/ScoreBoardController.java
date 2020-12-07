/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.domain.StatisticIF;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class ScoreBoardController implements Initializable {
    @FXML private TableView<StatisticIF> table;
    @FXML private TableColumn<StatisticIF, String> player, score, gameMode, maxScore;
    @FXML
    private void backToMenu() throws IOException {
        YatzyUi.setRoot("menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player.setCellValueFactory(new PropertyValueFactory<>("player"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        gameMode.setCellValueFactory(new PropertyValueFactory<>("gameMode"));
        maxScore.setCellValueFactory(new PropertyValueFactory<>("maxScore"));
        ItemNode.setStatistics();
        table.setItems(ItemNode.statistics);
    }
}
