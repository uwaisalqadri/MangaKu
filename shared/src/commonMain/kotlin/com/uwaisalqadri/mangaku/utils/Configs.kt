package com.uwaisalqadri.mangaku.utils

import kotlinx.serialization.Serializable

/**
 * Created by Uwais Alqadri on July 22, 2021
 */
@Serializable
data class Configs(
	val baseUrl: String
)

enum class EnvStage(val file: String) {
	DEV("dev.yaml"),
	RELEASE("release.yaml")
}

expect fun getStage(): EnvStage