package ru.evgenykuzakov.data.repository

import ru.evgenykuzakov.data.mapper.toDomain
import ru.evgenykuzakov.data.mapper.toEntity
import ru.evgenykuzakov.database.FavoriteCoursesDatabase
import ru.evgenykuzakov.domain.repository.FavoriteCoursesRepository
import ru.evgenykuzakov.model.Course

class FavoriteCoursesRepositoryImpl(private val database: FavoriteCoursesDatabase): FavoriteCoursesRepository {
    override suspend fun insertCourse(course: Course) {
        return database.courseDao().insertCourse(course = course.toEntity())
    }

    override suspend fun getAllCourses(): List<Course> {
        return database.courseDao().getAllCourses().map { it.toDomain() }
    }
}