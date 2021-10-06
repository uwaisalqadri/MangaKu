package com.uwaisalqadri.mangaku.di

import co.touchlab.kermit.CommonLogger
import co.touchlab.kermit.Kermit
import com.uwaisalqadri.mangaku.data.souce.local.LocalDataSource
import com.uwaisalqadri.mangaku.data.souce.remote.RemoteDataSource
import org.koin.dsl.module

val realmModule = module {
    single { LocalDataSource(get()) }
    single { createRealmDatabase() }
}

val ktorModule = module {
    single { RemoteDataSource(get()) }
    single { createJson() }
    single { createKtorClient(get()) }
    single { Kermit(CommonLogger()) }
}
