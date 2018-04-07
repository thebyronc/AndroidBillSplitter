package foiled.androidbillsplitter.models;

import org.parceler.Parcel;

@Parcel
public class People {
    private String name;
    private String email;
    private String pushId;
    String index;

    public People() {}
    public People(String name, String email){
        this.name = name;
        this.email = email;
        this.index = "not_specified";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
