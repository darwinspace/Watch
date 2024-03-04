package com.space.watch.presentation.create_video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.domain.model.Image
import com.space.watch.domain.model.Video
import com.space.watch.domain.repository.VideoRepository
import com.space.watch.domain.repository.implementation.FirebaseVideoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateVideoViewModel(
    private val repository: VideoRepository = FirebaseVideoRepository()
) : ViewModel() {
    private val _videoTitle = MutableStateFlow(String())
    val videoTitle = _videoTitle.asStateFlow()

    private val _videoDescription = MutableStateFlow(String())
    val videoDescription = _videoDescription.asStateFlow()

    private val _video = MutableStateFlow<Video?>(null)
    val video = _video.asStateFlow()

    private val _videoImage = MutableStateFlow<Image?>(null)
    val videoImage = _videoImage.asStateFlow()

    val isCreateVideoButtonEnabled = combine(
        videoTitle, video, videoImage
    ) { videoTitle, video, videoImage ->
        videoTitle.isNotEmpty() && video != null && videoImage != null
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    private val _isVideoUploading = MutableStateFlow(false)
    val isVideoUploading = _isVideoUploading.asStateFlow()

    fun onVideoTitleChange(value: String) {
        _videoTitle.value = value
    }

    fun onVideoDescriptionChange(value: String) {
        _videoDescription.value = value
    }

    fun onVideoChange(value: Video?) {
        _video.value = value
    }

    fun onVideoImageChange(value: Image?) {
        _videoImage.value = value
    }

    fun onCreateVideoClick(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _isVideoUploading.value = true
            repository.uploadVideo(
                videoTitle = videoTitle.value,
                videoDescription = videoDescription.value,
                videoUri = video.value?.content!!,
                videoSize = video.value?.size!!,
                videoImageUri = videoImage.value?.content!!,
                videoImageSize = videoImage.value?.size!!
            )
            _isVideoUploading.value = false
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }
}
