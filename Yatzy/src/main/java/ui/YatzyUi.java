package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import java.io.IOException;
import core.Core;
import javafx.scene.Parent;
import javafx.scene.Scene;
import ui.controller.ItemNode;

/** JavaFX App.
 */
public class YatzyUi extends Application {

    private static Scene scene;
    private Core core;
    private ItemNode itemNode;

    @Override
    public void init() throws Exception {
        core = new Core();
        core.install("Programfiles/");
        itemNode = new ItemNode();
        Core.clearUpperCount();
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
    public void stop() {
        System.exit(0);
    }
    /** Sets new scene to the stage.
     * @param fxml fxml file name
     * @throws IOException 
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(YatzyUi.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    /** Launches JavaFX application.
     * @param args 
     */
    public static void launch(String[] args) {
        launch();
    }

}