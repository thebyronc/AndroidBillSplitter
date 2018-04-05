package foiled.androidbillsplitter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import foiled.androidbillsplitter.models.Bill;

public class FirebaseBillViewHolder extends RecyclerView.ViewHolder {
    private ArrayList<Bill> bills = new ArrayList<>();
    View mView;
    Context mContext;

    public FirebaseBillViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindBill(Bill bill) {
        
    }
}
