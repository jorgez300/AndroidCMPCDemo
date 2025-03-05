package com.demo.demobaseandroid2.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.demobaseandroid2.MainActivity
import com.demo.demobaseandroid2.domain.helper.LocationManager
import com.demo.demobaseandroid2.screens.camara.CameraScreen
import com.demo.demobaseandroid2.screens.detail.DetailScreen
import com.demo.demobaseandroid2.screens.detail.DetailViewModel
import com.demo.demobaseandroid2.screens.gps.LocationScreen
import com.demo.demobaseandroid2.screens.gps.LocationViewModel
import com.demo.demobaseandroid2.screens.home.HomeScreen
import com.demo.demobaseandroid2.screens.login.LoginScreen
import com.demo.demobaseandroid2.screens.login.LoginViewModel
import com.demo.demobaseandroid2.screens.setting.SettingScreen

@Composable
fun NavigationHelper(mainActivity: MainActivity) {
    val navController = rememberNavController()
    val locationManager = LocationManager(mainActivity)
    NavHost(navController = navController, startDestination = Login) {
        composable<Home> {
            HomeScreen { screen -> navigateTo(screen, navController) }
        }

        composable<Camara> {
            CameraScreen()
        }

        composable<Location> {
            LocationScreen(
                viewModel = LocationViewModel(locationManager)
            )
        }

        composable<Login> {
            LoginScreen(
                navigateTo = { screen -> navigateTo(screen, navController) },
                viewModel = LoginViewModel()
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