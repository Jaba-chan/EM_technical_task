package ru.evgenykuzakov.em_technical_task

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinContext
import ru.evgenykuzakov.courses.MainScreen
import ru.evgenykuzakov.courses.MainScreenViewModel
import ru.evgenykuzakov.em_technical_task.navigation.AppNavGraph
import ru.evgenykuzakov.em_technical_task.navigation.NavigationItem
import ru.evgenykuzakov.em_technical_task.navigation.NavigationState
import ru.evgenykuzakov.favorite.FavoriteScreenViewModel
import ru.evgenykuzakov.login.SignInViewModel
import ru.evgenykuzakov.em_technical_task.theme.AppMaterialTheme
import ru.evgenykuzakov.favorite.FavoriteScreen
import ru.evgenykuzakov.login.LoginScreen
import ru.evgenykuzakov.onboarding.Onboarding


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMaterialTheme {
                KoinContext {
                    val mainActivityViewModel: MainActivityViewModel = koinViewModel()
                    val navController = rememberNavController()
                    val navState = remember { NavigationState(navController) }

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    val items = listOf(
                        NavigationItem.Main,
                        NavigationItem.Favorite,
                        NavigationItem.Account
                    )
                    Scaffold(
                        modifier = Modifier
                            .fillMaxSize(),
                        bottomBar = {
                            if (currentRoute in items.map { it.screen.route }) {
                                BottomNavigation(
                                    modifier = Modifier
                                        .height(80.dp),
                                    backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
                                ) {
                                    items.forEach { item ->
                                        BottomNavigationItem(
                                            modifier = Modifier
                                                .align(Alignment.CenterVertically),
                                            selected = currentRoute == item.screen.route,
                                            onClick = {
                                                navState.navigateTo(item.screen.route)
                                            },
                                            icon = {
                                                Box(modifier = Modifier
                                                    .let {
                                                        if (currentRoute == item.screen.route) it
                                                            .background(
                                                                MaterialTheme.colorScheme.secondaryContainer,
                                                                RoundedCornerShape(100.dp)
                                                            )
                                                            .size(width = 64.dp, height = 32.dp)
                                                        else it
                                                    }) {
                                                    Icon(
                                                        modifier = Modifier
                                                            .wrapContentSize(),
                                                        painter = painterResource(item.iconResId),
                                                        contentDescription = null,
                                                        tint = if (currentRoute == item.screen.route) MaterialTheme.colorScheme.primary
                                                        else MaterialTheme.colorScheme.onSurface
                                                    )
                                                }

                                            },
                                            label = {
                                                Text(
                                                    modifier = Modifier
                                                        .padding(top = 10.dp, bottom = 16.dp),
                                                    text = stringResource(item.titleResId),
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        lineHeight = 16.sp,
                                                        letterSpacing = 0.5.sp
                                                    ),
                                                    color = if (currentRoute == item.screen.route) MaterialTheme.colorScheme.primary
                                                    else MaterialTheme.colorScheme.onSurface
                                                )
                                            },
                                            selectedContentColor = MaterialTheme.colorScheme.primary,
                                            unselectedContentColor = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                }
                            }
                        }
                    ) { paddingValues ->
                        AppNavGraph(
                            viewModel = mainActivityViewModel,
                            navHostController = navController,
                            signInScreenContent = {
                                LoginScreen(
                                    signInButtonListener = {
                                        navState.navigateToMainScreenForSignedUser()

                                    },
                                    onVKButtonClicked = { openLinkInBrowser(vkUri) },
                                    onOKButtonClicked = { openLinkInBrowser(okUri) },
                                )
                            },
                            onboardingScreenContent = {
                                Onboarding(
                                    continueButtonListener = {
                                        mainActivityViewModel.onFirstLaunchComplete()
                                        navState.navigateToSignIn()
                                    },
                                )
                            },
                            mainScreenContent = {
                                MainScreen(
                                    paddingValues = paddingValues,
                                )
                            },
                            favoriteScreenContent = {
                                FavoriteScreen(
                                    paddingValues = paddingValues,
                                )
                            },
                            accountScreenContent = {}
                        )
                    }
                }
            }
        }
    }


    private fun openLinkInBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    companion object {
        const val vkUri = "https://vk.com/"
        const val okUri = "https://ok.ru/"
    }
}
