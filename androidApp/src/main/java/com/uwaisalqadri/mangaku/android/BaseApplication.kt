package com.uwaisalqadri.mangaku.android

import android.app.Application
import com.uwaisalqadri.mangaku.android.di.*
import com.uwaisalqadri.mangaku.di.initKoin
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/**
 * Created by Uwais Alqadri on July 23, 2021
 */
class BaseApplication: Application() {

	override fun onCreate() {
		super.onCreate()
		initKoin {
			androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
			androidContext(this@BaseApplication)
			modules(featureModule)
		}
	}
}