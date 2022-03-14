package id.reza.rijks.di

import id.reza.rijks.service.AppService
import id.reza.rijks.service.NetworkConnectionInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { NetworkConnectionInterceptor(get()) }

    single(named(appService)) { AppService.getServices(get()) }
}

const val appService = "APP_SERVICE"