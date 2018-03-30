package foiled.androidbillsplitter.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.Query;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foiled.androidbillsplitter.Constants;
import foiled.androidbillsplitter.adapter.FirebasePeopleViewHolder;
import foiled.androidbillsplitter.adapter.PeopleArrayAdapter;
import foiled.androidbillsplitter.R;
import foiled.androidbillsplitter.models.People;

public class PeopleActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference mPeopleFireBase;
    private ValueEventListener mPeopleFireBaseListener;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private Query peopleQuery;

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

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mPeopleFireBase = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_PEOPLE)
                .child(uid);

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
        peopleQuery = mPeopleFireBase.getRef();


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

        mAddPeopleButton.setOnClickListener(this);
        setUpFirebaseAdapter();
//        getPeople();

    }
    @Override
    public void onClick(View view) {
        if (view == mAddPeopleButton) {
            String newName = mPeopleEditText.getText().toString();
            String newEmail = mEmailEditText.getText().toString();
            People newPeople = new People(newName, newEmail);
            savePeopleToFirebase(newPeople);
            Toast.makeText(PeopleActivity.this, newName + " added.", Toast.LENGTH_LONG).show();
            mPeopleEditText.setText("");
            mEmailEditText.setText("");
        }

    }
    public void savePeopleToFirebase(People people) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference pushRef = mPeopleFireBase.push();
        String pushId = pushRef.getKey();
        people.setPushId(pushId);
        pushRef.setValue(people);
    }

    private void getPeople() {
        mAdapter = new PeopleArrayAdapter(getApplicationContext(), peoples);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(PeopleActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPeopleFireBase.removeEventListener(mPeopleFireBaseListener);
    }
    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions options =
                new FirebaseRecyclerOptions.Builder<People>()
                        .setQuery(peopleQuery, People.class)
                        .build();
        mFirebaseAdapter = new FirebaseRecyclerAdapter<People, FirebasePeopleViewHolder>
                (options) {
            @Override
            public FirebasePeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.people_list_item, parent, false);
                return new FirebasePeopleViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(FirebasePeopleViewHolder holder, int position, People model) {
                holder.bindPeople(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

    }


}
