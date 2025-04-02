package ru.evgenykuzakov.em_technical_task.navigation

import androidx.navigation.NavController

class NavigationState(
    private val navController: NavController
) {
    fun navigateTo(route: String){
        navController.navigate(route){
            popUpTo(Screen.MainScreen.route){
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
    fun navigateToMainScreenForSignedUser(){
        navController.navigate(Screen.HomeScreen.route){
            popUpTo(Screen.SignInScreen.route){
                inclusive = true
            }
        }
    }
    fun navigateToSignIn(){
        navController.navigate(Screen.SignInScreen.route){
            popUpTo(Screen.OnboardingScreen.route){
                inclusive = true
            }
        }
    }
}