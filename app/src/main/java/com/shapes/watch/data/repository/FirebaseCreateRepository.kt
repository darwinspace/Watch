package com.shapes.watch.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shapes.watch.data.remote.dto.UploadVideoInformationDto
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.repository.CreateRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseCreateRepository @Inject constructor(
    private val firestore: FirebaseFirestore/* = AppModule.provideFirebaseFirestoreInstance()*/,
    private val storage: FirebaseStorage/* = AppModule.provideFirebaseStorageInstance()*/
) : CreateRepository {
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
