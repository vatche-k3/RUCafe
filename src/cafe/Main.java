package cafe;

import cafe.utils.Constants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

/**
 * Entry point for the entire JavaFX Application.
 * @author Reagan McFarland, Vatche Kafafian
 */
public class Main extends Application {

    /**
     * Start JavaFX Application
     * @param primaryStage the primary stage to be shown in the GUI Window
     * @throws Exception any exceptions that occur
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("./guis/MainMenu.fxml"));
        primaryStage.setScene(new Scene(root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
        primaryStage.show();
        primaryStage.setTitle(Constants.MAIN_MENU_WINDOW_TITLE);
        primaryStage.setResizable(false);
    }

    /**
     * Entry point for the entire RUCafe Application
     * @param args Command line arguments from the JVM
     */
    public static void main(String[] args) {
        launch(args);
    }

}