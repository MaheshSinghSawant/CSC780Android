package com.example.mahesh.boltfitnesstracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private List<Response.HitsEntity> mFooditem;
    private Context context;
    private LayoutInflater inflater;

    public CustomAdapter(Context context, List<Response.HitsEntity> mFooditem) {
        this.context = context;
        this.mFooditem = mFooditem;
    }

    @Override
    public int getCount() {
        return mFooditem.size();
    }

    @Override
    public Object getItem(int position) {
        return mFooditem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = inflater.inflate(R.layout.each_list_item,parent,false);

        Response.HitsEntity item = (Response.HitsEntity) getItem(position);
        TextView title = (TextView) rowview.findViewById(R.id.title);
        TextView cal = (TextView) rowview.findViewById(R.id.cal);
        ImageView thumbnail = (ImageView) rowview.findViewById(R.id.thumbnail);

        double calo =item.getRecipe().getCalories();
        String imageurl = item.getRecipe().getImage();
        Picasso.with(context).load(imageurl).into(thumbnail);
        title.setText((item.getRecipe().getLabel()));
        cal.setText(Double.toString(calo));
        return rowview;
    }
}
