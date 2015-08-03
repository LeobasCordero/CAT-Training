package com.example.leobas.weekendassignamentone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    Button buttonLogin;
    EditText usernameText;
    EditText passwordText;
    Spinner spinerOption;
    CheckBox checkPassword;
    CheckBox checkRemember;
    SharedPreferences sharedPass;
    DatabaseAdapter dba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            // create a instance of SQLite Database
            dba = new DatabaseAdapter(this);
            dba = dba.open();
        }catch(Exception e){
            Log.e("Error", e.getStackTrace().toString());
        }
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        usernameText = (EditText)findViewById(R.id.username);
        passwordText = (EditText)findViewById(R.id.password);
        //filling spiner
        spinerOption = (Spinner)findViewById(R.id.spinnerLogin);
        ArrayAdapter<CharSequence> adapterLogin = ArrayAdapter.createFromResource(
                this, R.array.options_array, android.R.layout.simple_spinner_item);
        adapterLogin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinerOption.setAdapter(adapterLogin);
        spinerOption.setOnItemSelectedListener(new OnItemSelected());

        buttonLogin.setOnClickListener(this);
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

    //Decide on which Activity to continue based on the provided data
    @Override
    public void onClick(View v) {
        try{
            // get The User name and Password
            String userName = usernameText.getText().toString();
            String password = passwordText.getText().toString();

            String storedPassword = dba.getSinlgeEntry(userName);

            if(v == buttonLogin){
                Log.i("Login", "Validating values");
                if((userName != null && !"".equals(userName) || userName == "admin")){
                    if(storedPassword != null && !"".equals(storedPassword) &&
                            password != null && !"".equals(password)){
                        Intent intent = null;
                        if(password.equals(storedPassword) || password == "admin") {
                            Log.i("Login", spinerOption.getSelectedItem().toString());
                            if ("User".equals(spinerOption.getSelectedItem().toString())) {
                                intent = new Intent(this, ContactActivity.class);
                                //intent.putExtra("username",username.getText().toString());
                            } else if ("Manager".equals(spinerOption.getSelectedItem().toString())) {
                                intent = new Intent(this, ContactAdminActivity.class);
                            } else {
                                Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                                Log.e("ErrorLogin", "Unexpected Error Spiner");
                            }
                        }
                        startActivity(intent);
                    }else{
                        Log.e("ErrorLogin", "Password invalid");
                    }
                }else{
                    Log.e("ErrorLogin", "Username invalid");
                }
            }
        }catch(Exception e){
            Log.e("Exception", e.toString());
        }
    }

    public void CheckBoxSelection(View v){
        checkPassword = (CheckBox)findViewById(R.id.checkBoxPassword);
        checkRemember = (CheckBox)findViewById(R.id.checkBoxRemember);

        if(checkPassword.isChecked()){
            Log.i("Login", "Show Password activated");
        }
        if(checkRemember.isChecked()){
            Log.i("Login", "Remember Data activated");
            sharedPass = getSharedPreferences(
                    "applicationPreferences", MODE_WORLD_READABLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close The Database
        dba.close();
    }
}
