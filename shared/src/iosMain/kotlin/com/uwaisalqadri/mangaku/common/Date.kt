package com.uwaisalqadri.mangaku.common

import platform.Foundation.*

actual fun formatted(raw: String, fromFormat: DateFormat, toFormat: DateFormat): String? {
    val dateFormatter = NSDateFormatter().apply {
        dateFormat = fromFormat.pattern
    }
    val formatter = NSDateFormatter().apply {
        dateFormat = toFormat.pattern
        locale = NSLocale(localeIdentifier = "id_ID")
    }
    dateFormatter.dateFromString(raw)?.let {
        return formatter.stringFromDate(it)
    }
    return null
}