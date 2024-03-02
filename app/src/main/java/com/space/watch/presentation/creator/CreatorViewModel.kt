package com.space.watch.presentation.creator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.domain.repository.implementation.FirebaseCreatorRepository
import com.space.watch.domain.repository.implementation.FirebaseVideoRepository
import com.space.watch.domain.repository.CreatorRepository
import com.space.watch.domain.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CreatorViewModel(
    private val creatorRepository: CreatorRepository = FirebaseCreatorRepository(),
    private val videoRepository: VideoRepository = FirebaseVideoRepository()
) : ViewModel() {
    private val _state = MutableStateFlow<CreatorScreenState>(CreatorScreenState.Empty)
    val state = _state.asStateFlow()

    fun getContent(id: String) {
        viewModelScope.launch {
            _state.value = CreatorScreenState.Wait
            val creator = creatorRepository.getCreatorById(id)
            val videos = videoRepository.getAllVideosByCreatorId(id)
            _state.value = CreatorScreenState.Content(creator, videos)
        }
    }
}
