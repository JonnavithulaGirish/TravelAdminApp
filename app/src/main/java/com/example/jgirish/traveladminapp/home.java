package com.example.jgirish.traveladminapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.jgirish.traveladminapp.Adapters.HLVAdapter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ScrollView scrollView=(ScrollView)findViewById(R.id.sv1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("DashBoard");
        Window window= getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.BLACK);
        }
        scrollView.smoothScrollTo(0, 0);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.U_Name1);
        nav_user.setText("lololol");
        TextView nav_Email=(TextView)hView.findViewById(R.id.U_email);
        nav_Email.setText("girish@snu.edu.in");
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setNavigationIcon(R.drawable.nav_bar_icon);
        toolbar.setTitle("Dashboard");
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 1),
                new DataPoint(5, 5),
                new DataPoint(6, 3),
                new DataPoint(7, 2),
                new DataPoint(8, 1) ,
                new DataPoint(9, 5),
                new DataPoint(10, 3),
                new DataPoint(11, 2),

        });
        graph.addSeries(series);
        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[] {"Jan", "    Feb", "  Mar"," Apr","May","Jun","Jul","Aug","Sep","Oct  " ,"Nov    ","Dec"});
        //staticLabelsFormatter.setVerticalLabels(new String[] {"low", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
        series.setColor(Color.BLACK);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(10);
        series.setThickness(2);
        series.setTitle("Business Status ");
        //graph.getLegendRenderer().setVisible(true);
        //graph.getLegendRenderer().setBackgroundColor(Color.WHITE);
        //graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.);

        RecyclerView myRecyclerView=(RecyclerView)findViewById(R.id.dealRequest);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        myRecyclerView.setLayoutManager(layoutManager);
        HLVAdapter adapter=new HLVAdapter(this);
        myRecyclerView.setAdapter(adapter);





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent=new Intent(this,BookingsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {
            Intent intent=new Intent(this,AccountActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
