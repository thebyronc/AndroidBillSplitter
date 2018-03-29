package foiled.androidbillsplitter.ui;

import android.Manifest;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import foiled.androidbillsplitter.R;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;


public class MainActivity extends AppCompatActivity {
    @BindView(R.id.homeButton) Button mHomeButton;
    @BindView(R.id.peopleButton) Button mPeopleButton;
    @BindView(R.id.billButton) Button mBillButton;
    @BindView(R.id.addBillEditText) EditText mAddBillEditText;
    @BindView(R.id.addBillButton) Button mAddBillButton;

    public ArrayList<String> bills = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        bills.add("Luk Lac");
        bills.add("Shotun Sushi");
        bills.add("McDonalds");
        bills.add("Blue Star Donuts");


        mPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PeopleActivity.class);
                startActivity(intent);
            }
        });

        mBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BillActivity.class);
                intent.putExtra("bill", bills);
                startActivity(intent);
            }
        });

        mAddBillEditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAddBillEditText.setText("");
            }
        });
        mAddBillButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newBill = mAddBillEditText.getText().toString();
                bills.add(newBill);
                Toast.makeText(MainActivity.this, newBill + " added.", Toast.LENGTH_LONG).show();
                mAddBillEditText.setText("");
            }
        });


    }


}
