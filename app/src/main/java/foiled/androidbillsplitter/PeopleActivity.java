package foiled.androidbillsplitter;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleActivity extends AppCompatActivity {
    @BindView(R.id.peopleTitleTextView) TextView peopleTitleTextView;
    @BindView(R.id.addPeopleTextView) TextView mAddPeopleTextView;
    @BindView(R.id.peopleEditText) TextView mPeopleEditText;
    @BindView(R.id.addPeopleButton) Button mAddPeopleButton;
    @BindView(R.id.peopleListView) ListView mPeopleListView;

    public ArrayList<String> peoples = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);
        ButterKnife.bind(this);

        peoples.add("Byron Chang");
        peoples.add("Christine Yin");
        peoples.add("Tasha Chang");
        peoples.add("Ken Han-Liang");

        Typeface bebasNeueBold = Typeface.createFromAsset(getAssets(), "fonts/BebasNeue Bold.ttf");
        peopleTitleTextView.setTypeface(bebasNeueBold);

        mPeopleEditText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mPeopleEditText.setText("");
            }
        });

        mAddPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPerson = mPeopleEditText.getText().toString();
                peoples.add(newPerson);
                Toast.makeText(PeopleActivity.this, newPerson + " added.", Toast.LENGTH_LONG).show();
                mPeopleEditText.setText("");
            }
        });

        PeopleArrayAdapter adapter = new PeopleArrayAdapter(this, android.R.layout.simple_list_item_1, peoples);
        mPeopleListView.setAdapter(adapter);

    }
}
