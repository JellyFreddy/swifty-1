package com.example.swifty_component.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.swifty_component.ApiViewModel
import com.example.swifty_component.utils.TextWithDescription

@Composable
fun SuccessScreen(
    navController: NavHostController,
    viewModel: ApiViewModel
) {

    val user = viewModel.responseQueue.value.data

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(10.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            "Personal Info",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        TextWithDescription(type = "Email", info = user?.email ?: "")
        TextWithDescription(type = "Login", info = user?.login ?: "")
        TextWithDescription(type = "Location", info = user?.location.toString())
        TextWithDescription(type = "Wallet", info = user?.wallet.toString())
        Divider(
            color = Color.Blue,
            thickness = 3.dp,
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
        )
        Text(
            "Projects",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        var counter = 1
        user?.projects_users?.forEach { project ->
            Text(
                "${counter++}) ${project.project.name}",
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
        Divider(
            color = Color.Blue,
            thickness = 3.dp,
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp)
        )
        Text(
            "Skills",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        user?.cursus_users?.get(1)?.skills?.forEach { skill ->
            TextWithDescription(type = skill.name, info = skill.level.toString())
        }
    }
}