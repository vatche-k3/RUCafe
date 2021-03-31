package cafe.controllers;

import cafe.models.Coffee;
import cafe.models.Order;
import cafe.utils.CoffeeAddin;
import cafe.utils.CoffeeSize;
import cafe.utils.Constants;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;



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
    @FXML private CheckBox creamCheckbox;
    @FXML private Spinner<Integer> creamQuantity;
    @FXML private CheckBox milkCheckbox;
    @FXML private Spinner<Integer> milkQuantity;
    @FXML private CheckBox whippedCreamCheckbox;
    @FXML private Spinner<Integer> whippedCreamQuantity;
    @FXML private CheckBox syrupCheckbox;
    @FXML private Spinner<Integer> syrupQuantity;
    @FXML private CheckBox caramelCheckbox;
    @FXML private Spinner<Integer> caramelQuantity;
    @FXML private Spinner<Integer> quantitySpinner;


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

        // Set default selection to SHORT
        coffeeSizesComboBox.getSelectionModel().select(CoffeeSize.SHORT);
        currentCoffee.setSize(CoffeeSize.SHORT);

        // cream quantity on value change listener
        creamQuantity.valueProperty().addListener((obs, oldValue, newValue) -> {
            // Check if it the event was an increment or decrement
            if(newValue > oldValue) {
                currentCoffee.add(CoffeeAddin.CREAM);
            } else {
                currentCoffee.remove(CoffeeAddin.CREAM);
            }
            // Refresh dynamic price
            recomputeItemPrice();
        });

        // milk quantity on value change listener
        milkQuantity.valueProperty().addListener((obs, oldValue, newValue) -> {
            // Check if it the event was an increment or decrement
            if(newValue > oldValue) {
                currentCoffee.add(CoffeeAddin.MILK);
            } else {
                currentCoffee.remove(CoffeeAddin.MILK);
            }
            // Refresh dynamic price
            recomputeItemPrice();
        });

        // whipped cream quantity on value change listener
        whippedCreamQuantity.valueProperty().addListener((obs, oldValue, newValue) -> {
            // Check if it the event was an increment or decrement
            if(newValue > oldValue) {
                currentCoffee.add(CoffeeAddin.WHIPPED_CREAM);
            } else {
                currentCoffee.remove(CoffeeAddin.WHIPPED_CREAM);
            }
            // Refresh dynamic price
            recomputeItemPrice();
        });

        // syrup quantity on value change listener
        syrupQuantity.valueProperty().addListener((obs, oldValue, newValue) -> {
            // Check if it the event was an increment or decrement
            if(newValue > oldValue) {
                currentCoffee.add(CoffeeAddin.SYRUP);
            } else {
                currentCoffee.remove(CoffeeAddin.SYRUP);
            }
            // Refresh dynamic price
            recomputeItemPrice();
        });

        // caramel quantity on value change listener
        caramelQuantity.valueProperty().addListener((obs, oldValue, newValue) -> {
            // Check if it the event was an increment or decrement
            if(newValue > oldValue) {
                currentCoffee.add(CoffeeAddin.CARAMEL);
            } else {
                currentCoffee.remove(CoffeeAddin.CARAMEL);
            }
            // Refresh dynamic price
            recomputeItemPrice();
        });

        // on value change for spinner should update coffee's quantity and recomputePrice
        quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.currentCoffee.updateQuantity(newValue);
            recomputeItemPrice();
        });

        // Bind add to order button to be disabled unless a size is selected in the combobox
        addToOrderButton.disableProperty().bind(coffeeSizesComboBox.valueProperty().isNull());

        // Bind spinner's for each addin to be disabled unless the relevant checkbox is selected
        creamQuantity.disableProperty().bind(creamCheckbox.selectedProperty().not());
        milkQuantity.disableProperty().bind(milkCheckbox.selectedProperty().not());
        whippedCreamQuantity.disableProperty().bind(whippedCreamCheckbox.selectedProperty().not());
        syrupQuantity.disableProperty().bind(syrupCheckbox.selectedProperty().not());
        caramelQuantity.disableProperty().bind(caramelCheckbox.selectedProperty().not());

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
     * Handler for addin checkbox selected
     * @param e ActionEvent that triggered the handler
     */
    @FXML
    void addinCheckedHandler(ActionEvent e) {
        // Ensure that it was a checkbox that triggered the event, and cast source to CheckBox object
        if(e.getSource() instanceof CheckBox) {
            // Cast text of the checkbox to a coffee addin
            CheckBox sourceCheckBox = (CheckBox) e.getSource();
            // Cast to CoffeeAddin, making sure to replace all spaces with "_" so that it matches correctly
            CoffeeAddin addin = CoffeeAddin.valueOf(sourceCheckBox.getText().toUpperCase().replace(Constants.SPACE_CHARACTER, Constants.ENUM_SPACE_REPLACEMENT_CHARACTER));
            // This event gets called whenever it checks or gets unchecked, so we need to determine if we need to add the addin, or remove it
            if(sourceCheckBox.isSelected()) {
                // If we are selecting the checbox, add addin to coffee
                currentCoffee.add(addin);
            } else {
                // We need to remove all addins from the coffee object that match the newAddin
                while(currentCoffee.remove(addin));

                // We also need to reset the corresponding spinner back to 1
                switch(addin) {
                    case CREAM:
                        creamQuantity.getValueFactory().setValue(Constants.COFFEE_ADDIN_SPINNER_MIN_VALUE);
                        break;
                    case MILK:
                        milkQuantity.getValueFactory().setValue(Constants.COFFEE_ADDIN_SPINNER_MIN_VALUE);
                        break;
                    case WHIPPED_CREAM:
                        whippedCreamQuantity.getValueFactory().setValue(Constants.COFFEE_ADDIN_SPINNER_MIN_VALUE);
                        break;
                    case SYRUP:
                        syrupQuantity.getValueFactory().setValue(Constants.COFFEE_ADDIN_SPINNER_MIN_VALUE);
                        break;
                    case CARAMEL:
                        caramelQuantity.getValueFactory().setValue(Constants.COFFEE_ADDIN_SPINNER_MIN_VALUE);
                        break;
                }
            }

            // recompute dynamic price
            recomputeItemPrice();
        }
    }

    /**
     * Recompute and render new item price
     */
    void recomputeItemPrice() {
        double newPrice = 0;
        try {
            newPrice = currentCoffee.itemPrice() * currentCoffee.getQuantity();
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
        // Add to order
        Order.getInstance().add(currentCoffee);

        // Show alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(Constants.SUCCESSFULLY_ADDED_ITEM_TO_ORDER_MSG);
        alert.show();

        // Close modal
        Stage stage = (Stage) this.currentPrice.getScene().getWindow();
        stage.close();
    }


}