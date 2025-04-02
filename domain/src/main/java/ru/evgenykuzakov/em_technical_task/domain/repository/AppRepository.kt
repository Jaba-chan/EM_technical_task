package ru.evgenykuzakov.em_technical_task.domain.repository

import ru.evgenykuzakov.em_technical_task.domain.model.Course

interface AppRepository {
    suspend fun getCourses(): List<Course>
}