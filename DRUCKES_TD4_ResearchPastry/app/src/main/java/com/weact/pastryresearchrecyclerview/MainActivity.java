package com.weact.pastryresearchrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    View main = null;
    EditText researchBar = null;
    String appmsg = "[LOG] ********* APP MSG *********[LOG] : ";

    private RecyclerView myRecyclerView = null;
    private RecyclerView.Adapter myAdapter = null;
    private RecyclerView.LayoutManager myLayoutManager = null;
    private RecyclerItemClickListener myListener = null;

    //Food Pastry
    Food foodError = null;
    Food f1 = null;
    Food f2 = null;
    Food f3 = null;
    Food f4 = null;
    Food f5 = null;
    Food f6 = null;
    Food f7 = null;
    Food f8 = null;
    Food f9 = null;
    Food f10 = null;
    Food f11 = null;
    Food f12 = null;

    ArrayList<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = findViewById(R.id.main);
        setTitle(R.string.MainActivity_Title);

        foodError = new Food(getResources().getString(R.string.foodname_error),"UNKNOWN",0, R.drawable.error);

        f1 = new Food(getResources().getString(R.string.home_pastryname_Tiramisu),getResources().getString(R.string.Italie),10, R.drawable.tiramisu);
        f2 = new Food(getResources().getString(R.string.home_pastryname_Macaron),getResources().getString(R.string.France),4, R.drawable.macaron);
        f3 = new Food(getResources().getString(R.string.home_pastryname_MilleFeuille), getResources().getString(R.string.France), 4, R.drawable.millefeuille);
        f4 = new Food(getResources().getString(R.string.home_pastryname_ParisBrest), getResources().getString(R.string.France), 3, R.drawable.parisbrest);
        f5 = new Food(getResources().getString(R.string.donuts), getResources().getString(R.string.NA), 2, R.drawable.donuts);
        f6 = new Food(getResources().getString(R.string.home_pastryname_Eclair),getResources().getString(R.string.France),2, R.drawable.eclairauchocolat);
        f7 = new Food(getResources().getString(R.string.home_pastryname_GTiramisu),getResources().getString(R.string.GoldenItalie),59, R.drawable.tiramisu);
        f8 = new Food(getResources().getString(R.string.home_pastryname_GMacaron),getResources().getString(R.string.GoldenFrance),44, R.drawable.macaron);
        f9 = new Food(getResources().getString(R.string.home_pastryname_GMilleFeuille), getResources().getString(R.string.GoldenFrance), 57, R.drawable.millefeuille);
        f10 = new Food(getResources().getString(R.string.home_pastryname_GParisBrest), getResources().getString(R.string.GoldenFrance), 278, R.drawable.parisbrest);
        f11 = new Food(getResources().getString(R.string.goldendonuts), getResources().getString(R.string.GNA), 17, R.drawable.donuts);
        f12 = new Food(getResources().getString(R.string.home_pastryname_GEclair),getResources().getString(R.string.GoldenFrance),47, R.drawable.eclairauchocolat);

        Food[] foods = {f1, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, foodError}; //foodError HAS TO BE LAST ITEM
        Parcelable[] parcelables = foods;

        foodList = new ArrayList<>();
        initFoodList(foodList, foods);

        myRecyclerView = findViewById(R.id.myList);
        myRecyclerView.setHasFixedSize(true);

        myLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myLayoutManager);

        foodList.remove(foodError);
        myAdapter = new OptimizedAndroidRecycler(foodList);
        myRecyclerView.setAdapter(myAdapter);

        myListener = new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(appmsg, String.valueOf(foods.length));
                Log.d(appmsg, String.valueOf(foods[position]));
                String itemAnnouncer = foods[position].getName() + "AT POSITION : " + (position+1) + "IN THE ITEM LIST";
                Snackbar.make(main, itemAnnouncer, Snackbar.LENGTH_LONG).show();
                switchActivityFromParcelableAndFinish(MainActivity.this, FoodScreen.class, foods[position].getEXTRA_TAG(), parcelables[position]);
            }
        });

        myRecyclerView.addOnItemTouchListener(myListener);

        researchBar = findViewById(R.id.search_bar);
        researchBar.addTextChangedListener(this);

    }

    private void initFoodList(ArrayList<Food> foodList, Food[] FoodArray){
        foodList.addAll(Arrays.asList(FoodArray));
    }

    protected void switchActivityFromParcelableAndFinish(Context packageContext, Class<?> cls, String EXTRA_VARIABLE, Parcelable arg){
        Intent switchActivity = new Intent(packageContext, cls);
        switchActivity.putExtra(EXTRA_VARIABLE, arg);
        startActivity(switchActivity);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        filter(s.toString().toUpperCase());
    }

    public void filter(String input){
        int foodCount = 0;
        ArrayList<Food> foodResearchResultList = new ArrayList<>();
        ArrayList<Parcelable> foodParcelables = new ArrayList<>();
        myAdapter = null;
        myRecyclerView.setAdapter(null);
        for(Food f : foodList){
            if(f.getName().toUpperCase().startsWith(input)){
                foodResearchResultList.add(f);
                foodParcelables.add(f);
                foodCount++;
            }
        }

        if(foodCount == 0){
            foodResearchResultList.add(foodError);
            foodParcelables.add(foodError);
        }

        myAdapter = new OptimizedAndroidRecycler(foodResearchResultList);
        myRecyclerView.setAdapter(myAdapter);

        myRecyclerView.removeOnItemTouchListener(myListener);

        myListener = new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(appmsg, String.valueOf(foodResearchResultList.size()));
                Log.d(appmsg, String.valueOf(foodResearchResultList.get(position)));
                String itemAnnouncer = foodResearchResultList.get(position).getName() + "AT POSITION : " + (position+1) + "IN THE ITEM LIST";
                Snackbar.make(main, itemAnnouncer, Snackbar.LENGTH_LONG).show();
                switchActivityFromParcelableAndFinish(MainActivity.this, FoodScreen.class, foodResearchResultList.get(position).getEXTRA_TAG(), foodParcelables.get(position));
            }
        });

        myRecyclerView.addOnItemTouchListener(myListener);
    }
}