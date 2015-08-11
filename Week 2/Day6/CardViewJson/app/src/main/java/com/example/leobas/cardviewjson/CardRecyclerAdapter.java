package com.example.leobas.cardviewjson;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Leobas on 04/08/2015.
 */
public class CardRecyclerAdapter extends  RecyclerView.Adapter<CardRecyclerAdapter.MyViewHolder> {

    private ArrayList<Contact> contacts = new ArrayList<Contact>();

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;

        public String versionName;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.contact);
        }
    }

    public CardRecyclerAdapter(ArrayList<Contact> people) {
        this.contacts = people;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_layout, parent, false);
//Click event
        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;

        StringBuilder sb = new StringBuilder();
        sb.append("id: " + contacts.get(listPosition).getId());
        sb.append("first name: " + contacts.get(listPosition).getFirst_name());
        sb.append("surname: " + contacts.get(listPosition).getSurname());
        sb.append("address: " + contacts.get(listPosition).getAddress());
        sb.append("phone: " + contacts.get(listPosition).getPhone_number());
        sb.append("email: " + contacts.get(listPosition).getEmail());
        sb.append("date created: " + contacts.get(listPosition).getCreatedAt());
        sb.append("date updated: " + contacts.get(listPosition).getUpdatedAt());
        Log.i("contact", sb.toString());
        textViewName.setText(sb);
        holder.versionName=textViewName.getText().toString();
    }

    @Override
    public int getItemCount() {
        contacts.add(new Contact());
        return contacts.size();
    }

}
