package com.githubexplorer

import android.app.Application
import com.githubexplorer.features.projectlist.di.projectListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() = startKoin {
        androidContext(this@App)
        loadKoinModules(projectListModule)
    }
}