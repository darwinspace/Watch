package com.shapes.watch.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shapes.watch.presentation.home.component.HomeScreen
import com.shapes.watch.presentation.principal.DefaultScreen
import com.shapes.watch.presentation.search.SearchScreen
import com.shapes.watch.presentation.video.VideoScreen
import com.shapes.watch.presentation.create.CreateScreen
import com.shapes.watch.presentation.creator.CreatorScreen
import com.shapes.watch.presentation.profile.ProfileScreen

@ExperimentalMaterialApi
@Composable
fun WatchNavigationComponent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("default") {
            DefaultScreen(navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("create") {
            CreateScreen()
        }
        composable("search") {
            SearchScreen()
        }
        composable("video") {
            VideoScreen(navController)
        }
        composable("creator") {
            CreatorScreen(navController)
        }
    }
}
