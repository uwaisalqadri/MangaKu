package com.uwaisalqadri.mangaku.utils

import platform.Foundation.*

actual fun formatDate(dateString: String, format: String): String {

    val dateFormatter = NSDateFormatter().apply {
        dateFormat = Constants.normalDateFormat
    }

    val formatter = NSDateFormatter().apply {
        dateFormat = format
        locale = NSLocale(localeIdentifier = "id_ID")
    }


    return formatter.stringFromDate(dateFormatter.dateFromString(dateString) ?: NSDate())

}
