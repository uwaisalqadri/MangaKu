package com.uwaisalqadri.mangaku.utils

import platform.Foundation.*

actual fun formatDate(dateString: String, format: String): String {

    val dateFormatter = NSDateFormatter().apply {
        dateFormat = Configs.NORMAL_DATE_FORMAT
    }

    val formatter = NSDateFormatter().apply {
        dateFormat = format
        locale = NSLocale(localeIdentifier = "id_ID")
    }

    return formatter.stringFromDate(dateFormatter.dateFromString(dateString) ?: NSDate())
}