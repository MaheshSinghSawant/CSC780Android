package com.google.android.gms.fit.samples.bolt;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.fit.samples.basicsensorsapi.R;

public class Nutrition extends AppCompatActivity {


    private ImageView imageView;
    private AnimatedVectorDrawable emptyGlass;
    private AnimatedVectorDrawable fillGlass;
    private boolean full = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        imageView = (ImageView) findViewById(R.id.image_view);
        emptyGlass = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_glass_empty);
        fillGlass = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_glass_fill);
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
