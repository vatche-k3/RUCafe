package cafe;

public class Coffee extends MenuItem implements Customizable {
    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        return false;
    }
}
