package cafe.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for MainMenu FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class MainMenuController extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("guis/MainMenu.fxml"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.setTitle("Hello World");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
