package com.shapes.watch.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shapes.watch.presentation.home.component.HomeScreen
import com.shapes.watch.presentation.principal.DefaultScreen

@ExperimentalMaterialApi
@Composable
fun WatchNavigationComponent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "default") {
        composable("default") {
            DefaultScreen()
        }
        composable("home") {
            HomeScreen()
        }
    }
}
