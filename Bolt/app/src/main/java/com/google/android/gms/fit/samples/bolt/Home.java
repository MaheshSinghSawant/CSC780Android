package com.google.android.gms.fit.samples.bolt;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.fit.samples.basicsensorsapi.R;

public class Home extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    static final int REQUEST_IMAGE_CAPTURE = 1;

    ImageButton play;
    ImageButton pause;
    ImageButton stop;
    private Chronometer chronometer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.play_timer).setOnClickListener(this);
        findViewById(R.id.stop_timer).setOnClickListener(this);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
// Instantiate toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtain DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Enable Drawer toggle actions
        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.drawer_opened,
                R.string.drawer_closed);
        drawerLayout.setDrawerListener(drawerToggle);

        NavigationView navi = (NavigationView) findViewById(R.id.navi);
        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    default:
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    public void showMap(View view)
    {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

    public void showGoogleFitActivity(View view)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
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

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.play_timer:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.stop_timer:
                chronometer.stop();
                break;
        }
    }

}
