package ru.evgenykuzakov.em_technical_task.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import ru.evgenykuzakov.em_technical_task.R

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val iconResId: Int
) {
    object Main: NavigationItem(
        screen = Screen.MainScreen,
        titleResId = R.string.main,
        iconResId = R.drawable.ic_home
        
    )
    object Favorite: NavigationItem(
        screen = Screen.FavoriteScreen,
        titleResId = R.string.favorite,
        iconResId = R.drawable.ic_favorite

    )
    object Account: NavigationItem(
        screen = Screen.AccountScreen,
        titleResId = R.string.account,
        iconResId = R.drawable.ic_person

    )
}