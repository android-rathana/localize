package com.example.ratha.localize;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;

import java.util.Locale;

public class LocalizeHelper {

    private AppCompatActivity activity;
    private  String language;
    public LocalizeHelper(AppCompatActivity activity){
        this.activity=activity;
    }


    public  void setLanguage(String language){
       resetScreen(language);
    }

    public void resetScreen(String language){
        Locale locale=new Locale(language);
        if(activity!=null){
            Configuration configuration=activity.getResources().getConfiguration();
            configuration.setLocale(locale);
            activity.getResources().updateConfiguration(configuration,activity.getResources().getDisplayMetrics());

            activity.recreate();
        }

    }
}
