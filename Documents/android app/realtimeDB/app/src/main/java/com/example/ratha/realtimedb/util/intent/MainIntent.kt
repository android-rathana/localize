package com.example.ratha.realtimedb.util.intent

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import com.example.ratha.realtimedb.base.AbsIntent
import com.example.ratha.realtimedb.ui.MainActivity

class MainIntent(val mContext: Context, var intent: Intent? =null) : AbsIntent<MainActivity>(mContext,intent,MainActivity::class.java)