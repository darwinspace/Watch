package com.shapes.watch.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shapes.watch.data.remote.dto.UploadVideoInformationDto
import com.shapes.watch.data.remote.dto.VideoDto
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.model.HomeContent
import com.shapes.watch.domain.model.SearchVideoContent
import com.shapes.watch.domain.repository.VideoRepository
import kotlinx.coroutines.tasks.await

class FirebaseVideoRepository(
    private val firestore: FirebaseFirestore,
    private val storage: FirebaseStorage
) : VideoRepository {
    override suspend fun getHomeContent(): HomeContent {
        val videoSnapshots = firestore.collection("videos")
            .get().await().documents
        val videosDto = videoSnapshots.map { videoSnapshot ->
            val creatorId = requireNotNull(videoSnapshot.getString("creatorId"))
            val creatorSnapshot = firestore.collection("users")
                .document(creatorId).get().await()
            VideoDto(
                videoId = videoSnapshot.id,
                title = requireNotNull(videoSnapshot.getString("title")),
                description = videoSnapshot.getString("description"),
                contentUrl = requireNotNull(videoSnapshot.getString("contentUrl")),
                thumbnailUrl = requireNotNull(videoSnapshot.getString("thumbnailUrl")),
                creatorId = creatorId,
                creatorName = requireNotNull(creatorSnapshot.getString("name")),
                creatorDescription = creatorSnapshot.getString("description"),
                creatorPhotoUrl = requireNotNull(creatorSnapshot.getString("photoUrl")),
                creatorCoverUrl = creatorSnapshot.getString("coverUrl")
            )
        }

        val videos = videosDto.map { it.toVideoInformation() }

        return HomeContent(videos)
    }

    override suspend fun searchContent(value: String): SearchVideoContent {
        val videoSnapshots = firestore.collection("videos").whereEqualTo("title", value)
            .get().await().documents
        val videosDto = videoSnapshots.map { videoSnapshot ->
            val creatorId = requireNotNull(videoSnapshot.getString("creatorId"))
            val creatorSnapshot = firestore.collection("users")
                .document(creatorId).get().await()
            VideoDto(
                videoId = videoSnapshot.id,
                title = requireNotNull(videoSnapshot.getString("title")),
                description = videoSnapshot.getString("description"),
                contentUrl = requireNotNull(videoSnapshot.getString("contentUrl")),
                thumbnailUrl = requireNotNull(videoSnapshot.getString("thumbnailUrl")),
                creatorId = creatorId,
                creatorName = requireNotNull(creatorSnapshot.getString("name")),
                creatorDescription = creatorSnapshot.getString("description"),
                creatorPhotoUrl = requireNotNull(creatorSnapshot.getString("photoUrl")),
                creatorCoverUrl = creatorSnapshot.getString("coverUrl")
            )
        }

        val videos = videosDto.map { it.toVideoInformation() }
        return SearchVideoContent(videos)
    }

    override suspend fun uploadVideo(
        video: Uri,
        videoInformation: CreateVideoInformation,
        thumbnail: Uri
    ) {
        val document = firestore.collection("videos").document()
        val videoStorageReference = storage.getReference("${document.path}/video")
        val videoSnapshot = videoStorageReference.putFile(video).await()
        val videoUrl = videoSnapshot.storage.downloadUrl.await()
        val thumbnailStorageReference = storage.getReference("${document.path}/thumbnail")
        val thumbnailSnapshot = thumbnailStorageReference.putFile(thumbnail).await()
        val thumbnailUrl = thumbnailSnapshot.storage.downloadUrl.await()

        val information = UploadVideoInformationDto(
            creatorId = videoInformation.creatorId,
            description = videoInformation.description,
            title = videoInformation.title,
            contentUrl = videoUrl.toString(),
            thumbnailUrl = thumbnailUrl.toString()
        )

        document.set(information).await()
    }
}
