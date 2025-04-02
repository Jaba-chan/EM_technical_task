package ru.evgenykuzakov.em_technical_task.presentation.favorite

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.evgenykuzakov.em_technical_task.domain.common.Resource
import ru.evgenykuzakov.em_technical_task.domain.model.Course
import ru.evgenykuzakov.em_technical_task.domain.usecase.AddFavoriteCourseUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.ShowCourserUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.ShowFavoriteCoursesUseCase
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class FavoriteScreenViewModel(
    private val showFavoriteCoursesUseCase: ShowFavoriteCoursesUseCase,
) : ViewModel() {

    private val _state = mutableStateOf<FavoriteCoursesState>(FavoriteCoursesState.Loading)
    val state: State<FavoriteCoursesState> = _state

    init {
        getFavoriteCourses()
    }

    fun loadData(){
        getFavoriteCourses()
    }

    private fun getFavoriteCourses() {
        showFavoriteCoursesUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Error -> FavoriteCoursesState.Error("Something wrong")
                is Resource.Loading -> _state.value = FavoriteCoursesState.Loading
                is Resource.Success -> {
                    val data = result.data
                    _state.value = when {
                        data == null -> FavoriteCoursesState.Error("Something wrong")
                        data.isEmpty() -> FavoriteCoursesState.Empty
                        else ->{
                            val formatted = data.map { it.copy(startDate = formatDate(it.startDate)) }
                            FavoriteCoursesState.Success(formatted)
                        }
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed interface FavoriteCoursesState {
        class Success(val data: List<Course>) : FavoriteCoursesState
        object Loading : FavoriteCoursesState
        data class Error(val reason: String) : FavoriteCoursesState
        object Empty : FavoriteCoursesState
    }

    private fun formatDate(dateString: String): String {
        val parsedDate = LocalDate.parse(dateString)
        val formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru"))
        return parsedDate.format(formatter)
            .replaceFirstChar { it.titlecase(Locale("ru")) }
    }
}