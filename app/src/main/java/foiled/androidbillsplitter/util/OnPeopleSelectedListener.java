package foiled.androidbillsplitter.util;

import java.util.ArrayList;

import foiled.androidbillsplitter.models.People;

public interface OnPeopleSelectedListener {
    public void onPeopleSelected(Integer position, ArrayList<People> Peoples, String source);
}
