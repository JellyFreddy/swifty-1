package com.example.swifty_component.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.swifty_component.ApiViewModel
import com.example.swifty_component.api.Resource
import com.example.swifty_component.base.MainActivity
import com.example.swifty_component.models.User
import com.example.swifty_component.navgraph.Screen

@Composable
fun LoadingScreen(
    navController: NavController,
    viewModel: ApiViewModel
) {
    val resultQueue = remember {
        viewModel.responseQueue
    }
    val context = LocalContext.current as MainActivity

    when(resultQueue.value) {
        is Resource.Loading -> {}
        is Resource.Success<User> -> LaunchedEffect(Unit) {
            navController.navigate(Screen.Success.route)
        }
        is Resource.Error -> {
            if(resultQueue.value.status == 401)
                LaunchedEffect(Unit) { context.getTokenFunction() }
            else
                LaunchedEffect(Unit) { navController.navigate(Screen.NotFound.route) }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}