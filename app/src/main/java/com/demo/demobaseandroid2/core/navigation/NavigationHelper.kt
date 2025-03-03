package com.demo.demobaseandroid2.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.demo.demobaseandroid2.data.database.DatabaseProvider
import com.demo.demobaseandroid2.data.repository.AppConfigRepository
import com.demo.demobaseandroid2.screens.adduser.AddUserScreen
import com.demo.demobaseandroid2.screens.appconfig.AppConfigScreen
import com.demo.demobaseandroid2.screens.detail.DetailScreen
import com.demo.demobaseandroid2.screens.detail.DetailViewModel
import com.demo.demobaseandroid2.screens.home.HomeScreen
import com.demo.demobaseandroid2.screens.login.LoginScreen
import com.demo.demobaseandroid2.screens.login.LoginViewModel
import com.demo.demobaseandroid2.screens.setting.SettingScreen

//ss
@Composable
fun NavigationHelper() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(navController = navController, startDestination = Login) {
        composable<Home> {
            HomeScreen { screen -> navigateTo(screen, navController) }
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

        composable<AddUser> {

            AddUserScreen()

        }

        composable<AppConfig> {
            val appConfigRepository = remember {
                AppConfigRepository(DatabaseProvider.getDatabase(context).appConfigDao())
            }
            AppConfigScreen(repository = appConfigRepository)
        }


//        composable<AppConfig> {
//
//            AddUserScreen()
//        }
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