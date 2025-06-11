package com.uwaisalqadri.mangaku.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

actual fun formatted(raw: String, fromFormat: DateFormat, toFormat: DateFormat): String? {
    val date = SimpleDateFormat(fromFormat.pattern).parse(raw)
    val dateFormatter = SimpleDateFormat(toFormat.pattern, Locale.getDefault())
    date?.let {
        return dateFormatter.format(it)
    }
    return null
}