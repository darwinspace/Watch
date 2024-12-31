package com.space.watch.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.space.watch.presentation.create_video.CreateVideoScreen
import com.space.watch.presentation.create_video.CreateVideoViewModel
import com.space.watch.presentation.creator.CreatorScreen
import com.space.watch.presentation.creator.CreatorViewModel
import com.space.watch.presentation.home.HomeScreen
import com.space.watch.presentation.home.HomeViewModel
import com.space.watch.presentation.sign_in.SignInScreen
import com.space.watch.presentation.sign_in.SignInViewModel
import com.space.watch.presentation.sign_up.SignUpScreen
import com.space.watch.presentation.video.VideoScreen
import com.space.watch.presentation.video.VideoViewModel

@Composable
fun MainScreen() {
	val navController = rememberNavController()
	NavHost(
		navController = navController,
		startDestination = HomeRoute
	) {
		composable<HomeRoute> {
			val viewModel = viewModel<HomeViewModel>()
			val state by viewModel.state.collectAsState()

			LaunchedEffect(viewModel) {
				viewModel.getContent()
			}

			HomeScreen(
				state = state,
				onVideoClick = {
					navController.navigate(VideoRoute(it))
				},
				onVideoCreatorClick = {
					navController.navigate(VideoCreatorRoute(it))
				},
				onCreateVideoClick = {
					navController.navigate(CreateVideoRoute)
				}
			)
		}

		composable<VideoRoute> { backStackEntry ->
			val viewModel = viewModel<VideoViewModel>()
			val state by viewModel.state.collectAsState()

			LaunchedEffect(viewModel) {
				val (id) = backStackEntry.toRoute<VideoRoute>()
				viewModel.getContent(id)
			}

			VideoScreen(
				state = state,
				onBackButtonClick = navController::navigateUp,
				onVideoCreatorClick = {
					navController.navigate(VideoCreatorRoute(it))
				}
			)
		}

		composable<VideoCreatorRoute> { backStackEntry ->
			val viewModel = viewModel<CreatorViewModel>()
			val state by viewModel.state.collectAsState()

			LaunchedEffect(viewModel) {
				val (id) = backStackEntry.toRoute<VideoCreatorRoute>()
				viewModel.getContent(id)
			}

			CreatorScreen(
				state = state,
				onBackButtonClick = navController::navigateUp,
				onVideoClick = {
					navController.navigate(VideoRoute(it))
				}
			)
		}

		composable<CreateVideoRoute> {
			val viewModel = viewModel<CreateVideoViewModel>()
			val videoTitle by viewModel.videoTitle.collectAsState()
			val videoDescription by viewModel.videoDescription.collectAsState()
			val video by viewModel.video.collectAsState()
			val videoImage by viewModel.videoImage.collectAsState()
			val isCreateVideoButtonEnabled by viewModel.isCreateVideoButtonEnabled.collectAsState()
			val isVideoUploading by viewModel.isVideoUploading.collectAsState()
			val onCreateVideoClick = remember {
				{
					viewModel.onCreateVideoClick(onSuccess = navController::navigateUp)
				}
			}

			CreateVideoScreen(
				videoTitle = { videoTitle },
				onVideoTitleChange = viewModel::onVideoTitleChange,
				videoDescription = { videoDescription },
				onVideoDescriptionChange = viewModel::onVideoDescriptionChange,
				video = { video },
				onVideoChange = viewModel::onVideoChange,
				videoImage = { videoImage },
				onVideoImageChange = viewModel::onVideoImageChange,
				isCreateVideoButtonEnabled = { isCreateVideoButtonEnabled },
				isVideoUploading = { isVideoUploading },
				onBackButtonClick = navController::navigateUp,
				onCreateVideoClick = onCreateVideoClick
			)
		}

		composable<SignInRoute> {
			val viewModel = viewModel<SignInViewModel>()
			val email by viewModel.email.collectAsState()
			val password by viewModel.password.collectAsState()
			val isSignInButtonEnabled by viewModel.isSignInButtonEnabled.collectAsState()

			SignInScreen(
				email = { email },
				onEmailChange = viewModel::onEmailChange,
				password = { password },
				onPasswordChange = viewModel::onPasswordChange,
				isSignInButtonEnabled = { isSignInButtonEnabled },
				onSignInClick = viewModel::onSignInClick
			)
		}

		composable<SignUpRoute> {
			SignUpScreen()
		}
	}
}
