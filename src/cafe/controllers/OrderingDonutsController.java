package cafe.controllers;

import cafe.models.Donut;
import javafx.fxml.FXML;

import java.util.ArrayList;

/**
 * Controller for OrderingDonuts FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class OrderingDonutsController {

    ArrayList<Donut> donutsInCart;

    /**
     * Initialize the OrderingDonutsController. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        // TODO initialize / do any preprocessing that is necessary
        donutsInCart = new ArrayList<>();
    }
}