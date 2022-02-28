package com.shapes.watch.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.shapes.watch.data.remote.dto.HomeContentDto
import com.shapes.watch.data.remote.dto.VideoDto
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.repository.HomeRepository
import kotlinx.coroutines.tasks.await

class FirebaseHomeRepository(
    private val firebaseInstance: FirebaseFirestore/* = AppModule.provideFirebaseFirestoreInstance()*/
) : HomeRepository {
    override suspend fun getContent(): HomeContentDto {
        val videoSnapshots = firebaseInstance.collection("videos")
            .get().await().documents
        val videos = videoSnapshots.map { videoSnapshot ->
            val creatorId = requireNotNull(videoSnapshot.getString("creatorId"))
            val creatorSnapshot = firebaseInstance.collection("users")
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

        return HomeContentDto(videos)
    }
}
