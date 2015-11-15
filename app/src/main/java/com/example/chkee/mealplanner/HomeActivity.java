package com.example.chkee.mealplanner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    RestLocationHelper restLocationHelper = new RestLocationHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        restLocationHelper.insertValues();
        Log.d("calling", "calling");
        restLocationHelper.getValues(1, "AMERICAN");
        Log.d("Called", "Called");
        startActivity(new Intent(this, MapsActivity.class));
       // Uri gmmIntentUri = Uri.parse("geo:0,0?q=restaurants");
        //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        //mapIntent.setPackage("com.google.android.apps.maps");
        //startActivity(mapIntent);
    }
}
