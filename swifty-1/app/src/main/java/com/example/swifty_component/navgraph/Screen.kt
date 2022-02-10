package com.example.swifty_component.navgraph

sealed class Screen(
    val route: String,
) {
    object Login : Screen("login")
    object Loading: Screen("loading")
    object Success : Screen("success")
    object NotFound : Screen("not found")
}