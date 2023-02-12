package com.uwaisalqadri.mangaku.utils

import android.annotation.SuppressLint
import com.uwaisalqadri.mangaku.utils.DateFormatter.NORMAL_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
actual fun formatDate(dateString: String, format: String): String {

    val date = if (dateString.isNotEmpty()) SimpleDateFormat(NORMAL_DATE_FORMAT).parse(dateString) else Date()
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(date ?: Date())

}
