package com.space.watch.data.repository

import android.net.Uri
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.space.watch.data.model.CreatorDto
import com.space.watch.data.model.VideoDto
import com.space.watch.domain.model.Size
import com.space.watch.domain.model.Video
import com.space.watch.domain.repository.VideoRepository
import kotlinx.coroutines.tasks.await

class VideoRepositoryFirebaseImplementation : VideoRepository {
    private val database = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
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

    override suspend fun getAllVideosByCreatorId(id: String): List<Video> {
        return database
            .collection(collection)
            .whereEqualTo("creatorId", id)
            .get()
            .await()
            .toObjects<VideoDto>()
            .map(VideoDto::toVideo)
    }

    override suspend fun uploadVideo(
        videoTitle: String,
        videoDescription: String,
        videoUri: Uri,
        videoSize: Size,
        videoImageUri: Uri,
        videoImageSize: Size
    ) {
        val videosStorageRef = storage.reference.child("videos")
        val videosCollectionRef = database.collection("videos")
        val videoDocumentRef = videosCollectionRef.document()
        val id = videoDocumentRef.id
        val videoStorageRef = videosStorageRef.child("$id/video")
        val videoImageRef = videosStorageRef.child("$id/image")

        videoStorageRef.putFile(videoUri).await()
        videoImageRef.putFile(videoImageUri).await()
        val videoUrl = videoStorageRef.downloadUrl.await()
        val videoImageUrl = videoImageRef.downloadUrl.await()
        val creatorDto = CreatorDto(
            id = "NnbXZGVizfoTdos4HCdh",
            name = "Creator"
        )
        val videoDto = VideoDto(
            id = id,
            title = videoTitle,
            description = videoDescription,
            content = videoUrl.toString(),
            size = videoSize,
            image = videoImageUrl.toString(),
            imageSize = videoImageSize,
            creatorId = creatorDto.id,
            creator = creatorDto
        )

        videoDocumentRef.set(videoDto).await()
    }
}
