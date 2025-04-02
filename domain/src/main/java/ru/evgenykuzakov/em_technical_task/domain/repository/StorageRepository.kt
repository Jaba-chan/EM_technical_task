package ru.evgenykuzakov.em_technical_task.domain.repository

interface StorageRepository {
    fun getSignedState(): Boolean
    fun setSignedState(value: Boolean)
}