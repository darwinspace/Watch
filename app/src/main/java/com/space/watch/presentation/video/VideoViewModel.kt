package com.space.watch.presentation.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.domain.repository.implementation.FirebaseVideoRepository
import com.space.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VideoViewModel(
    private val repository: VideoRepository = FirebaseVideoRepository()
) : ViewModel() {
    private val _state = MutableStateFlow<VideoScreenState>(VideoScreenState.Empty)
    val state = _state.asStateFlow()

    fun getContent(id: String) {
        viewModelScope.launch {
            _state.value = VideoScreenState.Wait
            val video = repository.getVideoById(id)
            _state.value = VideoScreenState.Content(video)
        }
    }
}
