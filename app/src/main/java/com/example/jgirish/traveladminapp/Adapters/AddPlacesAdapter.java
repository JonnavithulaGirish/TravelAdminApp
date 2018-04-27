package com.example.jgirish.traveladminapp.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jgirish.traveladminapp.R;

import java.util.ArrayList;

public class AddPlacesAdapter extends BaseAdapter {

    ArrayList<String> places;
    Context context;

    public AddPlacesAdapter(ArrayList<String> places,Context context){
        this.places=places;
        this.context=context;
    }

    @Override
    public int getCount() {

        Log.d("testtag", "size: "+places.size());

        return places.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myview = LayoutInflater.from(context).inflate(R.layout.places_item, null);

        TextView txtView=myview.findViewById(R.id.placeName);
        txtView.setText(places.get(i));
        Log.d("testtag", "getView: "+i);

        return myview;
    }
}
