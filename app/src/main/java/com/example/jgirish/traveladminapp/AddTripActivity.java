package com.example.jgirish.traveladminapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.jgirish.traveladminapp.Adapters.AddPlacesAdapter;
import com.example.jgirish.traveladminapp.PoJo.NewTripDetails;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddTripActivity extends AppCompatActivity {

    ArrayList<String> placesArray=new ArrayList<>();
    ListView listView;
    ListAdapter adapter;
    EditText newPlace;
    EditText duration;
    EditText source;
    EditText price;
    RelativeLayout newPlaceLayout;
    Button addPlace;
    Button cancelPlace;
    Button nextPage;
    Boolean add=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.addTripToolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.back_icon);

        Window window= getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.BLACK);
        }

        listView=findViewById(R.id.placesList);
        newPlace=findViewById(R.id.newPlace);
        newPlaceLayout=findViewById(R.id.placeLayout);
        addPlace=findViewById(R.id.addPlace);
        cancelPlace=findViewById(R.id.cancelPlace);
        nextPage=findViewById(R.id.nextPage);
        duration=findViewById(R.id.duration);
        price=findViewById(R.id.budget);
        source=findViewById(R.id.source);

//        placesArray.add("test");
//        placesArray.add("test");
        adapter=new AddPlacesAdapter(placesArray,getApplicationContext());
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                placesArray.remove(position);
                                adapter=new AddPlacesAdapter(placesArray,getApplicationContext());
                                listView.setAdapter(adapter);
                                view.setAlpha(1);
                            }
                        });

            }

        });

        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(add){
                    newPlaceLayout.setVisibility(View.VISIBLE);
                    cancelPlace.setVisibility(View.VISIBLE);
                }
                else {
                    newPlaceLayout.setVisibility(View.GONE);
                    cancelPlace.setVisibility(View.GONE);
                    if(newPlace.getText().toString().length()>0)
                    {
                        placesArray.add(toCamelCase(newPlace.getText().toString()));
                        adapter=new AddPlacesAdapter(placesArray,getApplicationContext());
                        listView.setAdapter(adapter);
                    }
                }
                add=!add;
                newPlace.setText("");
            }
        });

        cancelPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPlaceLayout.setVisibility(View.GONE);
                cancelPlace.setVisibility(View.GONE);
                newPlace.setText("");
            }
        });

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(duration.getText().toString()!=null)
                    NewTripDetails.duration = Integer.parseInt(duration.getText().toString());
                if(price.getText().toString()!=null)
                    NewTripDetails.price = Integer.parseInt(price.getText().toString());
                if(placesArray.size()>0)
                    NewTripDetails.places = placesArray;
                if(source.getText().toString()!=null)
                    NewTripDetails.sourceLoc = source.getText().toString();

                Intent intent=new Intent(getApplicationContext(),IternaryActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static String toCamelCase(final String init) {
        if (init==null)
            return null;

        final StringBuilder ret = new StringBuilder(init.length());

        for (final String word : init.split(" ")) {
            if (!word.isEmpty()) {
                ret.append(word.substring(0, 1).toUpperCase());
                ret.append(word.substring(1).toLowerCase());
            }
            if (!(ret.length()==init.length()))
                ret.append(" ");
        }

        return ret.toString();
    }

//    private class StableArrayAdapter extends ArrayAdapter<String> {
//
//        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
//
//        public StableArrayAdapter(Context context, int textViewResourceId,
//                                  List<String> objects) {
//            super(context, textViewResourceId, objects);
//            for (int i = 0; i < objects.size(); ++i) {
//                mIdMap.put(objects.get(i), i);
//            }
//        }
//
//        @Override
//        public long getItemId(int position) {
//            String item = getItem(position);
//            return mIdMap.get(item);
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return true;
//        }
//
//    }

}
