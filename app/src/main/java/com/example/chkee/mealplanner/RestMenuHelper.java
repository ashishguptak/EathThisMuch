package com.example.chkee.mealplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chkee on 11/14/2015.
 */
public class RestMenuHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME ="restmenu.db";
    private static final String TABLE_NAME ="restmenu";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_RESTAURANT_NAME="name";
    private static final String COLUMN_DISH_NAME="dishname";
    private static final String COLUMN_TYPE="type";
    private static final String COLUMN_CALORIES="calories";

    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table restmenu (id integer primary key not null , " +
            "name text not null , dishname text not null , type text not null , calories text not null);";

    public RestMenuHelper(Context context)
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
        values.put(COLUMN_CALORIES,"220");
        values.put(COLUMN_DISH_NAME,"CARNITAS");
        values.put(COLUMN_TYPE,"LUNCH");
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
