package com.example.ratha.firebasedemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ratha.firebasedemo.entity.User;


/**
 * Created by ratha on 11/22/2017.
 */

public class UserSession {

    public static final String ID="ID";
    public static final String FIRST_NAME="FIRST_NAME";
    public static final String LAST_NAME="LAST_NAME";
    public static final String EMAIL="EMAIL";
    public static final String PASSWORD="PASSWORD";

    private static final String USER_SESSION="USER_SESSION";

    private static SharedPreferences.Editor getPreferencesEditor(Context context,String preferencesName){
        SharedPreferences preferences =context.getSharedPreferences(preferencesName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= preferences.edit();
        return editor;
    }

    private static SharedPreferences getPreferences(Context context,String preferencesName){
        SharedPreferences preferences =context.getSharedPreferences(preferencesName,Context.MODE_PRIVATE);
        return preferences;
    }

    public static void saveUser(Context context,User user){
        SharedPreferences.Editor editor=getPreferencesEditor(context,USER_SESSION);
        if(null!=user){

            editor.putString(FIRST_NAME,user.getName());
            //editor.putString(LAST_NAME,user.getLastName());
            editor.putString(EMAIL,user.getEmail());
            editor.putString(PASSWORD,user.getPassword());
            editor.commit();
        }
    }

    public static User getUserSession(Context context){
        User user =null;
        SharedPreferences preferences=getPreferences(context,USER_SESSION);
        if(null!=preferences){

            user=new User(
                    preferences.getString(FIRST_NAME,"first name"),
                    preferences.getString(EMAIL,"email"),
                    preferences.getString(PASSWORD,"password")
            );
        }
        return user;

    }
}
