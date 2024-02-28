package com.space.watch.presentation.main

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.space.watch.presentation.create_video.CreateVideoScreen
import com.space.watch.presentation.create_video.CreateVideoViewModel
import com.space.watch.presentation.creator.CreatorScreen
import com.space.watch.presentation.creator.CreatorViewModel
import com.space.watch.presentation.home.HomeScreen
import com.space.watch.presentation.home.HomeViewModel
import com.space.watch.presentation.sign_in.SignInScreen
import com.space.watch.presentation.video.VideoScreen
import com.space.watch.presentation.video.VideoViewModel

const val IdentifierArgumentName = "id"
val IdentifierArgumentType = NavType.StringType

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SignIn) {
        composable(
            route = Screen.Home
        ) {
            val viewModel = viewModel<HomeViewModel>()
            val state by viewModel.state.collectAsState()
            HomeScreen(
                state = state,
                onCreateVideoClick = {
                    navController.navigate(route = Screen.CreateVideo)
                },
                onVideoCreatorClick = {
                    navController.navigate(route = "${Screen.Creator}/$it")
                },
                onVideoClick = {
                    navController.navigate(route = "${Screen.Video}/$it")
                }
            )
        }

        composable(
            route = "${Screen.Video}/{$IdentifierArgumentName}",
            arguments = listOf(
                navArgument(name = IdentifierArgumentName) {
                    type = IdentifierArgumentType
                }
            ),
            enterTransition = { slideInVertically { it } },
            exitTransition = { slideOutVertically { it } }
        ) { backStackEntry ->
            val viewModel = viewModel<VideoViewModel>()
            val state by viewModel.state.collectAsState()

            val id = requireNotNull(backStackEntry.arguments)
                .getString(IdentifierArgumentName)!!

            LaunchedEffect(Unit) {
                viewModel.getContent(id)
            }

            VideoScreen(
                state = state,
                onBackButtonClick = navController::popBackStack,
                onVideoCreatorClick = {
                    navController.navigate(route = "${Screen.Creator}/$it")
                }
            )
        }

        composable(
            route = "${Screen.Creator}/{$IdentifierArgumentName}",
            arguments = listOf(
                navArgument(name = IdentifierArgumentName) {
                    type = IdentifierArgumentType
                }
            ),
            enterTransition = { slideInVertically { it } },
            exitTransition = { slideOutVertically { it } }
        ) { backStackEntry ->
            val viewModel = viewModel<CreatorViewModel>()
            val state by viewModel.state.collectAsState()

            val id = requireNotNull(backStackEntry.arguments)
                .getString(IdentifierArgumentName)!!

            LaunchedEffect(Unit) {
                viewModel.getContent(id)
            }

            CreatorScreen(
                state = state,
                onBackButtonClick = navController::popBackStack,
                onVideoClick = {
                    navController.navigate(route = "${Screen.Video}/$it")
                }
            )
        }

        composable(
            route = Screen.CreateVideo,
            enterTransition = { slideInVertically { it } },
            exitTransition = { slideOutVertically { it } }
        ) {
            val viewModel = viewModel<CreateVideoViewModel>()
            val videoTitle by viewModel.videoTitle.collectAsState()
            val videoDescription by viewModel.videoDescription.collectAsState()
            val videoUri by viewModel.videoUri.collectAsState()
            val videoSize by viewModel.videoSize.collectAsState()
            val videoImageUri by viewModel.videoImageUri.collectAsState()
            val videoImageSize by viewModel.videoImageSize.collectAsState()
            val isCreateVideoButtonEnabled by viewModel.isCreateVideoButtonEnabled.collectAsState()
            val isVideoUploading by viewModel.isVideoUploading.collectAsState()
            val onCreateVideoClick = remember {
                {
                    viewModel.onCreateVideoClick(onSuccess = navController::popBackStack)
                }
            }

            CreateVideoScreen(
                videoTitle = { videoTitle },
                onVideoTitleChange = viewModel::onVideoTitleChange,
                videoDescription = { videoDescription },
                onVideoDescriptionChange = viewModel::onVideoDescriptionChange,
                videoUri = { videoUri },
                onVideoSelected = viewModel::onVideoSelected,
                videoSize = { videoSize },
                onVideoSizeChange = viewModel::onVideoSizeChange,
                videoImageUri = { videoImageUri },
                onVideoImageSelected = viewModel::onVideoImageSelected,
                videoImageSize = { videoImageSize },
                onVideoImageSizeChange = viewModel::onVideoImageSizeChange,
                isCreateVideoButtonEnabled = { isCreateVideoButtonEnabled },
                isVideoUploading = { isVideoUploading },
                onBackButtonClick = navController::popBackStack,
                onCreateVideoClick = onCreateVideoClick
            )
        }

        composable(route = Screen.SignIn) {
            SignInScreen()
        }

        composable(route = Screen.SignUp) {

        }
    }
}
