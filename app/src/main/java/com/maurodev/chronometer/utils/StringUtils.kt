package com.maurodev.chronometer.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

object StringUtils {

    /**
     * This function transforms the long value into
     *  a string in an understandable format for the user.
     */
    @SuppressLint("SimpleDateFormat")
    fun formatTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("mm:ss.SSS")
        return format.format(date)
    }

}