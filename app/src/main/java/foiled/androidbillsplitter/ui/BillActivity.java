package foiled.androidbillsplitter.ui;


import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foiled.androidbillsplitter.Constants;
import foiled.androidbillsplitter.adapter.BillArrayAdapter;
import foiled.androidbillsplitter.R;

import android.graphics.Typeface;

public class BillActivity extends Activity {
    @BindView(R.id.billTitleTextView) TextView mBillTitleTextView;
    @BindView(R.id.billListView) ListView mBillListView;

    private SharedPreferences mSharedPreferences;
    private String mBillFromSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);

        mBillListView = (ListView) findViewById(R.id.billListView);

        Typeface bebasNeueBold = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue Bold.ttf");
        mBillTitleTextView.setTypeface(bebasNeueBold);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mBillFromSharedPreferences = mSharedPreferences.getString(Constants.PREFERENCES_BILL, null);


        Intent intent = getIntent();
        ArrayList<String> bills = intent.getStringArrayListExtra("bill");

        if (mBillFromSharedPreferences != null) {
            bills.add(mBillFromSharedPreferences);
        }

        BillArrayAdapter adapter = new BillArrayAdapter(this, android.R.layout.simple_list_item_1, bills);
        mBillListView.setAdapter(adapter);
    }

}
