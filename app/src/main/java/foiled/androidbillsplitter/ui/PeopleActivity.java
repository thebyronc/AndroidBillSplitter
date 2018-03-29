package foiled.androidbillsplitter.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foiled.androidbillsplitter.Constants;
import foiled.androidbillsplitter.adapter.PeopleArrayAdapter;
import foiled.androidbillsplitter.R;
import foiled.androidbillsplitter.models.People;

public class PeopleActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mPeopleFireBase;
    private ValueEventListener mPeopleFireBaseListener;

    @BindView(R.id.peopleEditText) TextView mPeopleEditText;
    @BindView(R.id.emailEditText) TextView mEmailEditText;
    @BindView(R.id.addPeopleButton) Button mAddPeopleButton;

    @BindView(R.id.peopleTitleTextView) TextView peopleTitleTextView;
    @BindView(R.id.addPeopleTextView) TextView mAddPeopleTextView;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    public ArrayList<People> peoples = new ArrayList<>();
    private PeopleArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        ButterKnife.bind(this);

        mPeopleFireBase = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_PEOPLE);
        mPeopleFireBaseListener = mPeopleFireBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot peopleSnapshot : dataSnapshot.getChildren()) {
                    String people = peopleSnapshot.getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        People people1 = new People("Byron Chang", "thebyronc@gmail.com");
        People people2 = new People("Christine Yin", "thebyronc@gmail.com");
        People people3 = new People("Tasha Chang", "thebyronc@gmail.com");
        People people4 = new People("Ken-Han Liang", "thebyronc@gmail.com");
        peoples.add(people1);
        peoples.add(people2);
        peoples.add(people3);
        peoples.add(people4);

        Typeface bebasNeueBold = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue Bold.ttf");
        peopleTitleTextView.setTypeface(bebasNeueBold);

        mPeopleEditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPeopleEditText.setText("");
            }
        });
        mEmailEditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mEmailEditText.setText("");
            }
        });

        mAddPeopleButton.setOnClickListener(this);

        getPeople();

    }
    @Override
    public void onClick(View v) {
        String newName = mPeopleEditText.getText().toString();
        String newEmail = mEmailEditText.getText().toString();
        People newPeople = new People(newName, newEmail);
        savePeopleToFirebase(newPeople);
        Toast.makeText(PeopleActivity.this, newName + " added.", Toast.LENGTH_LONG).show();
        mPeopleEditText.setText("");
        mEmailEditText.setText("");
    }
    public void savePeopleToFirebase(People people) {
        mPeopleFireBase.push().setValue(people);
    }

    private void getPeople() {
        mAdapter = new PeopleArrayAdapter(getApplicationContext(), peoples);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PeopleActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPeopleFireBase.removeEventListener(mPeopleFireBaseListener);
    }

}
