package com.example.mahesh.boltfitnesstracker;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.animation.AnticipateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.DecoDrawEffect;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;

public class NutritionScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    Double calories;
    TextView textView;
    TextView showgoal;
    static Double count= 0.0;
    final float seriesmax = 10000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        textView = (TextView) findViewById(R.id.showcalories);
        showgoal = (TextView) findViewById(R.id.showgoal);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            calories = bundle.getDouble("calories");
            count = count + calories;
            String s = Double.toString(count);
            //textView.setText(Double.toString(count));
            textView.setText(s);


        }

        if(count>10) {

            if(count>10000)
            {
                showgoal.setText("Calorie Goal Reached!");

            }
            else {
                String this_is_string = String.valueOf(count);
                float this_is_float = Float.valueOf(this_is_string);

                DecoView decoView = (DecoView) findViewById(R.id.dynamicArcView);

                SeriesItem seriesItem1 = new SeriesItem.Builder(Color.parseColor("#FFE2E2E2"))
                        .setRange(0, seriesmax, 0)
                        .build();

                int backIndex = decoView.addSeries(seriesItem1);


                final SeriesItem seriesItem = new SeriesItem.Builder(Color.parseColor("#FFFF8800"))
                        .setRange(0, seriesmax, 0)
                        .build();

                int series1Index = decoView.addSeries(seriesItem);


                final TextView textPercentage = (TextView) findViewById(R.id.textPercentage);
                seriesItem.addArcSeriesItemListener(new SeriesItem.SeriesItemListener() {
                    @Override
                    public void onSeriesItemAnimationProgress(float percentComplete, float currentPosition) {
                        float percentFilled = ((currentPosition - seriesItem.getMinValue()) / (seriesItem.getMaxValue() - seriesItem.getMinValue()));
                        textPercentage.setText(String.format("%.0f%%", percentFilled * 100f));
                    }

                    @Override
                    public void onSeriesItemDisplayProgress(float percentComplete) {

                    }
                });

                decoView.addEvent(new DecoEvent.Builder(seriesmax)
                        .setIndex(backIndex)
                        .build());

                decoView.addEvent(new DecoEvent.Builder(this_is_float)
                        .setIndex(series1Index)
                        .setDelay(2000)
                        .build());

            }

        }
    }

    public String obtainSearch()
    {
        EditText editText = (EditText)findViewById(R.id.search);

        return editText.getText().toString();
    }

    public void onSubmit(View view)
    {
        String search = obtainSearch();
        Intent data = new Intent(NutritionScreen.this, TheCaloriePlace.class);
        data.putExtra("search", search);
        startActivity(data);
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
        getMenuInflater().inflate(R.menu.nutrition_screen, menu);
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

        if (id == R.id.nav_home_screen) {
            // Handle the home action
            Intent intent = new Intent(this,HomeScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);

//        } else if (id == R.id.nav_nutrition_screen) {
//            Intent intent = new Intent(this,NutritionScreen.class);
//            startActivity(intent);

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
}
