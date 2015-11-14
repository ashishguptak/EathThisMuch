package com.example.chkee.mealplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    String[][] m = new String[3][3];
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query ="DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertValues()
    {
        m = new String[][]{{"MCDONALDS", "AMERICAN", "0.3"}, {"PANDA EXPRESS", "CHINESE", "0.4"}, {"SUBWAY", "AMERICAN", "0.3"}};
        db= this.getWritableDatabase();
        for(int i=0;i <3;i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, i);
            values.put(COLUMN_RESTAURANT_NAME, m[i][0]);
            Log.d("REST_NAME","Printing"+m[i][0]);
            values.put(COLUMN_LOCATION,m[i][2]);
            values.put(COLUMN_CUISINE, m[i][1]);
            db.insert(TABLE_NAME, null, values);
        }
    }
}
