package com.space.watch.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.space.watch.presentation.creator.CreatorScreen
import com.space.watch.presentation.creator.CreatorViewModel
import com.space.watch.presentation.home.HomeScreen
import com.space.watch.presentation.home.HomeViewModel
import com.space.watch.presentation.video.VideoScreen
import com.space.watch.presentation.video.VideoViewModel

const val IdentifierArgumentName = "id"
val IdentifierArgumentType = NavType.StringType

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.Home) {
        composable(route = Destination.Home) {
            val viewModel = viewModel<HomeViewModel>()
            val state by viewModel.content.collectAsState()
            HomeScreen(
                state = state,
                onVideoCreatorClick = {
                    navController.navigate(route = "${Destination.Creator}/$it")
                },
                onVideoClick = {
                    navController.navigate(route = "${Destination.Video}/$it")
                }
            )
        }

        composable(
            route = "${Destination.Video}/{$IdentifierArgumentName}",
            arguments = listOf(
                navArgument(name = IdentifierArgumentName) {
                    type = IdentifierArgumentType
                }
            )
        ) { backStackEntry ->
            val viewModel = viewModel<VideoViewModel>()
            val state by viewModel.content.collectAsState()

            val id = requireNotNull(backStackEntry.arguments)
                .getString(IdentifierArgumentName)!!

            LaunchedEffect(Unit) {
                viewModel.getContent(id)
            }

            VideoScreen(
                state = state,
                onBackButtonClick = {
                    navController.popBackStack()
                },
                onVideoCreatorClick = {
                    navController.navigate(route = "${Destination.Creator}/$it")
                }
            )
        }

        composable(
            route = "${Destination.Creator}/{$IdentifierArgumentName}",
            arguments = listOf(
                navArgument(name = IdentifierArgumentName) {
                    type = IdentifierArgumentType
                }
            )
        ) { backStackEntry ->
            val viewModel = viewModel<CreatorViewModel>()
            val state by viewModel.content.collectAsState()

            val id = requireNotNull(backStackEntry.arguments)
                .getString(IdentifierArgumentName)!!

            LaunchedEffect(Unit) {
                viewModel.getContent(id)
            }

            CreatorScreen(
                state = state,
                onBackButtonClick = {
                    navController.popBackStack()
                },
                onVideoClick = {
                    navController.navigate(route = "${Destination.Video}/$it")
                }
            )
        }
    }
}
