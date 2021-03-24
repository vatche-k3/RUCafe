package cafe.controllers;

import cafe.models.Coffee;
import cafe.utils.Constants;
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

    /**
     * Start the Main Menu GUI
     * @param primaryStage the primary stage to be shown in the GUI Window
     * @throws Exception any exceptions that occur
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../guis/MainMenu.fxml"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.setTitle(Constants.MAIN_MENU_WINDOW_TITLE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
