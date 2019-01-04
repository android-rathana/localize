package com.example.ratha.realtimedb.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ratha.realtimedb.R
import com.example.ratha.realtimedb.model.User
import com.example.ratha.realtimedb.util.CurrentDate
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
    }

    fun onRegister(view :View){
        val user=User(
            name = username?.text.toString(),
            email = email.text.toString(),
            password = password.text.toString(),
            dateCreated = CurrentDate.getCurrentDate().toString(),
            isLogin = false)
    }
}
