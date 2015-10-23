package com.google.android.gms.fit.samples.bolt;

/**
 * Created by Mahesh on 10/22/15.
 */
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.fit.samples.basicsensorsapi.R;


//Purpose is to display DB data in listview
public class DataListActivity extends AppCompatActivity {
    //Create Objects
    ListView listview;
    SQLiteDatabase sqLiteDatabase;
    NutritionDbHelper nutritionDbHelper; // to get info
    Cursor cursor;
    ListDataAdapter listDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_list);
        //Initializations
        listview = (ListView)findViewById(R.id.list_view);
        nutritionDbHelper = new NutritionDbHelper(getApplicationContext()); // Important use getAPPContext to initialize
        sqLiteDatabase = nutritionDbHelper.getReadableDatabase();// initialize using nutritiondb object and get readable
        cursor = nutritionDbHelper.getFood(sqLiteDatabase); // get info in form of cursor object
        listDataAdapter = new ListDataAdapter(getApplicationContext(),R.layout.row_layout); //pass the custom layout here
        listview.setAdapter(listDataAdapter); //Set the adapter for listview
        //analyze cursor
        if(cursor.moveToFirst()){ //this returns true in rows are available in cursor object
            // to get each row use do while loop

            do{

                String id,name,cal;
                id = cursor.getString(0);//Specify column index
                name = cursor.getString(1);
                cal = cursor.getString(2);
                DataProvider dataProvider= new DataProvider(id,name,cal);
                // add these objects to Adapter class add method
                listDataAdapter.add(dataProvider); // this will send each row of data in the form of a row into data provider object

            }while (cursor.moveToNext()); //returns true if another row is available

        }






    }
}
