package ru.evgenykuzakov.em_technical_task.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.evgenykuzakov.em_technical_task.domain.common.Resource
import ru.evgenykuzakov.em_technical_task.domain.model.Course
import ru.evgenykuzakov.em_technical_task.domain.repository.FavoriteCoursesRepository

class ShowFavoriteCoursesUseCase(private val repository: FavoriteCoursesRepository) {
    operator fun invoke(): Flow<Resource<List<Course>>> = flow{
        try {
            emit(Resource.Loading())
            val courses = repository.getAllCourses()
            emit(Resource.Success(data = courses))
        } catch (e: Exception){
            emit(Resource.Error(message = "Something wrong"))
        }
    }
}