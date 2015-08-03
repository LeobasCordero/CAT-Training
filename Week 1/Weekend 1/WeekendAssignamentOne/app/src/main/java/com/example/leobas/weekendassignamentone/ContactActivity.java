package com.example.leobas.weekendassignamentone;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Date;
import android.widget.Spinner;

/**
 * Created by Leobas on 02/08/2015.
 */
public class ContactActivity extends Activity implements OnClickListener {

    Button createButton;
    Button cancelButton;
    EditText name;
    DatabaseAdapter dba;
    EditText passport;
    EditText passwordContact;
    EditText passwordContactCon;
    EditText Nin;
    RadioButton male;
    Spinner spinerCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        try {
            // create a instance of SQLite Database
            dba = new DatabaseAdapter(this);
            dba = dba.open();
        }catch(Exception e){
            Log.e("Error", e.getStackTrace().toString());
        }
        //UserFragment userFragment = (UserFragment)getFragmentManager().findFragmentById(R.id.fragmentInfo);
        name = (EditText)findViewById(R.id.nameText);
        passport = (EditText)findViewById(R.id.passport);
        passwordContact = (EditText)findViewById(R.id.passwordUser);
        passwordContactCon = (EditText)findViewById(R.id.passwordUserConfirm);
        Nin = (EditText)findViewById(R.id.nin);
        male = (RadioButton)findViewById(R.id.radioBMale);
        //filling spiner
        spinerCountry = (Spinner)findViewById(R.id.spinnerCountry);
        ArrayAdapter<CharSequence> adapterCountry = ArrayAdapter.createFromResource(
                this, R.array.country_array, android.R.layout.simple_spinner_item);
        //spinerCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerCountry.setAdapter(adapterCountry);
        spinerCountry.setOnItemSelectedListener(new OnItemSelected());

        createButton = (Button)findViewById(R.id.createButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);

        createButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            FragmentManager fm = getFragmentManager();
            boolean genre = false; //means that is female
            if(v == createButton){
                if(!"".equals(name.getText().toString()) && !"".equals(passport) && !"".equals(passwordContact)
                    && !"".equals(Nin.getText().toString()) && !"".equals(passwordContactCon.getText().toString())){
                    if(male.isChecked()){
                        genre = true;
                    }
                    //Inserting the Contact
                    if(passwordContactCon.getText().toString().equals(passwordContact.getText().toString())){
                        dba.insertEntry(name.getText().toString(), passwordContact.getText().toString(),
                                Integer.valueOf(Nin.getText().toString()), Integer.valueOf(passport.getText().toString()),
                                genre, new Date(), spinerCountry.getSelectedItemPosition());
                    }else{
                        Log.e("ErrorContact", "password is not the same");
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                        return;
                    }
                }else{
                    Log.e("ErrorContact", "Fields Required");
                    Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
                    return;
                }
            }else if(v == cancelButton){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Log.e("ErrorContactActivity", "Unexpected Error");
            }
        }catch(Exception e){
            Log.e("Error", e.getStackTrace().toString());
        }
    }
}
