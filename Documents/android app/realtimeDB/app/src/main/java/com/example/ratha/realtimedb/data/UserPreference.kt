package com.example.ratha.realtimedb.data

import android.content.Context
import android.content.SharedPreferences
import com.example.ratha.realtimedb.model.User

object UserPreference {

    const val USER_PRE="user_pre"
    const val NAME="name"
    const val UID="uid"
    const val IS_LOGIN="is_login"
    const val DATE_CREATED="date_created"
    fun getPreference(mContext: Context): SharedPreferences=
            mContext.getSharedPreferences(USER_PRE,Context.MODE_PRIVATE)
    fun getEditor(mContext: Context): SharedPreferences.Editor= getPreference(mContext).edit()

    fun save(mContext: Context , user : User){
        val editor = getEditor(mContext)
        editor.putString(NAME,user.name)
        editor.putString(UID,user.uid)
        editor.putString(DATE_CREATED,user.dateCreated.toString())
        editor.putBoolean(IS_LOGIN,true)
        editor.apply()
    }

    fun read(mContext: Context) : User{
        val pre=getPreference(mContext)
        return User(
            name =pre.getString(NAME,""),
            uid = pre.getString(UID,""),
            dateCreated = pre.getString(DATE_CREATED,""),
            isLogin = pre.getBoolean(IS_LOGIN,false)
        )
    }

    fun isLogin(mContext: Context): Boolean {
        return getPreference(mContext).getBoolean(IS_LOGIN,false)
    }

    fun remove(mContext: Context){
        val editor= getEditor(mContext)
        editor.putString(NAME,"")
        editor.putString(UID,"")
        editor.putString(DATE_CREATED,"")
        editor.putBoolean(IS_LOGIN,false)
        editor.apply()
    }
}