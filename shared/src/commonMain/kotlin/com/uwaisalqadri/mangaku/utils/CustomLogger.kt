package com.uwaisalqadri.mangaku.utils

import io.ktor.client.plugins.logging.*
import co.touchlab.kermit.Logger as KermitLogger

class CustomLogger(private val log: KermitLogger): Logger {

    override fun log(message: String) {
        log.d { "MangaKu: $message" }
    }
}