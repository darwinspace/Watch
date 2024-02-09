package com.space.watch.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.data.repository.VideoRepositoryFirebaseImplementation
import com.space.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    repository: VideoRepository = VideoRepositoryFirebaseImplementation()
) : ViewModel() {
    private val _state = MutableStateFlow<HomeState>(HomeState.Empty)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = HomeState.Wait
            val content = repository.getAllVideos()
            _state.value = HomeState.Content(content)
        }
    }
}
