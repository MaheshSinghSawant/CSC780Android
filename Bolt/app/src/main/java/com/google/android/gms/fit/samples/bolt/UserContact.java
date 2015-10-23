package com.google.android.gms.fit.samples.bolt;

/**
 * Created by Mahesh on 10/22/15.
 */
public class UserContact {


    /*
    Purpose of this class is that it acts as a container for all constants to be used, so making changes in the database later is easier.
    If you need more than one table then you need to create separate static abstract inner classes in the contract class for each table.
     */
    //This is the Table information
    public static abstract class Nutrition{

        //The column names (which are essentially constants we will be using. Have to be public static final)
        public static final String Food_Name = "food_name"; // Food_Name is key and "food_name" is column name in db
        public static final String Food_ID = "food_id";
        public static final String Calories = "calories";
        public static final String Table_Name = "Nutrition";//The table name

    }
}
