package cafe.controllers;

import cafe.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for OrdersPage FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class OrdersPageController extends Application {
    /**
     * Start the Orders Page GUI
     * @param primaryStage the primary stage to be shown in the GUI Window
     * @throws Exception any exceptions that occur
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../guis/OrdersPage.fxml"));
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.setTitle(Constants.ORDERS_PAGE_WINDOW_TITLE);
    }
}
