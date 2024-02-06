package com.space.watch.presentation.create_video

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.space.watch.domain.model.Size
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateVideoViewModel : ViewModel() {
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

    fun onCreateVideoClick() {

    }
}
