package foiled.androidbillsplitter.models;

import java.util.ArrayList;


public class Bill {
    private String name;
    private Double total;
    private ArrayList<Item> items = new ArrayList<>();
    private String pushId;

    public Bill(String name) {
        this.name = name;
        this.total = calcTotal();
        items.add(new Item("Item", 0.00));
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
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
