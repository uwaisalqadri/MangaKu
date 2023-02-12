package com.uwaisalqadri.mangaku.utils

import kotlinx.serialization.DeserializationStrategy
import net.mamoe.yamlkt.Yaml

class YamlResourceReader(
    private val resourceReader: ResourceReader
) {
    internal fun <T> readAndDecodeResource(name: String, strategy: DeserializationStrategy<T>): T {
        val text = resourceReader.readResource(name)
        return Yaml.decodeFromString(strategy, text)
    }
}