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
    private val _content = MutableStateFlow<HomeState>(HomeState.Empty)
    val content = _content.asStateFlow()

    init {
        viewModelScope.launch {
            _content.value = HomeState.Wait
            val content = repository.getAllVideos()
            _content.value = HomeState.Content(content)
        }
    }
}
