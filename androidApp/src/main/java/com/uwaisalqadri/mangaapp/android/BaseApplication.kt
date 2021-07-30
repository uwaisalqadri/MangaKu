package com.uwaisalqadri.mangaapp.android

import android.app.Application
import co.touchlab.kermit.Kermit
import com.uwaisalqadri.mangaapp.android.di.*
import com.uwaisalqadri.mangaapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

/**
 * Created by Uwais Alqadri on July 23, 2021
 */
class BaseApplication: Application(), KoinComponent {
	private val logger: Kermit by inject()

	override fun onCreate() {
		super.onCreate()

		initKoin {
			androidLogger()
			androidContext(this@BaseApplication)
			modules(
				viewModelModule
			)
		}

		logger.d { "BaseApplication" }
	}
}