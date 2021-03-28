package cafe.controllers;

import cafe.models.Coffee;
import cafe.models.Order;
import cafe.utils.CoffeeSize;
import cafe.utils.Constants;
import javafx.beans.binding.Binding;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


/**
 * Controller for OrderingCoffee FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class OrderingCoffeeController {

    // FXML references
    @FXML private Button addToOrderButton;
    @FXML private TextField currentPrice;
    @FXML private ComboBox<CoffeeSize> coffeeSizesComboBox;

    // Our current coffee order object
    private Coffee currentCoffee;

    /**
     * Initialize the OrderingCoffeeController. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        currentCoffee = new Coffee();

        // Popualate combobox with sizes
        coffeeSizesComboBox.setItems(FXCollections.observableArrayList(CoffeeSize.values()));

        // Bind add to order button to be disabled unless a size is selected in the combobox
        addToOrderButton.disableProperty().bind(coffeeSizesComboBox.valueProperty().isNull());

        // recompute dynamic item price on initialization
        recomputeItemPrice();
    }

    /**
     * Handler for size of coffee combo box selected
     */
    @FXML
    void onSizeSelection() {
        // Update coffee's size
        this.currentCoffee.setSize(coffeeSizesComboBox.getValue());
        // Recompute dynamic item price field
        recomputeItemPrice();
    }

    /**
     * Recompute and render new item price
     */
    void recomputeItemPrice() {
        double newPrice = 0;
        try {
            newPrice = currentCoffee.itemPrice();
        } catch (IllegalStateException e) {
            // We haven't selected size yet, so price is zero because we don't know what size we are dealing with yet
            newPrice = 0.0;
        } finally {
            currentPrice.setText(String.format(Constants.CURRENCY_FORMAT_STRING, newPrice));
        }
    }

    /**
     * Adds the current Cofee to the current Order. Called by "Add to Order" button
     */
    @FXML
    protected void addCoffeeToOrder() {
        // TODO have button call this function
        Order.getInstance().add(currentCoffee);
    }


}