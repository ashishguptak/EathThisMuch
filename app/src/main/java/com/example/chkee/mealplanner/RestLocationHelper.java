package com.example.chkee.mealplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chkee on 11/14/2015.
 */
public class RestLocationHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION =9;
    private static final String DATABASE_NAME ="restlocations.db";
    private static final String TABLE_NAME1 ="restlocations";
    private static final String COLUMN_ID1="id";
    private static final String COLUMN_RESTAURANT_NAME1="name";
    private static final String COLUMN_CUISINE1="cuisine";
    private static final String COLUMN_LOCATION1 ="location";

    private static final String TABLE_NAME2 ="restmenu";
    private static final String COLUMN_ID2="id";
    private static final String COLUMN_RESTAURANT_NAME2="name";
    private static final String COLUMN_DISH_NAME2="dishname";
    private static final String COLUMN_TYPE2="type";
    private static final String COLUMN_CALORIES2="calories";
    private static final String COLUMN_PRICE2= "price";

    String[][] m = new String[3][3];
    String[][] n = new String[8][5];

    SQLiteDatabase db;

    private static final String TABLE_CREATE1 = "create table restlocations (id integer primary key not null , " +
            "name text not null , cuisine text not null , location text not null);";
    private static final String TABLE_CREATE2= "create table restmenu (id integer primary key not null , " +
            "name text not null , dishname text not null , type text not null , calories text not null , price text not null);";

    private String mealsRestQuery = "select * from restmenu rm , restlocations rl where rm.name = rl.name " +
            "and rl.location <= ? and rl.cuisine = ?";

    public List<RestMenu> getValues(int dist,String cuisine) {
        db = this.getWritableDatabase();
        int i=0;
        Cursor cursor = db.rawQuery(mealsRestQuery, new String[]{String.valueOf(dist), cuisine});
        List<RestMenu> result = new ArrayList<RestMenu>();
        if (cursor.moveToFirst()) {
            do {
               // Log.d("columns","columns"+cursor.getColumnCount());
               // Log.d ("Row","row"+i);
                //i++;
               RestMenu c = new RestMenu();
                c.setId(cursor.getString(0));
                c.setRestaurantName(cursor.getString(1));
                c.setDishName(cursor.getString(2));
                c.setType(cursor.getString(3));
                c.setCalories(cursor.getString(4));
                c.setPrice(cursor.getString(5));
                c.setCuisine(cursor.getString(8));
                c.setLocation(cursor.getString(9));
                result.add(c);
            } while (cursor.moveToNext());
        }
        Log.d("value","value" +(Arrays.toString(result.toArray())));
        return result;
    }


    public RestLocationHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE1);
        db.execSQL(TABLE_CREATE2);
        this.db=db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query1 ="DROP TABLE IF EXISTS "+TABLE_NAME1;
        db.execSQL(query1);
        String query2 = "DROP TABLE IF EXISTS "+TABLE_NAME2;
        db.execSQL(query2);
        this.onCreate(db);
    }

    public void insertValues()
    {
        db = this.getWritableDatabase();
        m = new String[][]{{"MCDONALDS", "AMERICAN", "0.3"}, {"PANDA EXPRESS", "CHINESE", "0.4"}, {"SUBWAY", "AMERICAN", "0.3"}};
        Cursor cursor = null;
        for(int i=0;i <3;i++) {
            ContentValues values = new ContentValues();
            String query = "select * from restlocations";
            cursor=db.rawQuery(query,null);
            int count=cursor.getCount();
            Log.d("table1","table1"+count);
            values.put(COLUMN_ID1, count);
            values.put(COLUMN_RESTAURANT_NAME1, m[i][0]);
            Log.d("REST_NAME","Printing"+m[i][0]);
            values.put(COLUMN_LOCATION1,m[i][2]);
            values.put(COLUMN_CUISINE1, m[i][1]);
            db.insert(TABLE_NAME1, null, values);
        }

        db = this.getWritableDatabase();
        Cursor cursor1 = null;
        n = new String[][]{{"SUBWAY","SWEET ONION CHICKEN TERIYAKI","MAIN COURSE","370","7.5"}, {"SUBWAY","FUZE GREEN TEA","APPETIZERS","140","2"},
                {"SUBWAY","OATMEAL RAISIN", "EXTRAS","200","1.5"},{"PANDA EXPRESS","ASIAN GRILLED CHICKEN","MAIN COURSE","300","6.8"},{"PANDA EXPRESS","CHICKEN EGG ROLL","APPETIZERS","200","4"},
                {"PANDA EXPRESS","SWEETFIRE CHICKEN BREAST","MAIN COURSE ","380","4"},{"MCDONALDS","BACON CLUB HOUSE BURGER","MAIN COURSE","720","4.53"},
                {"MCDONALDS","BIGMAC MEAL","MAIN COURSE","530","6.05"}  };
        for(int i=0;i<8;i++) {
            ContentValues values = new ContentValues();
            String query = "select * from restmenu";
            cursor1=db.rawQuery(query,null);
            int count=cursor1.getCount();
            Log.d("table2","table2"+count);
            values.put(COLUMN_ID2, count);
            values.put(COLUMN_RESTAURANT_NAME2,n[i][0]);
            values.put(COLUMN_CALORIES2, n[i][3]);
            values.put(COLUMN_DISH_NAME2, n[i][1]);
            values.put(COLUMN_TYPE2, n[i][2]);
            values.put(COLUMN_PRICE2, n[i][4]);
            db.insert(TABLE_NAME2, null, values);
        }
    }
}
