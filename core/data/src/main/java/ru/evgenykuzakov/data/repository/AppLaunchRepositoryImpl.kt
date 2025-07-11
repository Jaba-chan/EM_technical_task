package ru.evgenykuzakov.data.repository

import android.content.Context
import ru.evgenykuzakov.domain.repository.AppLaunchRepository
import java.io.File

private const val FILE_NAME = "first_launch"
class AppLaunchRepositoryImpl(private val context: Context): AppLaunchRepository {
    private val file = File(context.cacheDir, FILE_NAME)
    override fun isFirstLaunch(): Boolean = !file.exists()

    override fun setFirstLaunchComplete() {
        file.writeText("already_launched")
    }
}