package com.shapes.watch.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shapes.watch.domain.model.Creator
import com.shapes.watch.domain.model.VideoInformation
import com.shapes.watch.presentation.create.CreateScreen
import com.shapes.watch.presentation.creator.component.CreatorScreen
import com.shapes.watch.presentation.home.component.HomeScreen
import com.shapes.watch.presentation.principal.component.DefaultScreen
import com.shapes.watch.presentation.profile.component.ProfileScreen
import com.shapes.watch.presentation.search.component.SearchScreen
import com.shapes.watch.presentation.video.VideoScreen

@ExperimentalMaterialApi
@Composable
fun NavigationHostComponent(route: String = Screen.HomeScreen.route) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = route
    ) {
        composable(Screen.DefaultScreen.route) {
            DefaultScreen(navController)
        }
        composable(Screen.HomeScreen.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.CreateScreen.route + "/{creatorId}") {
            CreateScreen(
                navController = navController,
                creatorId = requireNotNull(it.arguments!!.getString("creatorId"))
            )
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(
            route = Screen.VideoScreen.route +
                    "/{videoId}" +
                    "/{videoTitle}" +
                    "/{videoThumbnailUrl}" +
                    "/{videoContentUrl}" +
                    "/{creatorId}" +
                    "/{creatorName}" +
                    "/{creatorPhotoUrl}" +
                    "?videoDescription={videoDescription}&" +
                    "creatorDescription={creatorDescription}&" +
                    "creatorCoverUrl={creatorCoverUrl}",
            arguments = listOf(
                navArgument("videoId") { type = NavType.StringType },
                navArgument("videoTitle") { type = NavType.StringType },
                navArgument("videoThumbnailUrl") { type = NavType.StringType },
                navArgument("videoContentUrl") { type = NavType.StringType },
                navArgument("creatorId") { type = NavType.StringType },
                navArgument("creatorName") { type = NavType.StringType },
                navArgument("creatorPhotoUrl") { type = NavType.StringType },
                navArgument("videoDescription") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("creatorDescription") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("creatorCoverUrl") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            VideoScreen(
                navController = navController,
                videoInformation = VideoInformation(entry.arguments!!)
            )
        }
        composable(
            route = Screen.CreatorScreen.route +
                    "/{creatorId}/{creatorName}/{creatorPhotoUrl}" +
                    "?creatorDescription={creatorDescription}&" +
                    "creatorCoverUrl={creatorCoverUrl}",
            arguments = listOf(
                navArgument("creatorId") { type = NavType.StringType },
                navArgument("creatorName") { type = NavType.StringType },
                navArgument("creatorPhotoUrl") { type = NavType.StringType },
                navArgument("creatorDescription") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("creatorCoverUrl") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            CreatorScreen(
                navController = navController,
                creator = Creator(entry.arguments!!)
            )
        }
    }
}
