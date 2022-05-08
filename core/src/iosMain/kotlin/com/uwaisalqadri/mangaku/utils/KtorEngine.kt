package com.uwaisalqadri.mangaku.utils

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*
import org.koin.dsl.module

actual fun ktorEngineModule() = module {
    single { getDarwinEngine() }
}

fun getDarwinEngine(): HttpClientEngine {
    return Darwin.create()
}