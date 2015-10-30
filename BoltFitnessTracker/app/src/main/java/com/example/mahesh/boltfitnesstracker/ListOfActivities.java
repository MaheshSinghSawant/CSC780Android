package com.example.mahesh.boltfitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListOfActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_activities);
    }


    public void goHome(View v){

        Intent intent = new Intent(this,HomeScreen.class);
        startActivity(intent);

    }
}
