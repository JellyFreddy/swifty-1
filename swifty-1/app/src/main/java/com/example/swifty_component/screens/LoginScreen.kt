package com.example.swifty_component.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.swifty_component.R
import com.example.swifty_component.ApiViewModel
import com.example.swifty_component.navgraph.Screen

@Composable
fun LoginScreen(
    navController: NavController,
    apiViewModel: ApiViewModel
) {
    val inputValue = rememberSaveable { mutableStateOf("Text") }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        OutlinedTextField(
            value = inputValue.value,
            onValueChange = {
                inputValue.value = it
                if (inputValue.value.contains('\n')) {
                    val lastIndex = inputValue.value.indexOf('\n')
                    inputValue.value = inputValue.value.substring(0, lastIndex)
                    apiViewModel.saveLogin(inputValue.value)
                    apiViewModel.getUserById()
                    navController.navigate(Screen.Loading.route)
                }
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .fillMaxHeight(0.1f)
                .padding(8.dp),
            label = { Text(stringResource(R.string.login_outline_text_value)) }
        )
    }
}
