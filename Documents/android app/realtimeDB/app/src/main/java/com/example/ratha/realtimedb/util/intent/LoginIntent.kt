package com.example.ratha.realtimedb.util.intent

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.example.ratha.realtimedb.base.AbsIntent
import com.example.ratha.realtimedb.ui.LoginActivity

class LoginIntent<T : Parcelable> (val mContext: Context,var intent : Intent?=null) : AbsIntent<LoginActivity>(mContext,intent,LoginActivity::class.java)