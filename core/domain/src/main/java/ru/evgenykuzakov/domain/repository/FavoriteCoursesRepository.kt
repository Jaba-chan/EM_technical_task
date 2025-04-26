package ru.evgenykuzakov.domain.repository

import ru.evgenykuzakov.model.Course


interface FavoriteCoursesRepository {
    suspend fun insertCourse(course: Course)
    suspend fun getAllCourses(): List<Course>
}