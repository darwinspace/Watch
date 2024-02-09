package com.space.watch.presentation.creator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.data.repository.CreatorRepositoryFirebaseImplementation
import com.space.watch.data.repository.VideoRepositoryFirebaseImplementation
import com.space.watch.domain.repository.CreatorRepository
import com.space.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreatorViewModel(
    private val creatorRepository: CreatorRepository = CreatorRepositoryFirebaseImplementation(),
    private val videoRepository: VideoRepository = VideoRepositoryFirebaseImplementation()
) : ViewModel() {
    private val _state = MutableStateFlow<CreatorState>(CreatorState.Empty)
    val state = _state.asStateFlow()

    fun getContent(id: String) {
        viewModelScope.launch {
            _state.value = CreatorState.Wait
            val creator = creatorRepository.getCreatorById(id)
            val videos = videoRepository.getAllVideosByCreatorId(id)
            _state.value = CreatorState.Content(creator, videos)
        }
    }
}
