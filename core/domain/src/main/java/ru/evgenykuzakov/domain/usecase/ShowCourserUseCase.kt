package ru.evgenykuzakov.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.domain.repository.AppRepository
import ru.evgenykuzakov.model.Course

class ShowCourserUseCase(private val appRepository: AppRepository) {
    operator fun invoke(): Flow<Resource<List<Course>>> = flow {
        try {
            emit(Resource.Loading())
            val courses = appRepository.getCourses()
            emit(Resource.Success(data = courses))
        } catch (e: Exception){
            emit(Resource.Error(message = "Something wrong"))
        }
    }
}