package com.weact.listdetailsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserScreen extends AppCompatActivity {

    String appmsg = "[LOG] ********* APP MSG *********[LOG] : ";

    User ux;

    TextView TV_ScreenTitle = null;
    TextView TV_userfname = null;
    TextView TV_username = null;
    TextView TV_usergender = null;
    TextView TV_useremail = null;

    ImageView IV_ProfilePicture = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        //GET EXTRA PARCELABLE USER AND CREATE ONE FROM EXTRA
        Intent intentDatas = getIntent();
        ux = createUserFromExtraDatas(intentDatas);
        setTitle("PROFIL [" + ux.getFName() + "  " + ux.getName() + "]");

        //GET VIEW OBJECTS
        TV_ScreenTitle = findViewById(R.id.DetailTitleUser);
        TV_userfname = findViewById(R.id.fname_text_getter);
        TV_username = findViewById(R.id.name_text_getter);
        TV_usergender = findViewById(R.id.gender_text_getter);
        TV_useremail = findViewById(R.id.email_text_getter);
        IV_ProfilePicture = findViewById(R.id.profilepicture);

        setFieldsValueFromUser(ux);
    }

    protected User createUserFromExtraDatas(Intent extraDATA){
        return extraDATA.getParcelableExtra("USER");
    }

    protected void setFieldsValueFromUser(User u){
        String welcome_profile = getResources().getString(R.string.welcome_to_profile_user);
        TV_ScreenTitle.setText(String.format(welcome_profile, u.getFName(), u.getName()));
        //TV_ScreenTitle.setText(String.format("Bienvenue sur votre profil,%n%s %s.", u.getFName(), u.getName()));
        TV_ScreenTitle.setTextSize(15);

        TV_userfname.setText(u.getFName());
        TV_username.setText(u.getName());
        TV_usergender.setText(u.getGender());
        TV_useremail.setText(u.getEmail());

        IV_ProfilePicture.setBackgroundResource(u.getPictureId());
    }

}