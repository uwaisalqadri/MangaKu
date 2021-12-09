package com.uwaisalqadri.mangaku.android

import android.app.Application
import co.touchlab.kermit.Kermit
import com.uwaisalqadri.mangaku.android.di.*
import com.uwaisalqadri.mangaku.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Created by Uwais Alqadri on July 23, 2021
 */
class BaseApplication: Application() {

	override fun onCreate() {
		super.onCreate()
		initKoin {
			androidLogger()
			androidContext(this@BaseApplication)
			modules(featureModule)
		}
	}
}