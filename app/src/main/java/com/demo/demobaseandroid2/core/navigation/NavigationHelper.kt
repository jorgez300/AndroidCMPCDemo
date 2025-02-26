package com.demo.demobaseandroid2.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.demobaseandroid2.screens.home.HomeScreen
import com.demo.demobaseandroid2.screens.login.LoginScreen
import com.demo.demobaseandroid2.screens.login.LoginViewModel
import com.demo.demobaseandroid2.screens.setting.SettingScreen

@Composable
fun NavigationHelper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Home> {
            HomeScreen { screen -> navigateTo(screen, navController) }
        }

        composable<Login> {
            LoginScreen(
                navigateTo = { screen -> navigateTo(screen, navController) },
                loginViewModel = LoginViewModel()
            )

        }

        composable<Setting> {
            SettingScreen { screen -> navigateTo(screen, navController) }
        }
    }

}


fun navigateTo(screen: Any, navController: NavHostController) {

    when (screen) {
        is Login -> {
            navController.navigate(screen) {
                popUpTo(Login) {
                    inclusive = true
                }
            }
        }

        is Back -> navController.popBackStack()
        else -> navController.navigate(screen)
    }

}