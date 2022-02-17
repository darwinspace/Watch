package com.shapes.watch.presentation.video

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.shapes.watch.common.Resource
import com.shapes.watch.domain.case.video.GetVideoContent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(
    private val getVideoContent: GetVideoContent,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf<VideoState>(VideoState.Empty)
    val state: State<VideoState> = _state

    init {
        savedStateHandle.get<String>("videoId")
    }

    private fun getVideo(videoId: String) {
        getVideoContent(videoId).onEach {
            when (it) {
                is Resource.Success -> _state.value = VideoState.Content(it.data)
                is Resource.Loading -> _state.value = VideoState.Loading
                is Resource.Error -> _state.value = VideoState.Error(it.exception)
            }
        }
    }
}