package com.example.jgirish.traveladminapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.jgirish.traveladminapp.R;

/**
 * Created by j.girish on 27-04-2018.
 */

public class BookingHis extends BaseAdapter {
    Context context;
    public BookingHis(Context context)
    {
        this.context=context;
    }
    @Override
    public int getCount() {
        return 5;
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
        View view=  LayoutInflater.from(context).inflate(R.layout.layout_history,null);

        return view;
    }
}
