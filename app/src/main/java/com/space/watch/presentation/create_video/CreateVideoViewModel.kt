package com.space.watch.presentation.create_video

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.watch.domain.repository.implementation.FirebaseVideoRepository
import com.space.watch.domain.model.Size
import com.space.watch.domain.repository.VideoRepository
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

    private val _videoUri = MutableStateFlow<Uri?>(null)
    val videoUri = _videoUri.asStateFlow()

    private val _videoSize = MutableStateFlow<Size?>(null)
    val videoSize = _videoSize.asStateFlow()

    private val _videoImageUri = MutableStateFlow<Uri?>(null)
    val videoImageUri = _videoImageUri.asStateFlow()

    private val _videoImageSize = MutableStateFlow<Size?>(null)
    val videoImageSize = _videoImageSize.asStateFlow()

    val isCreateVideoButtonEnabled = combine(
        videoTitle, videoUri, videoSize, videoImageUri, videoImageSize
    ) { videoTitle, video, videoSize, videoImage, videoImageSize ->
        videoTitle.isNotEmpty() && video != null && videoImage != null && videoSize != null && videoImageSize != null
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

    fun onVideoSelected(value: Uri?) {
        _videoUri.value = value
    }

    fun onVideoSizeChange(value: Size?) {
        _videoSize.value = value
    }

    fun onVideoImageSelected(value: Uri?) {
        _videoImageUri.value = value
    }

    fun onVideoImageSizeChange(value: Size?) {
        _videoImageSize.value = value
    }

    fun onCreateVideoClick(onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _isVideoUploading.value = true
            repository.uploadVideo(
                videoTitle = videoTitle.value,
                videoDescription = videoDescription.value,
                videoUri = videoUri.value!!,
                videoSize = videoSize.value!!,
                videoImageUri = videoImageUri.value!!,
                videoImageSize = videoImageSize.value!!
            )
            _isVideoUploading.value = false
            withContext(Dispatchers.Main) {
                onSuccess()
            }
        }
    }
}
