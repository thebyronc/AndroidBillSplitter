package foiled.androidbillsplitter.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import foiled.androidbillsplitter.Constants;
import foiled.androidbillsplitter.R;
import foiled.androidbillsplitter.models.People;

public class FirebasePeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
    private ArrayList<People> peoples = new ArrayList<>();
    public ImageView mMovieImageView;
    View mView;
    Context mContext;

    public FirebasePeopleViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindPeople(People people) {
        TextView mPeopleNameTextView = (TextView) mView.findViewById(R.id.peopleNameTextView);
        TextView mEmailTextView = (TextView) mView.findViewById(R.id.emailTextView);
        mMovieImageView = (ImageView) mView.findViewById(R.id.movieImageView);

        mPeopleNameTextView.setText(people.getName());
        mEmailTextView.setText(people.getEmail());

    }
    @Override
    public void onClick(View view) {
        final ArrayList<People> peoples = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_PEOPLE).child(uid);

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    peoples.add(snapshot.getValue(People.class));
                }
                int itemPosition = getLayoutPosition();
                People people = peoples.get(itemPosition);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.parse("mailto:" + people.getEmail()));
                mContext.startActivity(emailIntent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
