package ru.evgenykuzakov.domain.repository

import ru.evgenykuzakov.model.Course

interface AppRepository {
    suspend fun getCourses(): List<Course>
}