package ru.evgenykuzakov.domain.usecase

import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.domain.repository.FavoriteCoursesRepository
import ru.evgenykuzakov.model.Course

class AddFavoriteCourseUseCase(private val repository: FavoriteCoursesRepository) {
    operator fun invoke(course: Course) = flow {
        emit(Resource.Loading())
        val result = repository.insertCourse(course)
        emit(Resource.Success(result))
    }.catch {
        emit(Resource.Error("Something wrong"))
    }
}