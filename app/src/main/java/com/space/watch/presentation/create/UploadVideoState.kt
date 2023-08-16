package com.space.watch.presentation.create

sealed class UploadVideoState {
    data class Error(val exception: Exception) : UploadVideoState()
    object Success : UploadVideoState()
    object Empty : UploadVideoState()
    object Loading : UploadVideoState()
}
