package foiled.androidbillsplitter.models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Restaurant {
    private String name;
    private String website;
    private String imageUrl;
    private List<String> address = new ArrayList<>();

    public Restaurant() {}

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    public List<String> getAddress() {
        return address;
    }

    public Restaurant(String name, String website, String imageUrl, ArrayList<String> address) {
        this.name = name;
        this.website = website;
        this.imageUrl = imageUrl;
        this.address = address;
    }
}
