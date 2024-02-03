package com.space.watch.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.space.watch.presentation.home.HomeScreen
import com.space.watch.presentation.video.VideoScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Home) {
        composable(route = Destination.Home) {
            HomeScreen(
                onVideoClick = {
                    navController.navigate(route = Destination.Video)
                }
            )
        }

        composable(route = Destination.Video) {
            VideoScreen(
                onBackButtonClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
