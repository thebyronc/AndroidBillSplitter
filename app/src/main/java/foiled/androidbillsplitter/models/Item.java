package foiled.androidbillsplitter.models;

/**
 * Created by Guest on 4/5/18.
 */

public class Item {
    private String name;
    private Double cost;
    private People people;

    public Item(String name, Double cost, People people) {
        this.name = name;
        this.cost = cost;
        this.people = people;
    }

    public String getName() {
        return name;
    }

    public Double getCost() {
        return cost;
    }

    public People getPeople() {
        return people;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
