package com.google.android.gms.fit.samples.bolt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.fit.samples.basicsensorsapi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahesh on 10/17/15.
 */

//This is Custom Adapter
//Extend using
public class ListDataAdapter extends ArrayAdapter {

    List list = new ArrayList();

    //You need to add constructor for this class
    public ListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    //Change from textview to Progress bar
    // if row is empty we might have to uselayout handler
    static class LayoutHandler{

        TextView NAME,ID,CAL;

    }

    //add an add override method
    @Override
    public void add(Object object)
    {
        super.add(object);
        list.add(object);
    }

    //Pass number of items in list
    @Override
    public int getCount(){
        return list.size();

    }

    //Need to pass each of the object in current position
    @Override
    public Object getItem(int postion){

        return list.get(postion);
    }

    //Return each row of data

    public View getView(int position, View convertView, ViewGroup parent){

        View row = convertView;
        LayoutHandler layoutHandler;
        //check whether row is available
        if(row==null){
            //we need to inflate layour resource file
            LayoutInflater layoutInflater= (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.NAME = (TextView)row.findViewById(R.id.text_food_name);
            layoutHandler.ID = (TextView)row.findViewById(R.id.text_food_id);
            layoutHandler.CAL = (TextView)row.findViewById(R.id.text_food_cal);
            //Addd this row into the layout
            row.setTag(layoutHandler);




        }
        else{

            layoutHandler = (LayoutHandler)row.getTag();


        }

        //need obj of data provider
        DataProvider dataProvider = (DataProvider)this.getItem(position);
        layoutHandler.NAME.setText(dataProvider.getName());
        layoutHandler.ID.setText(dataProvider.getId());
        layoutHandler.CAL.setText(dataProvider.getCal());



        return row;
    }
}
