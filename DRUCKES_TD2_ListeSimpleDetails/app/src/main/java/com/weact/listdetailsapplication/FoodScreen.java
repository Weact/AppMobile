package com.weact.listdetailsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodScreen extends AppCompatActivity {

    String appmsg = "[LOG] ********* APP MSG *********[LOG] : ";

    Food fx;

    TextView TV_ScreenTitle = null;
    TextView TV_foodname = null;
    TextView TV_foodorigin = null;
    TextView TV_foodprice = null;

    ImageView IV_FoodPicture = null;
    /*
    *   private String name;
    *   private String origin;
    *   private int price;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_screen);
        //GET EXTRA PARCELABLE USER AND CREATE ONE FROM EXTRA
        Intent intentDatas = getIntent();
        fx = createFoodFromExtraDatas(intentDatas);
        setTitle("PLAT [" + fx.getName() + "]");

        //GET VIEW OBJECTS
        TV_ScreenTitle = findViewById(R.id.DetailTitleFood);
        TV_foodname = findViewById(R.id.food_name_text);
        TV_foodorigin = findViewById(R.id.food_origin_text);
        TV_foodprice = findViewById(R.id.food_price_text);

        IV_FoodPicture = findViewById(R.id.foodpicture);
        setFieldsValueFromFood(fx);
    }

    protected Food createFoodFromExtraDatas(Intent extraDATA){
        return extraDATA.getParcelableExtra("FOOD");
    }

    protected void setFieldsValueFromFood(Food f){
        String food_details = getResources().getString(R.string.food_details);
        TV_ScreenTitle.setText(String.format(food_details, f.getName()));
        //TV_ScreenTitle.setText(String.format("Voici les détails du plat%n%s", f.getName()));
        TV_ScreenTitle.setTextSize(15);

        TV_foodname.setText(String.format(" %s", f.getName()));
        TV_foodorigin.setText(String.format(" %s", f.getOrigin()));
        TV_foodprice.setText(String.format(" %s", f.getPrice()));

        IV_FoodPicture.setBackgroundResource(f.getPictureId());
    }

}