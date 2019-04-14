package frequent.items.models;

import java.util.TreeSet;

public class Basket {

    private TreeSet<Integer> items;

    public Basket(TreeSet<Integer> items) {
        this.items = items;
    }

    public TreeSet<Integer> getItems() {
        return items;
    }
}
