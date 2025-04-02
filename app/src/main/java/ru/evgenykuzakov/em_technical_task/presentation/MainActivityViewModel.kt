package ru.evgenykuzakov.em_technical_task.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import ru.evgenykuzakov.em_technical_task.domain.usecase.CheckFirstLaunchUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.CheckSignInStateUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.SetFirstLaunchCompleteUseCase
import ru.evgenykuzakov.em_technical_task.domain.usecase.SetSignInStateUseCase

class MainActivityViewModel(
    private val checkFirstLaunch: CheckFirstLaunchUseCase,
    private val setFirstLaunchComplete: SetFirstLaunchCompleteUseCase,
    private val checkSignInUseCase: CheckSignInStateUseCase,
    private val setSignInStateUseCase: SetSignInStateUseCase
) : ViewModel() {

    private val _isFirstLaunch = mutableStateOf(true)
    val isFirstLaunch: State<Boolean> = _isFirstLaunch

    private val _isSignedIn = mutableStateOf(false)
    val isSignedIn: State<Boolean> = _isSignedIn

    init {
        _isSignedIn.value = checkSignInUseCase()
        _isFirstLaunch.value = checkFirstLaunch()
    }

    fun onFirstLaunchComplete() {
        setFirstLaunchComplete()
        _isFirstLaunch.value = false
    }

    fun onUserSignedIn() {
        setSignInStateUseCase()
        _isSignedIn.value = true
    }
}