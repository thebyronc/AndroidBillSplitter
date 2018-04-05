package foiled.androidbillsplitter.models;

import java.util.ArrayList;

/**
 * Created by Guest on 3/29/18.
 */

public class Bill {
    private String name;
    private Double total;
    private ArrayList<Item> items;

    public Bill(String name) {
        this.name = name;
        this.total = calcTotal();
    }

    private Double calcTotal() {
        Double total = 0.00;
        for (Item item : items) {
            total += item.getCost();
        }
        return total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotal() {
        return total;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
