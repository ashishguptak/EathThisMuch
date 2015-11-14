package com.example.chkee.mealplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chkee on 11/14/2015.
 */
public class RestLocationHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="restlocations.db";
    private static final String TABLE_NAME ="restlocations";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_RESTAURANT_NAME="name";
    private static final String COLUMN_CUISINE="cuisine";
    private static final String COLUMN_LOCATION="location";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table restlocations (id integer primary key not null , " +
            "name text not null , cuisine text not null , location text not null);";

    public RestLocationHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
        insertValues();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query ="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertValues()
    {
        db= this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,1);
        values.put(COLUMN_RESTAURANT_NAME,"CHIPOTLE");
        values.put(COLUMN_LOCATION,"40.001321,-83.007512");
        values.put(COLUMN_CUISINE,"MEXICAN");
        db.insert(TABLE_NAME, null, values);
     //   db= this.getWritableDatabase();
       // ContentValues values1= new ContentValues();
        //values1.put(COLUMN_ID,1);
        //values1.put(COLUMN_RESTAURANT_NAME,"PANDA EXPRESS");
        //values1.put(COLUMN_LOCATION,"40.004211,-83.008522");
        //values1.put(COLUMN_CUISINE,"CHINESE");
        //db.insert(TABLE_NAME, null, values1);


    }
}
