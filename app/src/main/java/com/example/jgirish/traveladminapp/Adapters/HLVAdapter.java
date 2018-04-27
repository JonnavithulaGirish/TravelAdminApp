package com.example.jgirish.traveladminapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jgirish.traveladminapp.R;

/**
 * Created by j.girish on 27-04-2018.
 */

public class HLVAdapter extends RecyclerView.Adapter<HLVAdapter.ViewHolder> {
    Context myContext;
public HLVAdapter(Context mycontext)
{
    this.myContext=mycontext;
}
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View myView;
        public ViewHolder(View v) {
            super(v);
            myView = v;
    }
    }
    @NonNull
    @Override
    public HLVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(myContext).inflate(R.layout.dealrequest_layout,null);
        ViewHolder v=new ViewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull HLVAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
