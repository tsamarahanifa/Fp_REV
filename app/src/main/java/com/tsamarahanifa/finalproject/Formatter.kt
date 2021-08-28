package com.tsamarahanifa.finalproject

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Formatter {
    @SuppressLint("SimpleDateFormat")
    fun dateFormatter(input: String?): String {
        val date = SimpleDateFormat("dd/MM/yy").parse(input)
        return SimpleDateFormat("EEE, dd MMM yyy").format(date)
    }

    @SuppressLint("SimpleDateFormat")
    fun timeFormatter(input: String?): String {
        val inputFormat = SimpleDateFormat("HH:mm:ssXXX", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("Etc/ETC")
        val time = inputFormat.parse(input)

        return SimpleDateFormat("HH:mm").format(time)
    }
}