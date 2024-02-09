package com.space.watch.presentation.create_video

sealed interface CreateVideoState {
    data object Wait : CreateVideoState
    data object Empty : CreateVideoState
}
