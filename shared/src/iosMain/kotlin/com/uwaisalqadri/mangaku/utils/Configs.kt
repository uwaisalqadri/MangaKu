package com.uwaisalqadri.mangaku.utils

/**
 * Created by Uwais Alqadri on July 22, 2021
 */

actual fun getStage(): EnvStage {
	return stageIos ?: EnvStage.DEV
}

var stageIos: EnvStage? = null