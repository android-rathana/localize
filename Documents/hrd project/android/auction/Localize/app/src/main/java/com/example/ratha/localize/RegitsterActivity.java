package com.example.ratha.localize;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Locale;

public class RegitsterActivity extends AppCompatActivity {

    Button btnHomeScreen,btnEnglish,btnkhmer;
    LocalizeHelper localizeHelper;
    Toolbar toolbar;
    TextView toolbarTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitster);
        toolbar=findViewById(R.id.toolbar);
        toolbarTitle=findViewById(R.id.toolbarTitle);
        toolbarTitle.setText(getString(R.string.app_name));

        localizeHelper=new LocalizeHelper(this);

        btnHomeScreen=findViewById(R.id.btnHomeScreen);
        btnEnglish=findViewById(R.id.btnEnglish);
        btnkhmer=findViewById(R.id.btnKhmer);
        btnHomeScreen.setOnClickListener(v->{
                startActivity(new Intent(this,MainActivity.class));
        });


        btnkhmer.setOnClickListener(v->{
            //changeLanguage("km");
            localizeHelper.setLanguage("km");
        });

        btnEnglish.setOnClickListener(v->{
            //changeLanguage("en");
            localizeHelper.setLanguage("en");
        });

    }

    public void changeLanguage(String language){
        Locale locale=new Locale(language);
        Configuration configuration=getResources().getConfiguration();
        configuration.setLocale(locale);
        getResources().updateConfiguration(configuration,getResources().getDisplayMetrics());
        recreate();
    }
}
