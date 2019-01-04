package com.example.ratha.realtimedb.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.ratha.realtimedb.R
import com.example.ratha.realtimedb.data.UserPreference
import com.example.ratha.realtimedb.model.User
import com.example.ratha.realtimedb.service.ChatService
import com.example.ratha.realtimedb.util.CurrentDate
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ref=ChatService.initFirebaseDatabase(ChatService.ROOT_NOED)
    }

    fun onLogin(view : View){
        val user =User(name = username?.text.toString(),dateCreated = CurrentDate.getCurrentDate().toString(),isLogin = true)
        //save user
        UserPreference.save(this,user)
        //MainIntent(this)
        ChatService.writeUser(ref,user)
        finish()
    }
}
