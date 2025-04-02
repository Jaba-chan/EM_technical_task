package ru.evgenykuzakov.em_technical_task.domain.repository

interface AppLaunchRepository {
    fun isFirstLaunch(): Boolean
    fun setFirstLaunchComplete()
}