package ru.evgenykuzakov.em_technical_task

import android.app.Application
import org.koin.android.ext.koin.androidContext
import ru.evgenykuzakov.em_technical_task.presentation.di.initKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@App)
        }
    }
}