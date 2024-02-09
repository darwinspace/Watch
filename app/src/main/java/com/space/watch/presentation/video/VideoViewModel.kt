package com.space.watch.presentation.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.data.repository.VideoRepositoryFirebaseImplementation
import com.space.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VideoViewModel(
    private val repository: VideoRepository = VideoRepositoryFirebaseImplementation()
) : ViewModel() {
    private val _state = MutableStateFlow<VideoState>(VideoState.Empty)
    val state = _state.asStateFlow()

    fun getContent(id: String) {
        viewModelScope.launch {
            _state.value = VideoState.Wait
            val video = repository.getVideoById(id)
            _state.value = VideoState.Content(video)
        }
    }
}
