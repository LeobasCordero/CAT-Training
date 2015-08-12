package com.android4dev.navigationview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Leobas on 10/08/2015.
 */
public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ListingViewHolder>{

    //Atributes
    private static final String TAG = "ListingAdapter";
    private List<Listing> items;

    public static class ListingViewHolder extends RecyclerView.ViewHolder{
        public TextView categoryTxt, nameTxt, productTxt;

        public ListingViewHolder(View v){
            super(v);
            categoryTxt = (TextView)v.findViewById(R.id.categoryText);
            nameTxt = (TextView)v.findViewById(R.id.nameText);
            productTxt = (TextView)v.findViewById(R.id.productText);
        }
    }

    public ListingAdapter(List<Listing> items) {
        this.items = items;
    }


    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public ListingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        return new ListingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListingViewHolder holder, int position) {
        holder.categoryTxt.setText(items.get(position).getCategoryId());
        holder.nameTxt.setText(items.get(position).getName());
        holder.productTxt.setText(items.get(position).getProductCount());
    }

}
