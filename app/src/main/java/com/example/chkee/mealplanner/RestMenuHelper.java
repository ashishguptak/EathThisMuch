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
    private static final String COLUMN_PRICE= "price";

    SQLiteDatabase db;
    String[][] m = new String[8][5];

    private static final String TABLE_CREATE = "create table restmenu (id integer primary key not null , " +
            "name text not null , dishname text not null , type text not null , calories text not null , price text not null);";

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
        db = this.getWritableDatabase();
        m = new String[][]{{"SUBWAY","SWEET ONION CHICKEN TERIYAKI","MAIN COURSE","370","7.5"}, {"SUBWAY","FUZE GREEN TEA","APPETIZERS","140","2"},
                {"SUBWAY","OATMEAL RAISIN", "EXTRAS","200","1.5"},{"PANDA EXPRESS","ASIAN GRILLED CHICKEN","MAIN COURSE","300","6.8"},{"PANDA EXPRESS","CHICKEN EGG ROLL","APPETIZERS","200","4"},
                {"PANDA EXPRESS","SWEETFIRE CHICKEN BREAST","MAIN COURSE ","380","4"},{"MCDONALDS","BACON CLUB HOUSE BURGER","MAIN COURSE","720","4.53"},
                {"MCDONALDS","BIGMAC MEAL","MAIN COURSE","530","6.05"}  };
        for(int i=0;i<8;i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, i);
            values.put(COLUMN_RESTAURANT_NAME,m[i][0]);
            values.put(COLUMN_CALORIES, m[i][3]);
            values.put(COLUMN_DISH_NAME, m[i][1]);
            values.put(COLUMN_TYPE, m[i][2]);
            values.put(COLUMN_PRICE, m[i][4]);
            db.insert(TABLE_NAME, null, values);
        }
        //   db= this.getWritableDatabase();
        // ContentValues values1= new ContentValues();
        //values1.put(COLUMN_ID,1);
        //values1.put(COLUMN_RESTAURANT_NAME,"PANDA EXPRESS");
        //values1.put(COLUMN_LOCATION,"40.004211,-83.008522");
        //values1.put(COLUMN_CUISINE,"CHINESE");
        //db.insert(TABLE_NAME, null, values1);
    }
}
