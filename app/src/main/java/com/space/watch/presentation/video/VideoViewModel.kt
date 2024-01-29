package com.space.watch.presentation.video

import androidx.lifecycle.ViewModel
import com.space.watch.domain.model.HomeState
import com.space.watch.domain.model.VideoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VideoViewModel : ViewModel() {
    private val _content = MutableStateFlow<VideoState>(VideoState.Empty)
    val content = _content.asStateFlow()
}
