package com.space.watch.presentation.navigation

sealed class Screen(val route: String) {
    object DefaultScreen : Screen("default")
    object HomeScreen : Screen("home")
    object CreateScreen : Screen("create")
    object UserScreen : Screen("user")
    object SearchScreen : Screen("search")
    object VideoScreen : Screen("video")

}
