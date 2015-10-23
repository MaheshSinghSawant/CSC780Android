package com.google.android.gms.fit.samples.bolt;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.fit.samples.basicsensorsapi.R;

public class Nutrition extends AppCompatActivity {


    private ImageView imageView;
    private AnimatedVectorDrawable emptyGlass;
    private AnimatedVectorDrawable fillGlass;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private boolean full = false;
    Cursor cursor;
    EditText Id, Name, Calories;
    Context context = this;
    //object of NutritionDbHelper and SQLiteDatabase required
    NutritionDbHelper nutritionDbHelper;
    SQLiteDatabase sqlLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        imageView = (ImageView) findViewById(R.id.image_view);
        emptyGlass = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_glass_empty);
        fillGlass = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_glass_fill);

        Id = (EditText) findViewById(R.id.Food_id);
        Name = (EditText) findViewById(R.id.Food_name);
        Calories = (EditText) findViewById(R.id.Food_calories);

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

        NavigationView nv = (NavigationView) findViewById(R.id.navi);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.item_home:
                    {
                        Intent a= new Intent(Nutrition.this, Home.class);
                        startActivity(a);
                        break;
                    }

                    case R.id.item_nutrition:
                    {
                        Intent b= new Intent(Nutrition.this, Nutrition.class);
                        startActivity(b);
                        break;
                    }

                    case R.id.item_statistics:
                    {
                        Intent c= new Intent(Nutrition.this, Statistics.class);
                        startActivity(c);
                        break;
                    }

                    case R.id.item_history:
                    {
                        Intent d= new Intent(Nutrition.this, History.class);
                        startActivity(d);
                        break;
                    }

                    default:


                }
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    // Hamburger icon and sync with drawer
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    //Define the method to add food
    public void addFood(View view){


        //First get all info from Textfields
        String name = Name.getText().toString();
        String id = Id.getText().toString();
        String cal = Calories.getText().toString();
        // Then initialize the nutritionDbHelper object by passing context object
        nutritionDbHelper = new NutritionDbHelper(context);
        // Initialize the sqlLiteDatabase to write
        sqlLiteDatabase = nutritionDbHelper.getWritableDatabase();
        //Call the add addInformation method
        nutritionDbHelper.addInformation(id,name,cal,sqlLiteDatabase);
        Toast.makeText(getBaseContext(),"Data Saved", Toast.LENGTH_LONG).show();
        nutritionDbHelper.close(); //close db
    }


    public void viewFood(View view){

        Intent intent = new Intent(this,DataListActivity.class);
        startActivity(intent);


    }

    public void animate(View view) {
        AnimatedVectorDrawable drawable = full ? emptyGlass : fillGlass;
        imageView.setImageDrawable(drawable);
        drawable.start();
        full = !full;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nutrition, menu);
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
}
