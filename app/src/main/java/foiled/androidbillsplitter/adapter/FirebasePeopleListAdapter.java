package foiled.androidbillsplitter.adapter;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

import foiled.androidbillsplitter.R;
import foiled.androidbillsplitter.models.People;
import foiled.androidbillsplitter.util.ItemTouchHelperAdapter;
import foiled.androidbillsplitter.util.OnStartDragListener;

public class FirebasePeopleListAdapter extends FirebaseRecyclerAdapter<People, FirebasePeopleViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private Context mContext;
    private ChildEventListener mChildEventListener;

    private int mOrientation;
    private ArrayList<People> mPeoples = new ArrayList<>();

    public FirebasePeopleListAdapter(FirebaseRecyclerOptions<People> options,
                                     Query ref, OnStartDragListener onStartDragListener, Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mPeoples.add(dataSnapshot.getValue(People.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onBindViewHolder(final FirebasePeopleViewHolder holder, int position, People model) {
        holder.bindPeople(model);
        mOrientation = holder.itemView.getResources().getConfiguration().orientation;
    }

    @Override
    public FirebasePeopleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.people_list_item, parent, false);

        return new FirebasePeopleViewHolder(view);
    }
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mPeoples, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mPeoples.remove(position);
        getRef(position).removeValue();
    }

    public void setIndexInFirebase() {
        for (People people : mPeoples) {
            int index = mPeoples.indexOf(people);
            DatabaseReference ref = getRef(index);
            people.setIndex(Integer.toString(index));
            ref.setValue(people);
        }
    }
}
