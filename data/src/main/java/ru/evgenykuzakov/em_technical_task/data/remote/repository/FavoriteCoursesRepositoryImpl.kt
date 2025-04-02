package ru.evgenykuzakov.em_technical_task.data.remote.repository

import ru.evgenykuzakov.em_technical_task.data.local.room_db.FavoriteCoursesDatabase
import ru.evgenykuzakov.em_technical_task.data.mapper.toDomain
import ru.evgenykuzakov.em_technical_task.data.mapper.toEntity
import ru.evgenykuzakov.em_technical_task.domain.model.Course
import ru.evgenykuzakov.em_technical_task.domain.repository.AppRepository
import ru.evgenykuzakov.em_technical_task.domain.repository.FavoriteCoursesRepository

class FavoriteCoursesRepositoryImpl(private val database: FavoriteCoursesDatabase): FavoriteCoursesRepository {
    override suspend fun insertCourse(course: Course) {
        return database.courseDao().insertCourse(course = course.toEntity())
    }

    override suspend fun getAllCourses(): List<Course> {
        return database.courseDao().getAllCourses().map { it.toDomain() }
    }
}