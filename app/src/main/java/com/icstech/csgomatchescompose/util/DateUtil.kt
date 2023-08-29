package com.icstech.csgomatchescompose.util

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

    if (isYesterdaySelected) calendar.add(Calendar.DAY_OF_YEAR, -1)

    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(calendar.time)
}
