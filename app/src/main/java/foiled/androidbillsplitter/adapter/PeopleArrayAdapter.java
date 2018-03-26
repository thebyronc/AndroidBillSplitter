package foiled.androidbillsplitter.adapter;

import android.content.Context;
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

/**
 * Created by Sheep on 3/16/2018.
 */

public class PeopleArrayAdapter extends RecyclerView.Adapter<PeopleArrayAdapter.PeopleViewHolder> {
    private Context mContext;
    private ArrayList<People> mPeoples = new ArrayList<>();

    public PeopleArrayAdapter(Context mContext, int resource, ArrayList<People> mPeoples) {
        mContext = mContext;
        mPeoples = mPeoples;
    }

    @Override
    public PeopleArrayAdapter.PeopleViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.people_list_item, parent, false);
        PeopleViewHolder viewHolder = new PeopleViewHolder(view);
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

    public class PeopleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.peopleNameTextView) TextView mPeopleNameTextView;
        @BindView(R.id.emailTextView) TextView mEmailTextView;

        public PeopleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindPeople(People people) {
            mPeopleNameTextView.setText(people.getName());
            mEmailTextView.setText(people.getEmail());
        }
    }
}
