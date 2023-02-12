package com.uwaisalqadri.mangaku.utils

import android.content.Context
import java.io.InputStreamReader
import org.koin.dsl.module

class AssetResourceReader(
    private val context: Context
) : ResourceReader {
    override fun readResource(name: String): String {
        // TODO: Catch Android-only exceptions and map them to common ones.
        return context.assets.open(name).use { stream ->
            InputStreamReader(stream).use { reader ->
                reader.readText()
            }
        }
    }
}

actual fun resourceReaderModule() = module {
    single<ResourceReader> { AssetResourceReader(get()) }
}