package com.weact.formulaire_parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserScreen extends AppCompatActivity {

    //GET DATAS
    Intent formDataFromIntent = null;

    //EXTRAS
    private final String EXTRA_USER = "EXTRA_USER";

    TextView fname_getter = null;
    TextView name_getter = null;
    TextView gender_getter = null;
    TextView email_getter = null;
    ArrayList<TextView> getters = new ArrayList<>();

    Button btn_goback = null;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);
        setTitle(R.string.UserProfile);

        formDataFromIntent = getIntent();
        user = formDataFromIntent.getParcelableExtra(EXTRA_USER);

        btn_goback = findViewById(R.id.btn_goback);
        btn_goback.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent backToMainActivity = new Intent(UserScreen.this, MainActivity.class);
                startActivity(backToMainActivity);
                finish();
            }
        });

        fname_getter = (TextView)findViewById(R.id.fname_text_getter);
        name_getter = (TextView)findViewById(R.id.name_text_getter);
        gender_getter = (TextView)findViewById(R.id.gender_text_getter);
        email_getter = (TextView)findViewById(R.id.email_text_getter);

        getters.add(fname_getter);
        getters.add(name_getter);
        getters.add(gender_getter);
        getters.add(email_getter);

        setGettersValue();
        setGettersColor();
    }

    protected void setGettersValue(){
        fname_getter.setText(user.getFName());
        name_getter.setText(user.getName());
        gender_getter.setText(user.getGender());
        email_getter.setText(user.getEmail());
    }

    protected void setGettersColor(){
        if(gender_getter.getText().toString().equals("Homme")){
            for(int i = 0; i < getters.size(); i++){
                getters.get(i).setTextColor(getResources().getColor(R.color.result_man));
            }
        }else {
            for (int j = 0; j < getters.size(); j++) {
                getters.get(j).setTextColor(getResources().getColor(R.color.result_woman));
            }
        }
    }
}