package com.example.mahesh.boltfitnesstracker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import mehdi.sakout.fancybuttons.FancyButton;

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SensorEventListener,
        StepListener, View.OnClickListener {


    private TextView textView;
    private SimpleStepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private static final String TEXT_NUM_STEPS = " ";
    private int numSteps;
    private Chronometer chronometer;
    TextView chronometerView;
    private boolean isBR = false;
    private TextView displayTime;
    private TextView userName;
    private TextView calories_burnt;

    private FancyButton playButton;
    private FancyButton pauseButton;
    private FancyButton stopButton;

    private static final String play = "PLAY";
    private static final String pause = "PAUSE";
    private static final String stop = "STOP";

    private long timeElapsed;
    private int calories;
    private boolean isPaused = false;
    private boolean isCounting = false;
    private boolean isStopped = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        playButton =  (FancyButton) findViewById(R.id.play_timer);
        pauseButton = (FancyButton) findViewById(R.id.pause_timer);
        stopButton =  (FancyButton) findViewById(R.id.stop_timer);

        playButton.setTag(play);
        pauseButton.setTag(pause);
        stopButton.setTag(stop);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);


        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        stopButton.setVisibility(View.VISIBLE);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        findViewById(R.id.play_timer).setOnClickListener(this);
//        findViewById(R.id.stop_timer).setOnClickListener(this);
//        chronometer = (Chronometer) findViewById(R.id.chronometer);
//        Typeface myTypeface = Typeface.createFromAsset(this.getAssets(),"DS-DIGI.TTF");
//        chronometerView = (TextView) findViewById(R.id.chronometer);
//        chronometerView.setTypeface(myTypeface);

        displayTime = (TextView) findViewById(R.id.display_time);
        Typeface myTypeface = Typeface.createFromAsset(this.getAssets(), "DS-DIGI.TTF");
        displayTime.setTypeface(myTypeface);

        userName = (TextView) findViewById(R.id.username);
        String text = getIntent().getStringExtra("Name");
        //userName.setText("Welcome, "+text);

        calories_burnt = (TextView) findViewById(R.id.kcal);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        textView = (TextView) findViewById(R.id.step_count);
        textView.setTextSize(30);
//        setContentView(textView);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new SimpleStepDetector();
        simpleStepDetector.registerListener(this);

    }


    public void startTimer(){
        Intent stepCounterIntent =  new Intent(this, StepCounterService.class);
        if(isPaused){
            stepCounterIntent.putExtra("pause_time", timeElapsed);
        }
        startService(stepCounterIntent);
        if(!isBR){
            registerReceiver(broadcastReceiver, new IntentFilter(StepCounterService.BROADCAST_ACTION));
            isBR = true;
        }

        playButton.setVisibility(View.GONE);
        pauseButton.setVisibility(View.VISIBLE);
        isCounting = true;
        isPaused = false;
        isStopped = false;

    }

    public void pauseTimer(){
        stopService(new Intent(this, StepCounterService.class));
        if(isBR){
            unregisterReceiver(broadcastReceiver);
//            stopService(new Intent(this, StepCounterService.class));
            isBR = false;
        }
        isPaused = true;
        isCounting = true;
        isStopped = false;
        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);

    }

    public void stopTimer(){
        stopService(new Intent(this, StepCounterService.class));
        if(isBR){
            unregisterReceiver(broadcastReceiver);
            isBR = false;
        }
        isPaused = false;
        isCounting = false;

        playButton.setVisibility(View.VISIBLE);
        pauseButton.setVisibility(View.GONE);
        if(isStopped){
            timeElapsed = 0;
            displayTime.setText("00:00:00");
            isStopped = false;
        } else {
            isStopped = true ;
        }


    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            timeElapsed = intent.getLongExtra("elapsed_time", (long)0);
            displayTime.setText(formatTime(timeElapsed));
            calories = intent.getIntExtra("calories_burnt", (int) 0);
            calories_burnt.setText(String.valueOf(calories));
//            Log.d("Counter", intent.getStringExtra("counter"));
        }
    };

    private String formatTime(Long timeElapsed){

        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(timeElapsed),
                TimeUnit.MILLISECONDS.toMinutes(timeElapsed),
                TimeUnit.MILLISECONDS.toSeconds(timeElapsed)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeElapsed)) );
    }

    public void showActivities(View v){

        Intent intent = new Intent(this,ListOfActivities.class);
        startActivity(intent);

    }



    public void goToMap(View v){

        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);

    }






    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_home_screen) {
//            // Handle the home action
//            Intent intent = new Intent(this,HomeScreen.class);
//            startActivity(intent);

        if (id == R.id.nav_nutrition_screen) {
            Intent intent = new Intent(this,NutritionScreen.class);
            //set proper flags

            startActivity(intent);

        } else if (id == R.id.nav_history_screen) {
            Intent intent = new Intent(this,HistoryScreen.class);
            startActivity(intent);

        } else if (id == R.id.nav_statistics_screen) {
            Intent intent = new Intent(this,StatisticsScreen.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        numSteps = 0;
        textView.setText(numSteps + TEXT_NUM_STEPS);
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
        //resume br listener


        if(isPaused) {
            playButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.GONE);
        }

        if(isCounting) {
            displayTime.setText(formatTime(timeElapsed));
//            playButton.setVisibility(View.GONE);
//            pauseButton.setVisibility(View.VISIBLE);
        } else {
            timeElapsed = 0;
            displayTime.setText(formatTime(timeElapsed));
        }

        if(!isBR && !isPaused){
            registerReceiver(broadcastReceiver, new IntentFilter(StepCounterService.BROADCAST_ACTION));
            isBR = true;
        }

    }

    @Override
    public void onClick(View v){
        switch(v.getTag().toString()){
            case "PLAY":
                startTimer();
                break;
            case "PAUSE":
                pauseTimer();
                break;
            case "STOP":
                stopTimer();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
        if(isBR) {
            unregisterReceiver(broadcastReceiver);
            isBR = false;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        textView.setText(numSteps + TEXT_NUM_STEPS );
    }
}
