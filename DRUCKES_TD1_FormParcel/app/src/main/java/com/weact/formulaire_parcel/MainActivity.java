package com.weact.formulaire_parcel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements TextWatcher{

    // MAIN VIEW : PARENT / CONTEXT
    View mainView = null;

    //EXTRAS
    private final String EXTRA_USER = "EXTRA_USER";

    //appmsg string variable
    String appmsg = "[LOG] ********* APP MSG *********[LOG] : ";

    //today date variable
    Date today = Calendar.getInstance().getTime();

    //fname
    String input_fname = "";
    String input_name = "";
    String input_gender = "Femme";
    String input_email = "";


    //Objects
    TextView textTodayDate = null;
    TextInputEditText FName_input = null;
    TextInputEditText Name_input = null;
    TextView EMail_text = null;

    RadioGroup radioGroup = null;
    RadioButton radiobtn_man = null;
    RadioButton radiobtn_woman = null;

    Button form_reset = null;
    Button form_submit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.formSignup);

        //Objects declaration

        //MAIN VIEW
        View mainView = (View)findViewById(R.id.main);

        //TextView date
        textTodayDate = (TextView)findViewById(R.id.today_date);

        // TextEdit Inputs
        //FName
        FName_input = (TextInputEditText)findViewById(R.id.fname_input_text);
        //Name
        Name_input = (TextInputEditText)findViewById(R.id.name_input_text);
        //EMAIL
        EMail_text = (TextView)findViewById(R.id.email_text);


        //RADIOBUTTONS
        //GROUP
        radioGroup  = (RadioGroup)findViewById(R.id.vertical_radiogroup);
        //BTN
        radiobtn_man = (RadioButton)findViewById(R.id.radio_man);
        radiobtn_woman = (RadioButton)findViewById(R.id.radio_woman);

        //Assign today_date's text to Today Date ; Q1.1 => getTodayDate()
        textTodayDate.setText(getTodayDate());

        //TextWatchers
        FName_input.addTextChangedListener(this);
        Name_input.addTextChangedListener(this);

        //BUTTONS RESET SUBMIT LISTENERS
        form_reset = (Button)findViewById(R.id.button_reset);
        form_submit  = (Button)findViewById(R.id.button_submit);
        SETUP_ResetFormListeners();
        SETUP_SubmitFormListeners();

        //EVENT(S) LISTENER(S)
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged(RadioGroup arg, int id){
                switch(id){
                    case R.id.radio_man:
                        if(!input_fname.equals("") && !input_name.equals("")){
                            Snackbar.make(mainView, "Bienvenue "+input_fname+", " + input_name + " vous êtes un Homme !", Snackbar.LENGTH_SHORT).show();
                        }
                        input_gender = "Homme";
                        //Log.i(appmsg,"MAN RADIO HAS BEEN CHECKED");
                        //Log.i(appmsg, "WOMAN RADIO HAS BEEN UNCHECKED");
                        break;
                    case R.id.radio_woman:
                        if(!input_fname.equals("") && !input_name.equals("")) {
                            Snackbar.make(mainView, "Bienvenue "+input_fname+", " + input_name + " vous êtes une Femme !", Snackbar.LENGTH_SHORT).show();
                        }
                        input_gender = "Femme";
                        //Log.i(appmsg, "MAN RADIO HAS BEEN UNCHECKED");
                        //Log.i(appmsg, "WOMAN RADIO HAS BEEN CHECKED");
                        break;
                    default:
                        Snackbar.make(mainView, "UNKNOWN - ERROR", Snackbar.LENGTH_LONG).show();
                        //Log.i(appmsg, "UNKNOWN RADIOBUTTON");
                        break;
                }
            }
        });
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(FName_input.getText().hashCode() == s.hashCode()){
            input_fname = s.toString();
            EMail_text.setText(setEmail(input_fname, input_name));
        }
        if(Name_input.getText().hashCode() == s.hashCode()){
            input_name = s.toString();
            EMail_text.setText(setEmail(input_fname, input_name));
        }
        input_email = EMail_text.getText().toString();
    }

    protected String getTodayDate(){ // Q1.1
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(today);
    }

    protected String setEmail(String fname, String name){
        return fname + "." + name + "@ludus-academie.com";
    }

    protected void SETUP_ResetFormListeners(){
        form_reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent resetActivity = new Intent(MainActivity.this, MainActivity.class);
                startActivity(resetActivity);
                finish();
            }
        });
    }

    protected void SETUP_SubmitFormListeners(){
        form_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int checkRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton checkedRadioButton = (RadioButton)findViewById(checkRadioButtonId);

                Log.i(appmsg, "DONNEES UTILISATEUR APRES ENVOIE DES DONNEES : ");
                Log.i(appmsg, "NOM DE L'UTILISATEUR : " + input_fname);
                Log.i(appmsg, "PRENOM DE L'UTILISATEUR : " + input_name);
                Log.i(appmsg, "EMAIL DE L'UTILISATEUR : " + EMail_text.getText().toString());
                Log.i(appmsg, "GENRE DE L'UTILISATEUR : " + checkedRadioButton.getText().toString());

                /*  TO REPLACE WITH PARCEL CODE
                *   Intent submitFormAndSwitchActivity = new Intent(MainActivity.this, UserScreen.class);
                *   submitFormAndSwitchActivity.putExtra("FNAME",input_fname);
                *   submitFormAndSwitchActivity.putExtra("NAME",input_name);
                *   submitFormAndSwitchActivity.putExtra("GENDER",input_gender);
                *   submitFormAndSwitchActivity.putExtra("EMAIL",input_email);
                *   startActivity(submitFormAndSwitchActivity);
                *   finish();
                */

                //PARCEL CODE
                Intent submitFormCreateUserSwitchActivity = new Intent(MainActivity.this, UserScreen.class);
                User user = new User(input_fname, input_name, input_gender);
                submitFormCreateUserSwitchActivity.putExtra(EXTRA_USER, user);
                startActivity(submitFormCreateUserSwitchActivity);
            }
        });
    }
}