package com.uwaisalqadri.mangaku.utils

import co.touchlab.kermit.Logger as KermitLogger
import io.ktor.client.features.logging.Logger

class CustomLogger(private val log: KermitLogger) : Logger {

    override fun log(message: String) {
        log.d(message, "MangaKu")
    }
}