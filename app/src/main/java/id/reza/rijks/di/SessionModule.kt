package id.reza.rijks.di

import id.reza.rijks.utils.SessionManager
import org.koin.dsl.module

val sessionModule = module {
    single { SessionManager(get()) }
}