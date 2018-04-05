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
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import foiled.androidbillsplitter.Constants;
import foiled.androidbillsplitter.R;
import foiled.androidbillsplitter.models.Bill;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mBillFireBase;
    private ValueEventListener mBillFireBaseListener;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private Query billQuery;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mBillFireBase = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_BILL)
                .child(uid);

        mBillFireBaseListener = mBillFireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot billSnapshot : dataSnapshot.getChildren()) {
                    String bill = billSnapshot.getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        billQuery = mBillFireBase.getRef();

        bills.add("Luk Lac");
        bills.add("Shotun Sushi");
        bills.add("McDonalds");
        bills.add("Blue Star Donuts");

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mPeopleButton.setOnClickListener(this);
        mBillButton.setOnClickListener(this);
        mAddBillButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == mPeopleButton) {
            Intent intent = new Intent(MainActivity.this, PeopleActivity.class);
            startActivity(intent);
        }
        if (view == mBillButton) {
            Intent intent = new Intent(MainActivity.this, BillActivity.class);
            intent.putExtra("bill", bills);
            startActivity(intent);
        }
        if (view == mAddBillButton) {
            String billName = mAddBillEditText.getText().toString();
            Bill bill = new Bill(billName);
            saveBillToFirebase(bill);
            addToSharedPreferences(billName);
            Toast.makeText(MainActivity.this, bill.getName() + " added.", Toast.LENGTH_LONG).show();
            mAddBillEditText.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveBillToFirebase(Bill bill) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference pushRef = mBillFireBase.push();
        String pushId = pushRef.getKey();
        bill.setPushId(pushId);
        pushRef.setValue(bill);
    }

    private void addToSharedPreferences(String newBill) {
        mEditor.putString(Constants.PREFERENCES_BILL, newBill).apply();
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

}
