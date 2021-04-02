package cafe.controllers;

import cafe.models.MenuItem;
import cafe.models.Order;
import cafe.models.StoreOrders;
import cafe.utils.Constants;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.util.ArrayList;

/**
 * Controller for OrdersPage FXML View
 *
 * @author Reagan McFarland, Vatche Kafafian
 */
public class OrdersPageController {

    // FXML references
    @FXML private TreeView<String> ordersTreeView;
    @FXML private Button removeSelectedOrderButton;

    SimpleObjectProperty<Order> currentlySelectedOrder;

    /**
     * Initialize the OrdersPageController. Called behind the scenes by JavaFX
     */
    @FXML
    protected void initialize() {
        currentlySelectedOrder = new SimpleObjectProperty<>(null);

        // Create TreeItems for TreeView
        buildOrderTreeView();

        // On select listener for TreeView
        ordersTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // We only want to update our currentlySelectedOrder if an actual order was selected, not the root node
            // or a menu item
            if(newValue == null || newValue.isLeaf() || newValue.getValue().equals(Constants.ORDER_TREE_ROOT_NODE_STRING)) {
                currentlySelectedOrder.set(null);
            } else {
                // We can figure out which order it is by the ID, since the ids are recomputed upon deletion
                String nodeText = newValue.getValue();
                String idSubstring = nodeText.substring(0, nodeText.indexOf(" "));
                try {
                    int orderIndexInOrders = Integer.parseInt(idSubstring);
                    // successfully parsed index, so set property
                    currentlySelectedOrder.set(StoreOrders.getInstance().getOrders().get(orderIndexInOrders));
                } catch (Exception e) {
                    // failed, so reset to null
                    currentlySelectedOrder.set(null);
                }
            }
        });

        // remove currently selected should be disabled unless we have an order selected
        removeSelectedOrderButton.disableProperty().bind(currentlySelectedOrder.isNull());
    }

    /**
     * Remove an order from the store orders. Called by JavaFX whenever removedSelectedOrder button is pressed
     */
    @FXML
    void removeSelectedOrderHandler() {
        // Remove order, reset currentlySelectedOrder, reset order ids, and rebuild tree view
        StoreOrders.getInstance().remove(this.currentlySelectedOrder.getValue());
        this.currentlySelectedOrder.setValue(null);
        StoreOrders.getInstance().resetOrderIds();
        buildOrderTreeView();

        // show alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(Constants.CANCELLED_ORDER_MSG);
        alert.show();
    }

    /**
     * Build order tree view from current orders and update the UI
     */
    public void buildOrderTreeView() {
        TreeItem<String> rootTreeItem = new TreeItem<>(Constants.ORDER_TREE_ROOT_NODE_STRING);
        ArrayList<Order> orders = StoreOrders.getInstance().getOrders();
        for (Order order : orders) {
            // Build order tree item string, follows the format "Order (index) = (price)"
            TreeItem<String> orderTreeItem = new TreeItem<>(order.toString());
            for (MenuItem item : order.getItemsInOrder()) {
                // Make a new tree item to add to the currTreeItem for every menu item in the order
                TreeItem<String> menuItemTreeItem = new TreeItem<>(item.toString());
                orderTreeItem.getChildren().add(menuItemTreeItem);
            }
            // add curr order tree item to orders
            rootTreeItem.getChildren().add(orderTreeItem);
            orderTreeItem.setExpanded(true);
        }

        // Update tree view to new root tree item
        rootTreeItem.setExpanded(true);
        this.ordersTreeView.setRoot(rootTreeItem);

    }
}