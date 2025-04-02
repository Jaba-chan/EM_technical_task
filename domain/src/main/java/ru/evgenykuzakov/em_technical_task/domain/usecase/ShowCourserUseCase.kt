package ru.evgenykuzakov.em_technical_task.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.evgenykuzakov.em_technical_task.domain.common.Resource
import ru.evgenykuzakov.em_technical_task.domain.model.Course
import ru.evgenykuzakov.em_technical_task.domain.repository.AppRepository

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