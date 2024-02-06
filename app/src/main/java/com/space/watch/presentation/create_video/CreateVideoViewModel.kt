package com.space.watch.presentation.create_video

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.space.watch.data.model.CreatorDto
import com.space.watch.data.model.VideoDto
import com.space.watch.domain.model.Size
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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

    private val database = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()

    fun onCreateVideoClick() {
        viewModelScope.launch(Dispatchers.IO) {
            _videoUri.value?.let { videoUri ->
                _videoImageUri.value?.let { imageUri ->
                    val videosCollectionRef = database.collection("videos")
                    val videosStorageRef = storage.reference.child("videos")
                    val videoDocumentRef = videosCollectionRef.document()
                    val id = videoDocumentRef.id
                    val videoStorageRef = videosStorageRef.child("$id/video")
                    val videoImageRef = videosStorageRef.child("$id/image")

                    val videoTaskSnapshot = videoStorageRef.putFile(videoUri).await()
                    val videoUrl = videoStorageRef.downloadUrl.await()
                    val videoImageTaskSnapshot = videoImageRef.putFile(imageUri).await()
                    val videoImageUrl = videoImageRef.downloadUrl.await()
                    val videoDto = VideoDto(
                        id = id,
                        title = _videoTitle.value,
                        description = _videoDescription.value,
                        content = videoUrl.toString(),
                        size = _videoSize.value!!,
                        image = videoImageUrl.toString(),
                        imageSize = _videoImageSize.value!!,
                        creatorId = "NnbXZGVizfoTdos4HCdh",
                        creator = CreatorDto(
                            id = "NnbXZGVizfoTdos4HCdh",
                            name = "Creator"
                        )
                    )

                    videoDocumentRef.set(videoDto)
                }
            }
        }
    }
}
