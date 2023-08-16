package com.space.watch.presentation.user

import com.space.watch.domain.model.UserContent

sealed class UserState {
    data class Content(val userContent: UserContent) : UserState()
    data class Error(val exception: Exception) : UserState()
    object Empty : UserState()
    object Loading : UserState()
}
