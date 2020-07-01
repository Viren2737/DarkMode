package com.example.darktheme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

/*
* Code by Sabith Pkc Mnr
* Visit: https://www.youtube.com/SabithPkcMnr
* Visit: https://www.SabithPkcMnr.com
*/

public class MainActivity extends AppCompatActivity {

    //Variables
    private Switch myswitch;
    ThemeSharedPref sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Checking that state true or false from SharedPref
        sharedpref = new ThemeSharedPref(this);
        if(sharedpref.loadNightModeState()) {
            setTheme(R.style.darktheme);
        }
        else  setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        //Displaying the layout after setting the theme
        setContentView(R.layout.activity_main);

        myswitch= findViewById(R.id.mySwitch);
        if (sharedpref.loadNightModeState()) {
            myswitch.setChecked(true);
        }

        //Checking the toggle state and saving it to the SharedPref
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                }
                else {
                    sharedpref.setNightModeState(false);
                }
                restartApp();
            }
        });
    }

    //Method to restart the app
    public void restartApp () {
        Intent restartApp = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(restartApp);
        finish();
    }
}