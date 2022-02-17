package com.shapes.watch.data.repository

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.shapes.watch.data.remote.dto.HomeContentDto
import com.shapes.watch.data.remote.dto.VideoDto
import com.shapes.watch.domain.repository.HomeRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseHomeRepository @Inject constructor(
    private val firebaseInstance: FirebaseFirestore
) : HomeRepository {
    override suspend fun getContent(): HomeContentDto {
        return suspendCoroutine { continuation ->
            firebaseInstance.collection("videos").get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val videos = task.result!!.documents.map { it.toVideoDto() }
                    val content = HomeContentDto(videos)
                    continuation.resume(content)
                }
            }
        }
    }

    private fun DocumentSnapshot.toVideoDto(): VideoDto {
        return VideoDto(
            title = this["title"].toString(),
            thumbnailPhotoUrl = this["thumbnailPhotoUrl"].toString(),
            creatorPhotoUrl = this["creatorPhotoUrl"].toString()
        )
    }
}
