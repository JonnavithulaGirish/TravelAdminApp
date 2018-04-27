package com.example.jgirish.traveladminapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.jgirish.traveladminapp.Adapters.AddPlacesAdapter;
import com.example.jgirish.traveladminapp.PoJo.NewTripDetails;

import java.util.ArrayList;
import java.util.Arrays;

public class IternaryActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iternary);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        for(int i=0;i<NewTripDetails.duration;i++)
        {
            tabLayout.addTab(tabLayout.newTab().setText("Day "+i));
            NewTripDetails.hotels.add(new ArrayList<String>());
            NewTripDetails.iternary.add(new ArrayList<String>());
            NewTripDetails.placesIty.add(new ArrayList<String>());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.iternary_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case R.id.done:
                NewTripDetails.sendDataToFireBase();
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
//            Log.d(TAG, "newInstance: ");
            args.putInt(ARG_SECTION_NUMBER, sectionNumber-1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_iternary, container, false);

            final ListView hotelsList= rootView.findViewById(R.id.hotelList);
            final EditText newHotel= rootView.findViewById(R.id.newHotel);
            Button addButton=rootView.findViewById(R.id.addHotel);

            ListAdapter adapter=new AddPlacesAdapter(NewTripDetails.hotels.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
            hotelsList.setAdapter(adapter);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(newHotel.getText().toString().length()>0)
                    {
                        NewTripDetails.hotels.get(getArguments().getInt(ARG_SECTION_NUMBER)).add(newHotel.getText().toString());
                        ListAdapter adapter=new AddPlacesAdapter(NewTripDetails.hotels.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
                        hotelsList.setAdapter(adapter);
                    }
                }
            });

            hotelsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);
                    view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            NewTripDetails.hotels.get(getArguments().getInt(ARG_SECTION_NUMBER)).remove(position);
                            ListAdapter adapter=new AddPlacesAdapter(NewTripDetails.hotels.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
                            hotelsList.setAdapter(adapter);
                            view.setAlpha(1);
                        }
                    });

                }

            });


            final ListView iternaryList= rootView.findViewById(R.id.iternaryList);
            final EditText newIternary= rootView.findViewById(R.id.newIternary);
            Button addIternary=rootView.findViewById(R.id.addIternary);

            addIternary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(newIternary.getText().toString().length()>0)
                    {
                        NewTripDetails.iternary.get(getArguments().getInt(ARG_SECTION_NUMBER)).add(newIternary.getText().toString());
                        ListAdapter adapter=new AddPlacesAdapter(NewTripDetails.iternary.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
                        iternaryList.setAdapter(adapter);
                    }
                }
            });

            iternaryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);
                    view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            NewTripDetails.iternary.get(getArguments().getInt(ARG_SECTION_NUMBER)).remove(position);
                            ListAdapter adapter=new AddPlacesAdapter(NewTripDetails.iternary.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
                            iternaryList.setAdapter(adapter);
                            view.setAlpha(1);
                        }
                    });

                }

            });


            final ListView placeIternaryList= rootView.findViewById(R.id.placesListIternary);
            final EditText newPlaceIternary= rootView.findViewById(R.id.newPlaceIternary);
            Button addPlace=rootView.findViewById(R.id.addPlaceIternary);

            addPlace.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(newPlaceIternary.getText().toString().length()>0)
                    {
                        NewTripDetails.placesIty.get(getArguments().getInt(ARG_SECTION_NUMBER)).add(newPlaceIternary.getText().toString());
                        ListAdapter adapter=new AddPlacesAdapter(NewTripDetails.placesIty.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
                        placeIternaryList.setAdapter(adapter);
                    }
                }
            });

            placeIternaryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);
                    view.animate().setDuration(1000).alpha(0).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            NewTripDetails.iternary.get(getArguments().getInt(ARG_SECTION_NUMBER)).remove(position);
                            ListAdapter adapter=new AddPlacesAdapter(NewTripDetails.iternary.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
                            placeIternaryList.setAdapter(adapter);
                            view.setAlpha(1);
                        }
                    });

                }

            });

            Log.d("testing", "onCreateView: hotels"+Arrays.toString(NewTripDetails.hotels.get(getArguments().getInt(ARG_SECTION_NUMBER)).toArray()));
            Log.d("testing", "onCreateView: iter"+Arrays.toString(NewTripDetails.iternary.get(getArguments().getInt(ARG_SECTION_NUMBER)).toArray()));
            Log.d("testing", "onCreateView: placesITR"+Arrays.toString(NewTripDetails.placesIty.get(getArguments().getInt(ARG_SECTION_NUMBER)).toArray()));

            adapter=new AddPlacesAdapter(NewTripDetails.iternary.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
            iternaryList.setAdapter(adapter);

            adapter=new AddPlacesAdapter(NewTripDetails.placesIty.get(getArguments().getInt(ARG_SECTION_NUMBER)),getContext());
            placeIternaryList.setAdapter(adapter);

            return rootView;

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return NewTripDetails.duration;
        }
    }
}
