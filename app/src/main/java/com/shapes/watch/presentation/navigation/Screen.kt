package com.shapes.watch.presentation.navigation

sealed class Screen(val route: String) {
    object DefaultScreen : Screen("default")
    object HomeScreen : Screen("home")
    object ProfileScreen : Screen("profile")
    object CreateScreen : Screen("create")
    object CreatorScreen : Screen("creator")
    object SearchScreen : Screen("search")
    object VideoScreen : Screen("video")

    fun withArguments(vararg arguments: String): String {
        return buildString {
            arguments.forEach {
                append("/{$it}")
            }
        }
    }
}
