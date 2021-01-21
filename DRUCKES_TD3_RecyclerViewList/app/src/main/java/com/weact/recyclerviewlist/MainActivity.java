package com.weact.recyclerviewlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    View main = null;
    String appmsg = "[LOG] ********* APP MSG *********[LOG] : ";

    private RecyclerView myRecyclerView = null;
    private RecyclerView.Adapter myAdapter = null;
    private RecyclerView.LayoutManager myLayoutManager = null;

    //Planet
    Planet p1 = null;
    Planet p2 = null;
    Planet p3 = null;
    Planet p4 = null;
    Planet p5 = null;
    Planet p6 = null;
    Planet p7 = null;
    Planet p8 = null;
    Planet p9 = null;
    Planet p10 = null;
    Planet p11 = null;
    Planet p12 = null;

    ArrayList<Planet> planetList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = findViewById(R.id.main);

        Planet p1 = new Planet(getResources().getString(R.string.terre),6378,"5,9736*10^24","4,543 " + getResources().getString(R.string.age_unit),30, R.drawable.terre);
        Planet p2 = new Planet(getResources().getString(R.string.Saturne), 58232, "568,46*10^24", "4,503 " + getResources().getString(R.string.age_unit), 10, R.drawable.saturne);
        Planet p3 = new Planet(getResources().getString(R.string.mars), 3390, "9,7562*10^17", "5,603 " + getResources().getString(R.string.age_unit), 24, R.drawable.mars);
        Planet p4 = new Planet(getResources().getString(R.string.terre),1726,"3,8794*10^19","29,544 " + getResources().getString(R.string.age_unit),725, R.drawable.terre);
        Planet p5 = new Planet(getResources().getString(R.string.Saturne), 172624, "2,8461*10^14", "3,500 " + getResources().getString(R.string.age_unit), 24, R.drawable.saturne);
        Planet p6 = new Planet(getResources().getString(R.string.mars), 14237, "6,4894*10^7", "10,484 " + getResources().getString(R.string.age_unit), 684, R.drawable.mars);
        Planet p7 = new Planet(getResources().getString(R.string.terre),91540,"1,4910*10^28","7,641 " + getResources().getString(R.string.age_unit),164, R.drawable.terre);
        Planet p8 = new Planet(getResources().getString(R.string.Saturne), 237, "7,6431*10^38", "1,346 " + getResources().getString(R.string.age_unit), 27, R.drawable.saturne);
        Planet p9 = new Planet(getResources().getString(R.string.Saturne), 172624, "7,4613*10^14", "31,649 " + getResources().getString(R.string.age_unit), 61, R.drawable.saturne);
        Planet p10 = new Planet(getResources().getString(R.string.mars), 14237, "9,999*10^7", "13,484 " + getResources().getString(R.string.age_unit), 84, R.drawable.mars);
        Planet p11 = new Planet(getResources().getString(R.string.terre),91540,"1,4949*10^28","4,321 " + getResources().getString(R.string.age_unit),4, R.drawable.terre);
        Planet p12 = new Planet(getResources().getString(R.string.Saturne), 237, "4,9431*10^38", "9,654 " + getResources().getString(R.string.age_unit), 97, R.drawable.saturne);


        Planet[] planets = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12};
        Parcelable[] parcelables = planets;

        planetList = new ArrayList<>();

        //String[] myDataSet = {p1.getName(), p2.getName(), p3.getName()};
        initPlanetList(planetList, planets);

        myRecyclerView = findViewById(R.id.myList);
        myRecyclerView.setHasFixedSize(true);

        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        myAdapter = new OptimizedAndroidRecycler(planetList);
        myRecyclerView.setAdapter(myAdapter);

        myRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(appmsg, String.valueOf(planets.length));
                Log.d(appmsg, String.valueOf(planets[position]));
                String itemAnnouncer = planets[position].getName() + "AT POSITION : " + (position+1) + "IN THE ITEM LIST";
                Snackbar.make(main, itemAnnouncer, Snackbar.LENGTH_LONG).show();

                switchActivityFromParcelableAndFinish(MainActivity.this, PlanetScreen.class, planets[position].getEXTRA_TAG(), parcelables[position]);
            }
        }));
    }

    private void initPlanetList(ArrayList<Planet> planetList, Planet[] PlanetArray){
        planetList.addAll(Arrays.asList(PlanetArray));
    }

    protected void switchActivityFromParcelableAndFinish(Context packageContext, Class<?> cls, String EXTRA_VARIABLE, Parcelable arg){
        Intent switchActivity = new Intent(packageContext, cls);
        switchActivity.putExtra(EXTRA_VARIABLE, arg);
        startActivity(switchActivity);
    }
}