package com.example.ratha.realtimedb.model

import android.os.Parcel
import android.os.Parcelable
import com.google.firebase.database.Exclude
import java.util.*

data class User(var uid: String?="",
                var name:String,
                var dateCreated: String,
                var isLogin: Boolean,
                var email: String?="",
                var password: String?="",
                var gender: String?=""
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    @Exclude
    fun toMap(): Map<String,Any?>{
        return mapOf(
            "name" to name,
            "uid" to uid,
            "dateCreated" to dateCreated,
            "isLogin" to isLogin,
            "email" to email,
            "password" to password,
            "gender" to gender
        )
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(uid)
        parcel.writeString(name)
        parcel.writeString(dateCreated)
        parcel.writeByte(if (isLogin) 1 else 0)
        parcel.writeString(email)
        parcel.writeString(password)
        parcel.writeString(gender)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }

}