package com.shapes.watch.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.shapes.watch.common.getCollectionDocument
import com.shapes.watch.common.getCollectionDocuments
import com.shapes.watch.common.getStringOrThrow
import com.shapes.watch.data.remote.dto.HomeContentDto
import com.shapes.watch.data.remote.dto.VideoDto
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.repository.HomeRepository

class FirebaseHomeRepository(
    private val firebaseInstance: FirebaseFirestore = AppModule.provideFirebaseFirestoreInstance()
) : HomeRepository {
    override suspend fun getContent(): HomeContentDto {
        val videoSnapshots = firebaseInstance.getCollectionDocuments("videos")
        val videos = videoSnapshots.map { videoSnapshot ->
            val creatorId = videoSnapshot.getStringOrThrow("creatorId")
            val creatorSnapshot = firebaseInstance.getCollectionDocument("users", creatorId)

            VideoDto(
                videoId = videoSnapshot.id,
                title = videoSnapshot.getStringOrThrow("title"),
                description = videoSnapshot.getStringOrThrow("description"),
                contentUrl = videoSnapshot.getStringOrThrow("contentUrl"),
                thumbnailUrl = videoSnapshot.getStringOrThrow("thumbnailUrl"),
                creatorId = creatorId,
                creatorName = creatorSnapshot.getStringOrThrow("name"),
                creatorDescription = creatorSnapshot.getStringOrThrow("description"),
                creatorPhotoUrl = creatorSnapshot.getStringOrThrow("photoUrl"),
                creatorCoverUrl = creatorSnapshot.getStringOrThrow("coverUrl")
            )
        }

        return HomeContentDto(videos)
    }
}
