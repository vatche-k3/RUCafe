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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * Export orders to file. Triggered by JavaFX whenever Save/Export Order Button is pressed.
     *
     * The format for the output of the file is almost identical to the format of the TreeView:
     *
     * (id) - Total Price = (cost)
     *      (Menu Item)
     *      (Menu Item)
     *      ....
     * (id) - Total Price = (cost)
     *      (Menu Item)
     *      (Menu Item)
     *      ...
     * ...
     */
    @FXML
    void exportOrdersToFile() {
        // Open file dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(Constants.FILE_CHOOSER_EXPORT_TITLE);
        fileChooser.getExtensionFilters().addAll(Constants.TEXT_FILES_EXTENSION_FILTER, Constants.ALL_FILES_EXTENSION_FILTER);
        Stage fileChooserStage = new Stage();
        File targetFile = fileChooser.showSaveDialog(fileChooserStage);

        // Check if thet selected anything
        if(targetFile == null) {
            Alert noFileSelectedAlert = new Alert(Alert.AlertType.ERROR);
            noFileSelectedAlert.setContentText(Constants.NO_FILE_SELECTED_MSG);
            noFileSelectedAlert.show();
            return;
        }

        // If the file provdided doesn't exist, create it
        if(!targetFile.exists()) {
            try {
                targetFile.createNewFile();
            } catch (IOException e) {
                Alert failedToCreateFileAlert = new Alert(Alert.AlertType.ERROR);
                failedToCreateFileAlert.setContentText(Constants.FAILED_TO_CREATE_FILE_MSG);
                failedToCreateFileAlert.show();
                return;
            }
        }

        // We can start writing to the file according to the format above
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(targetFile));
            String exportString = "";
            for(Order order : StoreOrders.getInstance().getOrders()) {
                // Append order header
                exportString += order.toString() + "\n";
                // Append each menu item in the order
                for(MenuItem menuItem : order.getItemsInOrder()) {
                    exportString += Constants.FILE_OUTPUT_MENU_ITEM_PREFIX + menuItem.toString() + "\n";
                }
            }

            // Write to file and close the writer
            bufferedWriter.write(exportString);
            bufferedWriter.close();

            // Showm message that we succeeded
            Alert successfullyExportedAlert = new Alert(Alert.AlertType.INFORMATION);
            successfullyExportedAlert.setContentText(Constants.SUCCESSFULLY_EXPORTED_STORE_ORDERS_MSG);
            successfullyExportedAlert.show();
        } catch (IOException e) {
            // Show message that we failed
            Alert failedToWriteFileAlert = new Alert(Alert.AlertType.ERROR);
            failedToWriteFileAlert.setContentText(Constants.FAILED_TO_WRITE_FILE_MSG);
            failedToWriteFileAlert.show();
        }
    }
}