package com.uwaisalqadri.mangaku.data.source.api

import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual fun ktorEngineModule() = module {
    single { OkHttp.create() }
}