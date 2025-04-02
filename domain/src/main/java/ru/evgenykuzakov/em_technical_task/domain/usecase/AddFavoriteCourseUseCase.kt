package ru.evgenykuzakov.em_technical_task.domain.usecase

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.evgenykuzakov.em_technical_task.domain.common.Resource
import ru.evgenykuzakov.em_technical_task.domain.model.Course
import ru.evgenykuzakov.em_technical_task.domain.repository.FavoriteCoursesRepository

class AddFavoriteCourseUseCase(private val repository: FavoriteCoursesRepository) {
    operator fun invoke(course: Course) = flow {
        emit(Resource.Loading())
        val result = repository.insertCourse(course)
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error("Something wrong"))
    }
}