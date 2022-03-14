package id.reza.rijks

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.multidex.MultiDexApplication
import id.reza.rijks.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        startKoin {

            androidContext(this@MainApplication)

            modules(
                sessionModule,
                splashModule,
                networkModule,
                appRepoModule,
                profileModule,
                homeModule,
                loginModule,
                registerModule
            )
        }

    }


    companion object {
        private lateinit var context: Context

        fun getContext(): Context {
            return context
        }

        fun getString(resId: Int): String {
            return context.getString(resId)
        }

        fun getString(resId: Int, vararg format: Any?): String {
            return context.getString(resId, *format)
        }
    }

}
