package com.space.watch.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import com.space.watch.data.VideoDto
import com.space.watch.domain.model.HomeContent
import com.space.watch.domain.repository.HomeRepository
import kotlinx.coroutines.tasks.await

class HomeRepositoryFirebaseImplementation : HomeRepository {
    private val database = Firebase.firestore
    private val collection = "videos"

    override suspend fun getContent(): HomeContent {
        val videosSnapshot = database.collection(collection).get().await()
        val videos = videosSnapshot.toObjects<VideoDto>().map(VideoDto::toVideo)
        return HomeContent(videos = videos)
    }
}
