package com.example.mahesh.boltfitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Mahesh on 11/18/15.
 */
public class HistoryListAdapter extends BaseAdapter {

    private Context context;

    public HistoryListAdapter(Context context) {

        this.context = context;

    }

    @Override
    public int getCount() {
        return 5;
        // hardcoded to 5, but will probably be number of days in history

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = inflater.inflate(R.layout.each_list_item_history,parent,false);


        // code for rowview : one day of history in this case it just says hi.
        //but you can add the function to get the history data
        //say if you want to add steps on that day make corresponding view in each_list_item_history and add its here with function call

        TextView time_item = (TextView) rowview.findViewById(R.id.time_item);
        time_item.setText("Duration: ");

        TextView distance_item = (TextView) rowview.findViewById(R.id.distance_item);
        distance_item.setText("Distance Covered:");

        TextView calories_item = (TextView) rowview.findViewById(R.id.calories_item);
        calories_item.setText("Calories Expended");

        return rowview;
    }
}
