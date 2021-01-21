package com.weact.recyclerviewlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PlanetScreen extends AppCompatActivity {

    String appmsg = "[LOG] ********* APP MSG *********[LOG] : ";

    Planet px;

    TextView TV_ScreenTitle = null;
    TextView TV_planetname = null;
    TextView TV_planetradius = null;
    TextView TV_planetweight = null;
    TextView TV_planetage = null;
    TextView TV_planetorbital = null;
    ImageView IV_planetpicture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planet_screen);
        //GET EXTRA PARCELABLE USER AND CREATE ONE FROM EXTRA
        Intent intentDatas = getIntent();
        px = createPlanetFromExtraDatas(intentDatas);
        setTitle("PLANETE [" + px.getName() + "]");

        //GET VIEW OBJECTS
        TV_ScreenTitle = findViewById(R.id.DetailTitlePlanet);
        TV_planetname = findViewById(R.id.planet_name_text);
        TV_planetradius = findViewById(R.id.planet_radius_text);
        TV_planetweight = findViewById(R.id.planet_weight_text);
        TV_planetage = findViewById(R.id.planet_age_text);
        TV_planetorbital = findViewById(R.id.planet_orbital_text);
        IV_planetpicture = findViewById(R.id.planetpicture);
        setFieldsValueFromPlanet(px);
    }

    protected Planet createPlanetFromExtraDatas(Intent extraDATA){
        return extraDATA.getParcelableExtra("PLANET");
    }

    protected void setFieldsValueFromPlanet(Planet p){
        String planet_details = getResources().getString(R.string.planet_details);
        TV_ScreenTitle.setText(String.format(planet_details, p.getName()));
        //TV_ScreenTitle.setText(String.format("Voici les détails de la planète%n%s.", p.getName()));
        TV_ScreenTitle.setTextSize(15);

        /*p
         *   private String name;
         *   private int radius;
         *   private String weight;
         *   private String age;
         *   private int orbital;
         * */

        TV_planetname.setText(String.format(" %s", p.getName()));
        TV_planetradius.setText(String.format(" %s", p.getRadius()));
        TV_planetweight.setText(String.format(" %s", p.getWeight()));
        TV_planetage.setText(String.format(" %s", p.getAge()));
        TV_planetorbital.setText(String.format(" %s", p.getOrbital()));
        IV_planetpicture.setBackgroundResource(p.getPictureId());

    }

}