package ru.evgenykuzakov.data.repository

import ru.evgenykuzakov.data.mapper.toDomain
import ru.evgenykuzakov.domain.repository.AppRepository
import ru.evgenykuzakov.model.Course


class AppRemoteRepositoryImpl(private val api: ru.evgenykuzakov.network.retrofit.Api) :
    AppRepository {
    override suspend fun getCourses(): List<Course> {
        return api.getCourses("u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q&export=download").courses.map { it.toDomain() }
    }
}