package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class YatzyUi extends Application {

    private static Scene scene;

    @Override
    public void init(){
        
    }
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"), 1050, 700);
        stage.setScene(scene);
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