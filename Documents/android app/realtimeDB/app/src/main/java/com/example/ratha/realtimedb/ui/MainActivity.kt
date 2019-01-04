package com.example.ratha.realtimedb.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ratha.realtimedb.R
import com.example.ratha.realtimedb.data.UserPreference
import com.example.ratha.realtimedb.model.User
import com.example.ratha.realtimedb.util.intent.LoginIntent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!UserPreference.getPreference(this).contains(UserPreference.UID)){
            val user = User(name="",dateCreated = "",isLogin = false)
            UserPreference.save(this,user)
        }

        if(UserPreference.isLogin(this)){
            //LoginIntent<User>(this)
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        Log.e("MainActivity","${UserPreference.read(this).toString()}")
    }
}
