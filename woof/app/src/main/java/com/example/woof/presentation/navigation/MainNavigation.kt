package com.example.woof.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.woof.presentation.screen.ListScreen
import com.example.woof.presentation.screen.LoginScreen
import com.example.woof.presentation.screen.MainScreen
import com.example.woof.presentation.screen.RegisterScreen
import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object Login: Screen()

    @Serializable
    data object Register: Screen()

    @Serializable
    data object Main: Screen()
    @Serializable
    data object List: Screen()
}
@Composable
fun MainNav(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navHostController,
        startDestination = Screen.Login
    ) {
        composable<Screen.Login> {
            LoginScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Main> {
            MainScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.List> {
            ListScreen { navigateTo ->
                navHostController.navigate(navigateTo)
            }
        }
        composable<Screen.Register> {
            RegisterScreen (onRegisterClicked = { lastName, firstName, email ->
                navHostController.navigate(Screen.List)
                })
        }
    }
}

