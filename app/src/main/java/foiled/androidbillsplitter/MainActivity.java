package foiled.androidbillsplitter;

import android.content.Intent;
import android.support.design.widget.TabItem;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
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
