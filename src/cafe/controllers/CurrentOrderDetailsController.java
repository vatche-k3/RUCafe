package cafe.controllers;

import cafe.models.Order;
import cafe.models.MenuItem;
import cafe.models.StoreOrders;
import cafe.utils.Constants;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for CurrentOrderDetails FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class CurrentOrderDetailsController {

    // FXML references
    @FXML private ListView<MenuItem> currentOrderListView;
    @FXML private Button removeSelectedItemButton;
    @FXML private Button placeOrderButton;
    @FXML private TextField subTotalTextField;
    @FXML private TextField salesTaxTextField;
    @FXML private TextField totalTextField;

    private Order currentOrder;

    // Currently selected item in cart
    SimpleObjectProperty<MenuItem> currentlySelectedItem;

    /**
     * Initialize the CurrentOrderDetails Controller. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        currentlySelectedItem = new SimpleObjectProperty<>();
        currentOrder = Order.getInstance();

        // on select listener for menu item in the current order list view
        currentOrderListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            currentlySelectedItem.setValue(newValue);
        });

        // Remove Selected item should be disabled unless currently selected item is not null
        removeSelectedItemButton.disableProperty().bind(this.currentlySelectedItem.isNull());

        // Update UI and recompute price and update text fields
        updateUI();
        recomputePrice();
    }

    /**
     * Remove selected item from the order. Called whenever removeSelectedItem button is pressed
     */
    @FXML
    void removeItemFromOrder() {
        currentOrder.remove(this.currentlySelectedItem.getValue());
        // Update UI and Recompute price
        updateUI();
        recomputePrice();
    }

    /**
     * Place the order, updating StoreOrders. Called by JavaFX whenever PlaceOrder button is pressed.
     */
    @FXML
    void placeOrder() {
        // finalize current store order, which adds it to store orders
        currentOrder.finalizeOrder();

        // Show alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(Constants.SUCCESSFULLY_PLACED_ORDER_MSG);
        alert.show();

        // Close modal
        Stage stage = (Stage) this.placeOrderButton.getScene().getWindow();
        stage.close();

    }

    /**
     * Update UI, clear selections and repopulate order listview. Also check if placeOrder should be disabled
     */
    private void updateUI() {
        // Update UI
        currentOrderListView.getSelectionModel().clearSelection();
        currentOrderListView.setItems(FXCollections.observableArrayList(currentOrder.getItemsInOrder()));
        // Check if place order button should be disabled
        placeOrderButton.setDisable(this.currentOrder.getItemsInOrder().isEmpty());
    }

    /**
     * Recompute all the prices and update text boxes
     */
    private void recomputePrice() {
        // calc subtotal
        double subTotal = 0;
        for(MenuItem menuItem : this.currentOrder.getItemsInOrder()) {
            subTotal += menuItem.itemPrice();
        }
        // Update subtotal formatted as currency
        this.subTotalTextField.setText(String.format(Constants.CURRENCY_FORMAT_STRING, subTotal));

        // calc sales tax
        double salesTax = subTotal * Constants.SALES_TAX_RATE;
        // Update salestax formatted as currency
        this.salesTaxTextField.setText(String.format(Constants.CURRENCY_FORMAT_STRING, salesTax));

        // Calc total
        double total = subTotal + salesTax;
        // Update total formatted as currency
        this.totalTextField.setText(String.format(Constants.CURRENCY_FORMAT_STRING, total));
    }

}