package com.icstech.csgomatchescompose.util

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar


@RequiresApi(Build.VERSION_CODES.O)
fun convertUtcToLocalDate(utcString: String): String {
    val utcDateTime = ZonedDateTime.parse(utcString, DateTimeFormatter.ISO_ZONED_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
    return formatter.format(utcDateTime.withZoneSameInstant(ZoneId.systemDefault()))
}

fun getCurrentDate(isYesterdaySelected: Boolean = false): String{
    val calendar = Calendar.getInstance()
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(calendar.time)
}


@SuppressLint("NewApi")
fun toUTCISOFormat(dateTime: String): String {
    val localDateTime = ZonedDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault()))
    val utcDateTime = localDateTime.withZoneSameInstant(ZoneId.of("UTC"))

    return utcDateTime.format(DateTimeFormatter.ISO_INSTANT)
}

