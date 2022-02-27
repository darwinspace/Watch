package com.shapes.watch.presentation.create

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shapes.watch.common.Resource
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.use_case.create.UploadVideo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CreateViewModel constructor(
    private val uploadVideo: UploadVideo = UploadVideo()
) : ViewModel() {
    private val _state = mutableStateOf<CreateState>(CreateState.Empty)
    val state: State<CreateState> = _state

    fun uploadVideoInformation(video: Uri, videoInformation: CreateVideoInformation, thumbnail: Uri) {
        uploadVideo(video, videoInformation, thumbnail).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = CreateState.Success
                }
                is Resource.Error -> {
                    _state.value = CreateState.Error(it.exception)
                }
                is Resource.Loading -> Unit
            }
        }.launchIn(viewModelScope)
    }
}
