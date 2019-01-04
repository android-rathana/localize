package com.example.ratha.realtimedb.base

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

abstract class AbsIntent<T : AppCompatActivity>(mContext: Context,var o : Intent?=null,c : Class<T>) : Intent(mContext,c) {
    init {
        if (o != null) {
            putExtras(o)
        }
    }
}