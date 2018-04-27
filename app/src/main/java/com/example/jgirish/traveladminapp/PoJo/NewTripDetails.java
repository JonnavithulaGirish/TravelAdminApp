package com.example.jgirish.traveladminapp.PoJo;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewTripDetails {

    public static ArrayList<String> places;
    public static int duration;
    public static int price;
    public static String sourceLoc;



    public static ArrayList<ArrayList<String>> hotels = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> iternary = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> placesIty = new ArrayList<ArrayList<String>>();

    public static boolean sendDataToFireBase()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Packages").child("key");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Integer value = dataSnapshot.getValue(Integer.class);
                Log.d("myTester", "Value is: " + value);
                myRef.setValue(value+1);

                DatabaseReference packageRef = database.getReference("Packages").child("dream"+value).child("Places Visited");
                packageRef.setValue(places);

                packageRef = database.getReference("Packages").child("dream"+value).child("Cost");
                packageRef.setValue(price);

                packageRef = database.getReference("Packages").child("dream"+value).child("Duration");
                packageRef.setValue(duration);

                for(int i=0;i<hotels.size();i++)
                {
                    packageRef = database.getReference("Packages").child("dream"+value).child("Day"+i).child("Hotels");
                    packageRef.setValue(hotels.get(i));

                    packageRef = database.getReference("Packages").child("dream"+value).child("Day"+i).child("Iternary");
                    packageRef.setValue(iternary.get(i));

                    packageRef = database.getReference("Packages").child("dream"+value).child("Day"+i).child("Places");
                    packageRef.setValue(placesIty.get(i));
                }

                //Trip added!!!

                database.getReference("Dealers").child("Support Team").child("Trips").setValue("dream"+value);

                for(int i=0;i<places.size();i++){
                    database.getReference("Trip Search").child(places.get(i)).child(duration+" Day").child("dream"+value).setValue(true);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return true;
    }
}
