package com.example.leobas.cardviewjson;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.widget.RecyclerView;

import android.app.Activity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONException;



public class MainActivity extends Activity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Contact> contacts;
    static View.OnClickListener myOnClickListener;
    //URL to get JSON Array
    private static String url = "http://fast-gorge.herokuapp.com/contacts";

    //JSON Node Names
    private static final String TAG_NAME = "first_name";
    private static final String TAG_SURNAME = "surname";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_PHONE = "phone_number";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ID = "id";
    private static final String TAG_DATE_CREATE = "createAt";
    private static final String TAG_DATE_UPDATE = "updateAt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue queue = Volley.newRequestQueue(this);

        //STEP 1: Initialize the RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_container);
        recyclerView.setHasFixedSize(true);

        //STEP 2: Set the Layout
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //STEP 3: Set the Animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Retrieve the info
        JSONObjectRequest jr = new JSONObjectRequest(Request.Method.GET,url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    Contact contact = new Contact();
                    contact.setId(response.getString(TAG_ID));
                    contact.setAddress(response.getString(TAG_ADDRESS));
                    contact.setEmail(response.getString(TAG_EMAIL));
                    contact.setFirst_name(response.getString(TAG_NAME));
                    contact.setSurname(response.getString(TAG_SURNAME));
                    contact.setPhone_number(response.getString(TAG_PHONE));
                    contact.setCreatedAt(response.getString(TAG_DATE_CREATE));
                    contact.setUpdatedAt(response.getString(TAG_DATE_UPDATE));
                    contacts.add(contact);
                }
                catch(JSONException ex)
                {
                    Log.e("error", ex.getStackTrace().toString());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error", error.getStackTrace().toString());
            }
        });


        //STEP 4: Set the Adapter
        adapter = new CardRecyclerAdapter(contacts);
        recyclerView.setAdapter(adapter);

        queue.add(jr);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
