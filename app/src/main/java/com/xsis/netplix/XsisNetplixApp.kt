package com.xsis.netplix

import android.app.Application
import com.xsis.netplix.core.di.apiModule
import com.xsis.netplix.core.di.networkModule
import com.xsis.netplix.core.di.repoModule
import com.xsis.netplix.core.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class XsisNetplixApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@XsisNetplixApp)
            modules(listOf(apiModule, networkModule, repoModule, viewModel))
        }
    }
}