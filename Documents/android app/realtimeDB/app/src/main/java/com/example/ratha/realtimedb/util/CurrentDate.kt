package com.example.ratha.realtimedb.util

import java.text.SimpleDateFormat
import java.util.*

object CurrentDate {
    fun getCurrentDate() : Date =Calendar.getInstance().time

    fun parseDate(date : String): Date{
        val format = SimpleDateFormat("MMMM d, yyyy")
        return format.parse(date)
    }
}