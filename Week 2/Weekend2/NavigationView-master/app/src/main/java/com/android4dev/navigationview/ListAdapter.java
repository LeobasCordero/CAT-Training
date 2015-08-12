package com.android4dev.navigationview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by Leobas on 11/08/2015.
 */
public class ListAdapter extends ArrayAdapter {

    private String url = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/cats_women.json";
    private static String TAG = "ListAdapter";
    List<Listing> items;

    public ListAdapter(Context context){
        super(context,0);
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItemView = convertView;

        if(null == convertView){

        }

        return super.getView(position, convertView, parent);
    }
}
