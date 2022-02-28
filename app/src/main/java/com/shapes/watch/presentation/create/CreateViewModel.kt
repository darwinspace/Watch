package com.shapes.watch.presentation.create

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shapes.watch.common.Resource
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.use_case.create.UploadVideo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CreateViewModel @Inject constructor(
    private val uploadVideo: UploadVideo/* = UploadVideo()*/
) : ViewModel() {
    private val _uploadState = mutableStateOf<UploadVideoState>(UploadVideoState.Empty)
    val uploadState: State<UploadVideoState> = _uploadState

    fun uploadVideoInformation(
        video: Uri,
        videoInformation: CreateVideoInformation,
        thumbnail: Uri
    ) {
        uploadVideo(video, videoInformation, thumbnail).onEach {
            _uploadState.value = when (it) {
                is Resource.Success -> UploadVideoState.Success
                is Resource.Error -> UploadVideoState.Error(it.exception)
                is Resource.Loading -> UploadVideoState.Loading
            }
        }.launchIn(viewModelScope)
    }
}
