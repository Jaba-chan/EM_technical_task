package ru.evgenykuzakov.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class SignInViewModel: ViewModel() {
    private val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$")
    private val _email = mutableStateOf("")
    val email: State<String> = _email

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _isEmailValid: Boolean
        get() = emailRegex.matches(_email.value)

    private val _isPasswordValid: Boolean
        get() = _password.value.isNotBlank()

    private val _isFormValid = mutableStateOf(_isPasswordValid && _isEmailValid)
    val isFormValid: State<Boolean> = _isFormValid

    fun onEmailTextChanged(newText: String) {
        _email.value = newText.filter { it.code < 128 }
        _isFormValid.value = _isPasswordValid && _isEmailValid
    }
    fun onPasswordTextChanged(newText: String) {
        _password.value = newText
        _isFormValid.value = _isPasswordValid && _isEmailValid

    }

}