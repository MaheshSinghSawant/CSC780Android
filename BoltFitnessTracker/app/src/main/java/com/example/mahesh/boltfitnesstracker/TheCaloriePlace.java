package com.example.mahesh.boltfitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class TheCaloriePlace extends AppCompatActivity {

    String search;

    ListView listview;
    Response responseobj;
    CustomAdapter adapter;
    String url;
    //"https://api.edamam.com/search?q=%22%20pizza%20%22&from=0&to=10&app_id=e494d87e&app_key=b057bd4f328ed351264e6be95f68ecd1"
    Gson gson;
    AsyncHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_calorie_place);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {

            search = bundle.getString("search");
        }


        listview = (ListView)findViewById(R.id.foodlist);
        client = new AsyncHttpClient();
        client.get(TheCaloriePlace.this, obtainURL(search), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String responsestr = new String(responseBody);
                gson = new Gson();
                responseobj = gson.fromJson(responsestr, Response.class);
                adapter = new CustomAdapter(TheCaloriePlace.this, responseobj.getHits());
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(TheCaloriePlace.this, NutritionScreen.class);
                                Response.HitsEntity.RecipeEntity recipe = responseobj.getHits().get(position).getRecipe();
                                double calories =recipe.getCalories();
                                intent.putExtra("calories",calories);
                                startActivity(intent);
                            }
                        });


            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });



    }
    public String obtainURL( String search) {
        String URL = "https://api.edamam.com/search?q=" + search + "&from=0&to=10&app_id=e494d87e&app_key=b057bd4f328ed351264e6be95f68ecd1";

        return URL;
    }
}
