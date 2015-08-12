package com.android4dev.navigationview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Activity {

    /*@Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycler_layout,container,false);
        v = showText(v);
        return v;
    }*/

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String url = "https://dl.dropboxusercontent.com/u/1559445/ASOS/SampleApi/cats_women.json";
    JSONArray listingArray = null;
    List<Listing> items = new ArrayList<>();
    private String TAG = "ContentFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fragment);

        RequestQueue rq = Volley.newRequestQueue(this);
        //items.add(new Listing("1","algo","3"));
        //items.add(new Listing("6","algo4","2"));

        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    // Getting JSON Array node
                    //Log.i(TAG, response.toString());
                    JSONObject x = response;
                    listingArray = x.getJSONArray("Listing");

                    for (int i = 0; i < listingArray.length(); i++) {
                        JSONObject c = listingArray.getJSONObject(i);

                        Listing listing = new Listing();
                        listing.setCategoryId(c.getString("CategoryId"));
                        Log.i(TAG, listing.getCategoryId());
                        listing.setName(c.getString("Name"));
                        Log.i(TAG, listing.getName());
                        listing.setProductCount(c.getString("ProductCount"));
                        Log.i(TAG, listing.getProductCount());

                        items.add(listing);
                    }

                } catch (JSONException ex) {
                    //Do nothing
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO Auto-generated method stub

            }
        });
        rq.add(jr);

        recycler = (RecyclerView)findViewById(R.id.recycler);
        recycler.hasFixedSize();
        recycler.setItemAnimator(new DefaultItemAnimator());

        layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        adapter = new ListingAdapter(items);
        recycler.setAdapter(adapter);
    }

}
