package com.example.leobas.weekendassignamentone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import java.util.Date;

import java.sql.SQLException;

/**
 * Created by Leobas on 02/08/2015.
 */
public class DatabaseAdapter {

    static final String DATABASE_NAME = "contacts.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    static final String DATABASE_CREATE = "create table CONTACTS" +
            "( ID integer primary key autoincrement, USERNAME text, PASSWORD text," +
            "NAT_INS_NUM integer, PASSPORT integer, GENRE bit, BIRTHDATE datetime, COUNTRY integer);";

    public SQLiteDatabase db;
    private final Context context;
    private DataBaseHelper dbHelper;

    public DatabaseAdapter(Context _context){
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseAdapter open() throws SQLException{
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){db.close();}

    public SQLiteDatabase getDatabaseInstance(){return db;}

    public void insertEntry(String userName, String password, int nin, int passport,
                            boolean genre, Date birthdate, int country){

        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD", password);
        newValues.put("NAT_INS_NUM", nin);
        newValues.put("PASSPORT", passport);
        newValues.put("GENRE", genre);
        newValues.put("BIRTHDATE", birthdate.getTime());
        newValues.put("COUNTRY", country);

        // Insert the row into your table
        db.insert("CONTACTS", null, newValues);
    }

    public int deleteEntry(String UserName)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("CONTACTS", where, new String[]{UserName}) ;
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public String getSinlgeEntry(String userName)
    {
        Cursor cursor=db.query("CONTACTS", null, " USERNAME=?", new String[]{userName}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public void  updateEntry(String userName, String password, int nin, int passport,
                             boolean genre, Date birthdate, int country)
    {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);
        updatedValues.put("NAT_INS_NUM", nin);
        updatedValues.put("PASSPORT", passport);
        updatedValues.put("GENRE", genre);
        updatedValues.put("BIRTHDATE", birthdate.getTime());
        updatedValues.put("COUNTRY", country);
        String where="USERNAME = ?";
        db.update("LOGIN", updatedValues, where, new String[]{userName});
    }
}
