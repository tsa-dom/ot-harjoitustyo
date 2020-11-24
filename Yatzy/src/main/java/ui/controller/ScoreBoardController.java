/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import service.game.Statistic;
import ui.YatzyUi;

/**
 *
 * @author Tapio Salonen
 */
public class ScoreBoardController implements Initializable{
    @FXML private TableView<Statistic> table;
    @FXML private TableColumn<Statistic, String> player, score, gameMode, maxScore;
    @FXML
    private void backToMenu() throws IOException {
        YatzyUi.setRoot("menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player.setCellFactory(TextFieldTableCell.forTableColumn());
        player.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getPlayer()));
        score.setCellFactory(TextFieldTableCell.forTableColumn());
        score.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getScore()));
        gameMode.setCellFactory(TextFieldTableCell.forTableColumn());
        gameMode.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getGameMode()));
        maxScore.setCellFactory(TextFieldTableCell.forTableColumn());
        maxScore.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().getMaxScore()));
        Storage.setStatistics();
        table.setItems(Storage.statistics);
    }
}
