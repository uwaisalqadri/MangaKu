package com.uwaisalqadri.mangaku.common

import co.touchlab.skie.configuration.annotations.DefaultArgumentInterop

// commonMain

data class DisplayDate(
    val raw: String
) {
    fun string(): String? {
        return formatted(raw, DateFormat.DATE_TIME_STANDARD, DateFormat.DISPLAY_PATTERN_COMMA)
    }
}

expect fun formatted(raw: String, fromFormat: DateFormat, toFormat: DateFormat): String?