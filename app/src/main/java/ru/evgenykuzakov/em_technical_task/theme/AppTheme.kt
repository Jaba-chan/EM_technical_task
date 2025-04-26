package ru.evgenykuzakov.em_technical_task.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import ru.evgenykuzakov.ui.theme.Dark
import ru.evgenykuzakov.ui.theme.DarkGray
import ru.evgenykuzakov.ui.theme.Glass
import ru.evgenykuzakov.ui.theme.Green
import ru.evgenykuzakov.ui.theme.LightGray
import ru.evgenykuzakov.ui.theme.Typography
import ru.evgenykuzakov.ui.theme.White
import ru.evgenykuzakov.ui.theme.White50

private val DarkColorScheme = darkColorScheme(
    primary = Green,

    background = Dark,
    onSurface = White,
    onSecondaryContainer = White50 ,
    tertiaryContainer = DarkGray,
    secondaryContainer = LightGray,
    surfaceContainerLow = Glass
)

private val LightColorScheme = lightColorScheme(
    primary = Green,

    background = Dark,
    onSurface = White,
    onSecondaryContainer = White50 ,
    tertiaryContainer = DarkGray,
    secondaryContainer = LightGray,
    surfaceContainerLow = Glass


    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun AppMaterialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme){
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}