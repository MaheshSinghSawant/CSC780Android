package com.google.android.gms.fit.samples.bolt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Mahesh on 10/22/15.
 */
/*

Purpose of this class is to perform database operations.

 */
public class NutritionDbHelper extends SQLiteOpenHelper { // extend using SQLiteOpenHelper and then override/implement 2 methods

    //create
    private static final String Database_Name = "Nutrition.DB"; //name for DB
    private static final int Database_Version = 1; // Version

    //Following is string for query WHICH IS HIGHLY ERROR PRONE
    private static final String Create_Query =

            "CREATE TABLE "+ UserContact.Nutrition.Table_Name+"("+ UserContact.Nutrition.Food_ID+" TEXT,"+UserContact.Nutrition.Food_Name+" TEXT,"+
                    UserContact.Nutrition.Calories+" TEXT);";
    //so it is JavaClass.InnerClass.key and datatype is TEXT and put spaces appropriately

    public NutritionDbHelper(Context context){ //Constructor with argument as context

        super(context,Database_Name,null,Database_Version); // super class constructor (context obj, db name, cursor factory object, dbversion)
        //If you create an object of this class with context argument it will create a database.So system checks db version and if there is no change it will not create a new one. But if it is changed it would be created again.

        Log.e("Database Operations", "Database created or Opened");

    }



//The onCreate method is called if the table does not already exist, if operations are performed again then it wont be called.


    @Override
    public void onCreate(SQLiteDatabase db) { //Create a table on call

        db.execSQL(Create_Query); //Execute a Query
        Log.e("Database Operations", "Table Created");

    }

    //Method for insertion of data
    public void addInformation(String id, String name, String cal,SQLiteDatabase db)

    {
        //In SQlite database the data is inserted in the form of map values where keys are represented by column names.
        //For creating map values create an object of Content Values

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserContact.Nutrition.Food_ID,id); // put keyword(key,arg name in addinfo method)
        contentValues.put(UserContact.Nutrition.Food_Name,name);
        contentValues.put(UserContact.Nutrition.Calories,cal);
        //To save data use db.insert
        db.insert(UserContact.Nutrition.Table_Name, null, contentValues);
        Log.e("Database Operations", "1 row inserted");

    }

    // Method to Retrieve information
    // The info is retrieved in the form of a cursor object so return type is
    public Cursor getFood(SQLiteDatabase db){

        Cursor cursor; //Create object of Cursor
        //Projections nothing but needed column names
        String[] projections = {UserContact.Nutrition.Food_ID, UserContact.Nutrition.Food_Name,UserContact.Nutrition.Calories};
        //to retrieve info use method called query which has args(tablename,projections,selection where clause, selection args which are values for where clause ,group rows, filter by row groups, sort order)
        cursor = db.query(UserContact.Nutrition.Table_Name,projections,null,null,null,null,null);
        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
