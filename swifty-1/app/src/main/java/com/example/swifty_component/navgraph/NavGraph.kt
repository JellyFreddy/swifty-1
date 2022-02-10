package com.example.swifty_component.navgraph

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.swifty_component.ApiViewModel
import com.example.swifty_component.screens.LoadingScreen
import com.example.swifty_component.screens.LoginScreen
import com.example.swifty_component.screens.NotFoundScreen
import com.example.swifty_component.screens.SuccessScreen

@Composable
fun NavGraph(
    viewModel: ApiViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(route = Screen.Login.route) {
            LoginScreen(
                navController = navController,
                apiViewModel = viewModel
            )
        }
        composable(route = Screen.Loading.route) {
            LoadingScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = Screen.Success.route) {
            BackHandler {
                navController.navigate(Screen.Login.route)
            }
            SuccessScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(route = Screen.NotFound.route) {
            BackHandler {
                navController.navigate(Screen.Login.route)
            }
            NotFoundScreen(navController)
        }
    }
}