package com.demo.demobaseandroid2.core.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.demobaseandroid2.screens.detail.DetailScreen
import com.demo.demobaseandroid2.screens.detail.DetailViewModel
import com.demo.demobaseandroid2.screens.home.HomeScreen
import com.demo.demobaseandroid2.screens.login.LoginScreen
import com.demo.demobaseandroid2.screens.login.LoginViewModel
import com.demo.demobaseandroid2.screens.setting.SettingScreen
import com.demo.demobaseandroid2.screens.login.LoginViewModelFactory

//ss
@Composable
fun NavigationHelper() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val application = context.applicationContext as Application
    NavHost(navController = navController, startDestination = Login) {
        composable<Home> {
            HomeScreen { screen -> navigateTo(screen, navController) }
        }

//        composable<Login> {
//            LoginScreen(
//                navigateTo = { screen -> navigateTo(screen, navController) },
//                viewModel = LoginViewModel()
//            )
//
//        }
        composable<Login> {
            // Create the LoginViewModel using the factory
            val loginViewModel: LoginViewModel =
                viewModel(factory = LoginViewModelFactory(application))
            LoginScreen(
                navigateTo = { screen -> navigateTo(screen, navController) },
                viewModel = loginViewModel
            )
        }

        composable<Detail> {
            DetailScreen(
                navigateTo = { screen -> navigateTo(screen, navController) },
                viewModel = DetailViewModel()
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