package com.uwaisalqadri.mangaku.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
actual fun formatDate(dateString: String, format: String): String {

    val date = SimpleDateFormat(Constants.normalDateFormat).parse(dateString)
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(date ?: Date())

}
