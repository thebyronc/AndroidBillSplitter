package foiled.androidbillsplitter.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foiled.androidbillsplitter.adapter.PeopleArrayAdapter;
import foiled.androidbillsplitter.R;
import foiled.androidbillsplitter.models.People;

public class PeopleActivity extends AppCompatActivity {
    @BindView(R.id.peopleTitleTextView) TextView peopleTitleTextView;
    @BindView(R.id.addPeopleTextView) TextView mAddPeopleTextView;
    @BindView(R.id.peopleEditText) TextView mPeopleEditText;
    @BindView(R.id.addPeopleButton) Button mAddPeopleButton;
    @BindView(R.id.emailEditText) TextView mEmailEditText;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    public ArrayList<People> peoples = new ArrayList<>();
    private PeopleArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        ButterKnife.bind(this);
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

        mAddPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = mPeopleEditText.getText().toString();
                String newEmail = mEmailEditText.getText().toString();
                People newPeople = new People(newName, newEmail);
                peoples.add(newPeople);
                Toast.makeText(PeopleActivity.this, newName + " added.", Toast.LENGTH_LONG).show();
                mPeopleEditText.setText("");
                mEmailEditText.setText("");
            }
        });

        getPeople();

    }

    private void getPeople() {
        mAdapter = new PeopleArrayAdapter(this, android.R.layout.simple_list_item_1, peoples);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PeopleActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
}
