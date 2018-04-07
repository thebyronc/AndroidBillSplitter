package foiled.androidbillsplitter.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foiled.androidbillsplitter.R;
import foiled.androidbillsplitter.models.People;
import foiled.androidbillsplitter.util.OnPeopleSelectedListener;


/**
 * Created by Sheep on 3/16/2018.
 */

public class PeopleArrayAdapter extends RecyclerView.Adapter<PeopleArrayAdapter.PeopleViewHolder> {
    private Context mContext;
    private ArrayList<People> mPeoples = new ArrayList<>();
    private OnPeopleSelectedListener mOnPeopleSelectedListener;

    public PeopleArrayAdapter(Context context, ArrayList<People> peoples, OnPeopleSelectedListener peopleSelectedListener) {
        mContext = context;
        mPeoples = peoples;
        mOnPeopleSelectedListener = peopleSelectedListener;
    }

    @Override
    public PeopleArrayAdapter.PeopleViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_list_item, parent, false);
        PeopleViewHolder viewHolder = new PeopleViewHolder(view, mPeoples, mOnPeopleSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PeopleArrayAdapter.PeopleViewHolder holder, int position) {
        holder.bindPeople(mPeoples.get(position));
    }

    @Override
    public int getItemCount() {
        return mPeoples.size();
    }

    public class PeopleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.peopleNameTextView) TextView mPeopleNameTextView;
        @BindView(R.id.emailTextView) TextView mEmailTextView;

        private Context mContext;

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            People people = mPeoples.get(itemPosition);

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO,
                        Uri.parse("mailto:" + people.getEmail()));
            mContext.startActivity(emailIntent);

        }

        public PeopleViewHolder(View itemView, ArrayList<People> peoples, OnPeopleSelectedListener peopleSelectedListener ) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mPeoples = peoples;
            mOnPeopleSelectedListener = peopleSelectedListener;
            itemView.setOnClickListener(this);
        }

        public void bindPeople(People people) {
            mPeopleNameTextView.setText(people.getName());
            mEmailTextView.setText(people.getEmail());
        }
    }
}
