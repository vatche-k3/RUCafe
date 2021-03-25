package cafe.models;


import java.util.ArrayList;

public class Order implements Customizable {

    ArrayList<MenuItem> itemsInOrder;

    private static Order instance;
    private Order() {
        itemsInOrder = new ArrayList<>();
    }
    public static Order getInstance() {
        if(instance == null)
            instance = new Order();
        return instance;
    }

    public void finalizeOrder() {
        // add to store orders
        // make a new order
        StoreOrders.getInstance().add(this);
        instance = null;
    }
    @Override
    public boolean add(Object obj) {
        // TODO
        // Make sure that obj is a MenuItem
        // Add to itemsInOrder
        // Return whether or not it was successful
        if(obj instanceof MenuItem) {
            MenuItem objAsMenuItem = (MenuItem)obj;
            this.itemsInOrder.add(objAsMenuItem);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        // TODO
        // Make sure that obj is a MenuItem
        // Refer to Coffee.remove for a good example
        return false;
    }

}
