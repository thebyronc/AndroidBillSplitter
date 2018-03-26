package foiled.androidbillsplitter.models;

/**
 * Created by Sheep on 3/23/2018.
 */

public class People {
    private String name;
    private String email;
    public People(String name, String email){
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
