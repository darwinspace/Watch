package com.space.watch.presentation.video

import androidx.lifecycle.ViewModel
import com.space.watch.data.repository.VideoRepositoryFirebaseImplementation
import com.space.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class VideoViewModel(
    repository: VideoRepository = VideoRepositoryFirebaseImplementation()
) : ViewModel() {
    private val _content = MutableStateFlow<VideoState>(VideoState.Empty)
    val content = _content.asStateFlow()
}
