package ru.evgenykuzakov.em_technical_task.data.remote.repository

import android.content.Context
import android.content.SharedPreferences
import ru.evgenykuzakov.em_technical_task.domain.repository.StorageRepository

class StorageRepositoryImpl(private val context: Context): StorageRepository {
    private val appPrefs = "app_prefs"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(appPrefs, Context.MODE_PRIVATE)

    override fun getSignedState(): Boolean = sharedPreferences.getBoolean(appPrefs, false)
    override fun setSignedState(value: Boolean) {
        sharedPreferences.edit().putBoolean(appPrefs, value).apply()
    }
}