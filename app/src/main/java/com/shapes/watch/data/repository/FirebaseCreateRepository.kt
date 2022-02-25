package com.shapes.watch.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shapes.watch.di.AppModule
import com.shapes.watch.domain.model.CreateVideoInformation
import com.shapes.watch.domain.repository.CreateRepository
import kotlinx.coroutines.tasks.await

class FirebaseCreateRepository(
    private val firestore: FirebaseFirestore = AppModule.provideFirebaseFirestoreInstance(),
    private val storage: FirebaseStorage = AppModule.provideFirebaseStorageInstance()
) : CreateRepository {
    override suspend fun uploadVideo(
        videoInformation: CreateVideoInformation,
        video: Uri
    ) {
        val document = firestore.collection("videos").document()
        document.set(videoInformation).await()
        val id = document.id
        val videoReference = storage.reference.child("videos").child(id).child("video.mpeg")
        videoReference.putFile(video).await()
        videoReference.root
//
//        videoReference.path
//        val url = videoReference.downloadUrl.await().path!!
//        val map = hashMapOf<String, Any>(
//            "contentUrl" to videoReference.bucket + videoReference.path
//        )
//        document.update(map)
    }
}

