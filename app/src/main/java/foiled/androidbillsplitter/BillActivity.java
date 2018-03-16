package foiled.androidbillsplitter;


import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentTransaction;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillActivity extends Activity {
    @BindView(R.id.billListView) ListView mBillListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);

        mBillListView = (ListView) findViewById(R.id.billListView);

        Intent intent = getIntent();
        ArrayList<String> bills = intent.getStringArrayListExtra("bills");
        BillArrayAdapter adapter = new BillArrayAdapter(this, android.R.layout.simple_list_item_1, bills);
        mBillListView.setAdapter(adapter);
    }

}
