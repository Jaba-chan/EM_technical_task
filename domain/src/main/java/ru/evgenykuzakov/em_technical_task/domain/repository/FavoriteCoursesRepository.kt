package ru.evgenykuzakov.em_technical_task.domain.repository

import ru.evgenykuzakov.em_technical_task.domain.model.Course

interface FavoriteCoursesRepository {
    suspend fun insertCourse(course: Course)
    suspend fun getAllCourses(): List<Course>
}