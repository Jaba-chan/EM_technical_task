package ru.evgenykuzakov.em_technical_task.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.evgenykuzakov.em_technical_task.domain.usecase.CheckFirstLaunchUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.SetFirstLaunchCompleteUseCase

class MainActivityViewModel(
    private val checkFirstLaunch: CheckFirstLaunchUseCase,
    private val setFirstLaunchComplete: SetFirstLaunchCompleteUseCase,
) : ViewModel() {

    private val _isFirstLaunch = mutableStateOf(true)
    val isFirstLaunch: State<Boolean> = _isFirstLaunch

    init {
        _isFirstLaunch.value = checkFirstLaunch()
    }

    fun onFirstLaunchComplete() {
        setFirstLaunchComplete()
        _isFirstLaunch.value = false
    }

}