package com.uwaisalqadri.mangaku.data.source.remote

import io.ktor.client.engine.android.*
import org.koin.dsl.module

actual fun ktorEngineModule() = module {
    single { Android.create() }
}