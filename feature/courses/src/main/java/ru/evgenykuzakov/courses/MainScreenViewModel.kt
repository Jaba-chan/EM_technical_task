package ru.evgenykuzakov.courses

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.evgenykuzakov.common.Resource
import ru.evgenykuzakov.domain.usecase.AddFavoriteCourseUseCase
import ru.evgenykuzakov.domain.usecase.ShowCourserUseCase
import ru.evgenykuzakov.model.Course


class MainScreenViewModel(
    private val showCourserUseCase: ShowCourserUseCase,
    private val addFavoriteCourseUseCase: AddFavoriteCourseUseCase
) : ViewModel() {

    private val _state = mutableStateOf<CoursesState>(CoursesState.Loading)
    val state: State<CoursesState> = _state

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    init {
        getCourses()
    }

    fun onEmailTextChanged(newText: String) {
        _searchText.value = newText
    }

    fun onFavoriteButtonPressed(id: Int) {
        val oldData = (_state.value as CoursesState.Success).data.toList()
        _state.value =
            CoursesState.Success(data = oldData.map {
                if (it.id == id) it.copy(hasLike = !it.hasLike) else it
            })
        val course = oldData.firstOrNull { it.id == id }
        course?.let {
            addCourseToFavorite(it.copy(hasLike = true))
        }
    }

    private fun addCourseToFavorite(course: Course) {
        addFavoriteCourseUseCase.invoke(course).onEach {}.launchIn(viewModelScope)
    }

    private fun getCourses() {
        showCourserUseCase.invoke().onEach { result ->
            when (result) {
                is Resource.Error -> CoursesState.Error("Something wrong")
                is Resource.Loading -> _state.value = CoursesState.Loading
                is Resource.Success -> {
                    val data = result.data
                    _state.value = when {
                        data == null -> CoursesState.Error("Something wrong")
                        data.isEmpty() -> CoursesState.Empty
                        else -> CoursesState.Success(data)

                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    sealed interface CoursesState {
        class Success(val data: List<Course>) : CoursesState
        object Loading : CoursesState
        data class Error(val reason: String) : CoursesState
        object Empty : CoursesState
    }
}