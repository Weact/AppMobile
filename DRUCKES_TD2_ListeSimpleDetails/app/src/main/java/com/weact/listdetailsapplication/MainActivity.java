package com.weact.listdetailsapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    View main = null;
    ListView maliste = null;
    String appmsg = "[LOG] ********* APP MSG *********[LOG] : ";

    //EXTRAS
    private final String EXTRA_USER = "USER";
    private final String EXTRA_PLANET = "PLANET";
    private final String EXTRA_FOOD = "FOOD";

    //User
    User u = new User("MARC","Salomon","Homme",R.drawable.profil_placeholder1);
    User u2 = new User("DUPONT","Gerard","Homme",R.drawable.profil_placeholder2);
    User u3 = new User("MATIN","Annabelle","Femme",R.drawable.profil_placeholder3);

    //Planet
    Planet p = new Planet("Terre",6378,"5,9736*10^24","4543 milliards",30, R.drawable.terre);
    Planet p2 = new Planet("Saturne", 58232, "568,46*10^24", "4,503 milliards", 10, R.drawable.saturne);
    Planet p3 = new Planet("Mars", 3390, "6,4185*10^23", "5,603milliards", 24, R.drawable.mars);

    //Food
    Food f = new Food("Choucroute d'Alsace", "France - Alsace",35, R.drawable.choucroute);
    Food f2 = new Food("Frites", "Belgique", 2, R.drawable.frite);
    Food f3 = new Food("Pizza","Italie",10, R.drawable.pizza);

    //Objects
    Object[] objects = {u, u2, u3, p, p2, p3, f, f2, f3};
    Parcelable[] parcelables = {u, u2, u3, p, p2, p3, f, f2, f3};


    //Objects => Converted to String => Get Class Name
    String[] objectsName = {
            u.getFName() + " " + u.getName(),
            u2.getFName() + " " + u2.getName(),
            u3.getFName() + " " + u3.getName(),
            p.getName(),
            p2.getName(),
            p3.getName(),
            f.getName(),
            f2.getName(),
            f3.getName()
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Liste et Détails");

        //LAYOUT'S PARENT : CONSTRAINT LAYOUT
        main = (View)findViewById(R.id.main);

        maliste = (ListView)findViewById(R.id.listview);
        ArrayList<String> arrayList = new ArrayList<>();

        fillObjectArray(arrayList, objectsName);

        /*
         *   You can use this adapter to provide views for an AdapterView,
         *   Returns a view for each object in a collection of data objects you provide,
         *   and can be used with list-based user interface widgets such as ListView or Spinner.
         *   By default, the array adapter creates a view by calling Object.toString()
         *   on each data object in the collection you provide, and places the result in a TextView.
         *   You may also customize what type of view is used for the data object in the collection.
         *   To customize what type of view is used for the data object,
         *   override getView(int, View, ViewGroup) and inflate a view resource.
         */

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        maliste.setAdapter(arrayAdapter);

        maliste.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClass = objects[position].getClass().getSimpleName();
                String itemAnnouncer =  itemClass +  " AT POSITION : " + (position+1) + " IN THE ITEM LIST";
                Snackbar.make(main, itemAnnouncer, Snackbar.LENGTH_LONG).show();

                /*
                *   SOLUTION TO BE ABLE TO USE :
                *   objects[position].getEXTRA_TAG() instead of
                *   u.getEXTRA_TAG(), p.getEXTRA_TAG(), f.getEXTRA_TAG()
                *
                *   CREATE A CLASS WHICH WILL BE THE PARENT OF ALL OTHER OBJECT NAMED 'item'
                *   USER FOOD AND PLANET CLASS WILL INHERIT FROM 'item' CLASS
                *   SO THAT THOSE THREE WILL SHARE COMMON METHODS AND WILL BE ABLE
                *   TO BE GROUPED IN A SINGLE ARRAY ; THEN THEIR COMMON METHODS
                *   WILL BE USABLE
                 */
                switch(itemClass){
                    case "User":
                        Log.d(appmsg, "RESULT ----- USER");
                        String intentName = "switchActivityFromUser";
                        switchActivityFromParcelableAndFinish(MainActivity.this, UserScreen.class, u.getEXTRA_TAG(), parcelables[position]);
                        break;
                    case "Planet":
                        Log.d(appmsg, "RESULT ----- PLANET");
                        switchActivityFromParcelableAndFinish(MainActivity.this, PlanetScreen.class, p.getEXTRA_TAG(), parcelables[position]);
                        break;
                    case "Food":
                        Log.d(appmsg, "RESULT ----- FOOD");
                        switchActivityFromParcelableAndFinish(MainActivity.this, FoodScreen.class, f.getEXTRA_TAG(), parcelables[position]);
                        break;
                    default:
                        Log.d(appmsg, "ERROR ----- UNKNOWN OBJECT");
                        break;
                }
            }
        });
    }

    protected void fillObjectArray(ArrayList<String> arrayListToFill, String[] objects){
        //Object.getClass().getSimpleName(); return class' name
        arrayListToFill.addAll(Arrays.asList(objects));
    }

    protected void switchActivityFromParcelableAndFinish(Context packageContext, Class<?> cls, String EXTRA_VARIABLE, Parcelable arg){
        Intent switchActivity = new Intent(packageContext, cls);
        switchActivity.putExtra(EXTRA_VARIABLE, arg);
        startActivity(switchActivity);
    }
}