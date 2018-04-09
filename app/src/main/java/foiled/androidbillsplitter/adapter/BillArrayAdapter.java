package foiled.androidbillsplitter.adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sheep on 3/16/2018.
 */

public class BillArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<String> mBills;
    private int mOrientation;

    public BillArrayAdapter(Context mContext, int resource, ArrayList<String> mBills) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mBills = mBills;
    }
    @Override
    public Object getItem(int position) {
        String bill = mBills.get(position);
        return String.format(bill);
    }
    @Override
    public int getCount() {
        return mBills.size();
    }
}
