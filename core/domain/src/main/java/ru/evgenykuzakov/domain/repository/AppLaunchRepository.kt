package ru.evgenykuzakov.domain.repository

interface AppLaunchRepository {
    fun isFirstLaunch(): Boolean
    fun setFirstLaunchComplete()
}