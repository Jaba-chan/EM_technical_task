package ru.evgenykuzakov.em_technical_task.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation

fun NavGraphBuilder.bottomNavGraph(
    mainScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    accountScreenContent: @Composable () -> Unit
){
    navigation(
        startDestination = Screen.MainScreen.route,
        route = Screen.HomeScreen.route
    ){
        composable(Screen.MainScreen.route) {
            mainScreenContent()
        }
        composable(Screen.FavoriteScreen.route) {
            favoriteScreenContent()
        }
        composable(Screen.AccountScreen.route) {
            accountScreenContent()
        }
    }
}