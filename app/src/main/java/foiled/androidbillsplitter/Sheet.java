package foiled.androidbillsplitter;

/**
 * Created by Sheep on 3/23/2018.
 */

public class Sheet {
    private String name;
    private double cost;
    public Sheet(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}
