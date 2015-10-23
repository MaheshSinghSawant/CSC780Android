package com.google.android.gms.fit.samples.bolt;

/**
 * Created by Mahesh on 10/22/15.
 */
//To display cursor object rows in listview we need custom adapter
//Purpose  of this class is to provide each row of data in the form of an object
public class DataProvider {

    private String id;
    private String name;
    private String cal;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

//    Create constructor with 3 args

    public DataProvider(String id, String name,String cal){

        //Initialize the strings
        //Pres Right Click and Select Getters and Setters method and select all three

        this.id = id;
        this.name = name;
        this.cal = cal;



    }

}

