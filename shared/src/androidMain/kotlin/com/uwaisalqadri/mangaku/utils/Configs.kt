package com.uwaisalqadri.mangaku.utils

import com.uwaisalqadri.mangaku.BuildConfig

/**
 * Created by Uwais Alqadri on July 22, 2021
 */

actual fun getStage(): EnvStage {
	if (BuildConfig.DEBUG) {
		return EnvStage.DEV
	}

	return EnvStage.RELEASE
}
