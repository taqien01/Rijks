package id.reza.rijks.di

import id.reza.rijks.service.AppInterface
import id.reza.rijks.service.AppRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val appRepoModule = module {
    factory { AppRepository(get<Retrofit>(named(appService)).create(AppInterface::class.java)) }
}