package com.space.watch.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.domain.repository.VideoRepository
import com.space.watch.domain.repository.implementation.FirebaseVideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: VideoRepository = FirebaseVideoRepository()
) : ViewModel() {
    private val _state = MutableStateFlow<HomeScreenState>(HomeScreenState.Empty)
    val state = _state.asStateFlow()

    fun getContent() {
        viewModelScope.launch {
            _state.value = HomeScreenState.Wait
            val videos = repository.getAllVideos()
            _state.value = HomeScreenState.Content(videos)
        }
    }
}
