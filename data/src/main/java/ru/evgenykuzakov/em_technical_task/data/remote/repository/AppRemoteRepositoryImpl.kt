package ru.evgenykuzakov.em_technical_task.data.remote.repository

import ru.evgenykuzakov.em_technical_task.data.mapper.toDomain
import ru.evgenykuzakov.em_technical_task.data.remote.Api
import ru.evgenykuzakov.em_technical_task.domain.model.Course
import ru.evgenykuzakov.em_technical_task.domain.repository.AppRepository

class AppRemoteRepositoryImpl(private val api: Api) : AppRepository {
    override suspend fun getCourses(): List<Course> {
        return api.getCourses("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download").courses.map { it.toDomain() }
    }
}