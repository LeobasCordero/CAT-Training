package com.example.mymapsapp;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;


public class MainActivity extends FragmentActivity implements OnClickListener{

    private GoogleMap googleMap;
    private Button bDisplayMap, bSearch, bRequest, bDsplayPan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            initilizeMap();
            bDisplayMap = (Button)findViewById(R.id.button);
            bSearch = (Button)findViewById(R.id.button2);
            bRequest = (Button)findViewById(R.id.button3);
            bDsplayPan = (Button)findViewById(R.id.button4);

            bDisplayMap.setOnClickListener(this);
            bSearch.setOnClickListener(this);
            bRequest.setOnClickListener(this);
            bDsplayPan.setOnClickListener(this);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View arg0) {
        Intent intent;
        Uri gmmIntentUri = null;

        if (arg0 == bDisplayMap) {
            //intent = new Intent(this,DisplayMap.class);
            // Create a Uri from an intent string. Use the result to create an Intent.
            gmmIntentUri = Uri.parse("geo:37.7749,-122.4194");
        }else if(arg0 == bSearch){
            gmmIntentUri = Uri.parse("geo:0,0?q=restaurants");
        }else if(arg0 == bRequest){
            gmmIntentUri = Uri.parse("google.navigation:q=Taronga+Zoo,+Sydney+Australia");
        }else if(arg0 == bDsplayPan){
            gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        }

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        intent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        intent.setPackage("com.google.android.apps.maps");

        if(intent.resolveActivity(getPackageManager()) != null) {
            // Attempt to start an activity that can handle the Intent
            startActivity(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    private void initilizeMap() {
        if (googleMap == null) {

            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
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
