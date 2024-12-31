package com.space.watch.presentation.main

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class VideoRoute(val id: String)

@Serializable
data class VideoCreatorRoute(val id: String)

@Serializable
data object CreateVideoRoute

@Serializable
data object SignInRoute

@Serializable
data object SignUpRoute
