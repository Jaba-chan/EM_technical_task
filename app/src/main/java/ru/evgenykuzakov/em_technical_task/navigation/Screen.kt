package ru.evgenykuzakov.em_technical_task.navigation

sealed class Screen(val route: String) {
    object SignInScreen: Screen(ROUTE_SIGN_IN)
    object OnboardingScreen: Screen(ROUTE_ONBOARDING)
    object MainScreen: Screen(ROUTE_MAIN)
    object FavoriteScreen: Screen(ROUTE_FAVORITE)
    object AccountScreen: Screen(ROUTE_ACCOUNT)
    object HomeScreen: Screen(ROUTE_HOME)

    companion object {
        const val ROUTE_SIGN_IN = "sign_in"
        const val ROUTE_ONBOARDING = "onboarding"
        const val ROUTE_MAIN = "main"
        const val ROUTE_FAVORITE = "favorite"
        const val ROUTE_ACCOUNT = "account"
        const val ROUTE_HOME = "home"
    }
}