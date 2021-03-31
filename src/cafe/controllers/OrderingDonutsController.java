package cafe.controllers;

import cafe.models.Donut;
import cafe.utils.DonutFlavor;
import cafe.utils.DonutType;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableSetValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

/**
 * Controller for OrderingDonuts FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class OrderingDonutsController {

    // FXML references
    @FXML private ListView<DonutFlavor> yeastDonutListView;
    @FXML private ListView<DonutFlavor> cakeDonutListView;
    @FXML private ListView<DonutFlavor> holeDonutListView;
    @FXML private Spinner<Integer> quantitySpinner;
    @FXML private Button addDonutSelectionToCartButton;
    @FXML private ListView<Donut> donutCartListView;
    @FXML private TitledPane yeastDonutTitledPane;
    @FXML private TitledPane cakeDonutTitledPane;
    @FXML private TitledPane holeDonutTitledPane;

    // Constants
    private static final int MIN_DONUT_QUANTITY_SPINNER_VALUE = 1;
    private static final int MAX_DONUT_QUANTITY_SPINNER_VALUE = 100;
    private static final int DEFAULT_DONUT_QUANTITY_SPINNER_VALUE = MIN_DONUT_QUANTITY_SPINNER_VALUE;

    // All the donuts in our cart
    ArrayList<Donut> donutsInCart;
    // Currently selected properties
    SimpleObjectProperty<DonutType> currentlySelectedType;
    SimpleObjectProperty<DonutFlavor> currentlySelectedFlavor;

    /**
     * Initialize the OrderingDonutsController. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        donutsInCart = new ArrayList<>();
        currentlySelectedType = new SimpleObjectProperty<>();
        currentlySelectedFlavor = new SimpleObjectProperty<>();

        // populate list views
        yeastDonutListView.setItems(FXCollections.observableArrayList(DonutFlavor.values()));
        cakeDonutListView.setItems(FXCollections.observableArrayList(DonutFlavor.values()));
        holeDonutListView.setItems(FXCollections.observableArrayList(DonutFlavor.values()));

        // set quantity spinner min, default, and max value
        quantitySpinner.setValueFactory(
            new SpinnerValueFactory.IntegerSpinnerValueFactory(
                MIN_DONUT_QUANTITY_SPINNER_VALUE,
                MAX_DONUT_QUANTITY_SPINNER_VALUE,
                DEFAULT_DONUT_QUANTITY_SPINNER_VALUE
            )
        );

        // On select listener for yeastDonutListView
        yeastDonutListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // if we are unselecting (i.e., newValue is null) then don't do anything
            if(newValue == null) return;

            this.currentlySelectedType.setValue(DonutType.YEAST);
            this.currentlySelectedFlavor.setValue(newValue);
            // deselect the other list views
            cakeDonutListView.getSelectionModel().clearSelection();
            holeDonutListView.getSelectionModel().clearSelection();
        });
        // On select listener for cakeDonutListView
        cakeDonutListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // if we are unselecting (i.e., newValue is null) then don't do anything
            if(newValue == null) return;

            this.currentlySelectedType.setValue(DonutType.CAKE);
            this.currentlySelectedFlavor.setValue(newValue);
            // deselect the other list views
            yeastDonutListView.getSelectionModel().clearSelection();
            holeDonutListView.getSelectionModel().clearSelection();
        });
        // On select listener for holeDonutListView
        holeDonutListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // if we are unselecting (i.e., newValue is null) then don't do anything
            if(newValue == null) return;

            this.currentlySelectedType.setValue(DonutType.HOLE);
            this.currentlySelectedFlavor.setValue(newValue);
            // deselect the other list views
            yeastDonutListView.getSelectionModel().clearSelection();
            cakeDonutListView.getSelectionModel().clearSelection();
        });

        // Add donut selection to cart should be disabled unless the flavor and type are selected
        addDonutSelectionToCartButton.disableProperty().bind(
            Bindings.and(
                this.currentlySelectedType.isNotNull(),
                this.currentlySelectedFlavor.isNotNull()
            ).not()
        );

    }

    /**
     * Unselect all lists, and collapse all list views. Called upon a successful add or remove to the cart.
     */
    void clearSelections() {
        // Collapse list views
        yeastDonutTitledPane.setExpanded(false);
        cakeDonutTitledPane.setExpanded(false);
        holeDonutTitledPane.setExpanded(false);

        // Unselect all items from list views
        yeastDonutListView.getSelectionModel().clearSelection();
        cakeDonutListView.getSelectionModel().clearSelection();
        holeDonutListView.getSelectionModel().clearSelection();
        donutCartListView.getSelectionModel().clearSelection();
    }

    /**
     * Triggered whenever the addDonutSelectionToCart button is pressed
     */
    @FXML
    void addDonutSelectionToCart() {
        // create a new donut object and add it to the cart
        Donut newDonut = new Donut(
            this.currentlySelectedType.getValue(),
            this.currentlySelectedFlavor.getValue(),
            this.quantitySpinner.getValue()
        );
        // We need to defragment donuts that are of the same type and flavor
        Donut matchingDonut = null;
        for(Donut donut : this.donutsInCart) {
            if(donut.equals(newDonut)) {
                matchingDonut = donut;
                break;
            }
        }

        // If there is a matching donut, just update its quantity instead. Otherwise, add it to the cart
        if(matchingDonut == null) {
            this.donutsInCart.add(newDonut);
        } else {
            matchingDonut.updateQuantity(matchingDonut.getQuantity() + newDonut.getQuantity());
        }

        // Update UI
        donutCartListView.setItems(FXCollections.observableArrayList(this.donutsInCart));
        clearSelections();
    }
}