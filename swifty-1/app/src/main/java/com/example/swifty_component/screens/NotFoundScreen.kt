package com.example.swifty_component.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.swifty_component.R

@Composable
fun NotFoundScreen(
    navController: NavHostController
) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_cancel_24),
            contentDescription = "not found content",
            colorFilter = ColorFilter.tint(Color.Black)
        )
    }
}