package com.uwaisalqadri.mangaku.utils

object DateFormatter {
    const val NORMAL_DATE_FORMAT = "dd-MM-yyyy"
    const val CASUAL_DATE_FORMAT = "dd MMM, yy"
}
expect fun formatDate(dateString: String, format: String): String