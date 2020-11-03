package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import manager.*;

/**
 * JavaFX App
 */
public class YatzyUi extends Application {

    private static Scene scene;
    public static DatabaseManager databaseManager;
    public static SetUpManager setUpManager;

    @Override
    public void init(){
        databaseManager = new DatabaseManager();
        setUpManager = new SetUpManager();
        setUpManager.executeSetUp();
    }
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 1050, 700);
        stage.setScene(scene);
        stage.setTitle("Yatzy");
        stage.setResizable(false);
        stage.show();
    }
    @Override
    public void stop(){
        System.exit(0);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(YatzyUi.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void launch(String[] args) {
        launch();
    }

}