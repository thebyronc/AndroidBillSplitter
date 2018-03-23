package foiled.androidbillsplitter.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Sheep on 3/16/2018.
 */

public class PeopleArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<String> mPeoples;

    public PeopleArrayAdapter(Context mContext, int resource, ArrayList<String> mPeoples) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mPeoples = mPeoples;
    }
    @Override
    public Object getItem(int position) {
        String bill = mPeoples.get(position);
        return String.format(bill);
    }
    @Override
    public int getCount() {
        return mPeoples.size();
    }
}
