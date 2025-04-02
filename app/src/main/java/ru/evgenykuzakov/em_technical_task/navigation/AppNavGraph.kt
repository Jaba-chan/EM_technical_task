package ru.evgenykuzakov.em_technical_task.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.evgenykuzakov.em_technical_task.presentation.MainActivityViewModel

@Composable
fun AppNavGraph(
    viewModel: MainActivityViewModel,
    navHostController: NavHostController,
    signInScreenContent: @Composable () -> Unit,
    onboardingScreenContent: @Composable () -> Unit,
    mainScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    accountScreenContent: @Composable () -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = if (viewModel.isFirstLaunch.value) Screen.OnboardingScreen.route else Screen.SignInScreen.route

    ) {
        composable(Screen.SignInScreen.route) {
            signInScreenContent()
        }
        composable(Screen.OnboardingScreen.route) {
            onboardingScreenContent()
        }
        bottomNavGraph(
            mainScreenContent = mainScreenContent,
            favoriteScreenContent = favoriteScreenContent,
            accountScreenContent = accountScreenContent
        )
    }
}