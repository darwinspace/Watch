package com.space.watch.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import com.space.watch.data.model.VideoDto
import com.space.watch.domain.model.Video
import com.space.watch.domain.repository.VideoRepository
import kotlinx.coroutines.tasks.await

class VideoRepositoryFirebaseImplementation : VideoRepository {
    private val database = Firebase.firestore
    private val collection = "videos"

    override suspend fun getVideoById(id: String): Video {
        return database
            .collection(collection)
            .document(id)
            .get()
            .await()
            .toObject<VideoDto>()!!
            .toVideo()
    }

    override suspend fun getAllVideos(): List<Video> {
        return database
            .collection(collection)
            .get()
            .await()
            .toObjects<VideoDto>()
            .map(VideoDto::toVideo)
    }
}
