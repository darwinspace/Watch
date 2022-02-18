package com.shapes.watch.common

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend fun FirebaseFirestore.getCollectionDocuments(
    collection: String
): MutableList<DocumentSnapshot> {
    return collection(collection).get().await().documents
}

suspend fun FirebaseFirestore.getCollectionDocument(
    collection: String,
    document: String
): DocumentSnapshot {
    return collection(collection).document(document).get().await()
}

fun DocumentSnapshot.getStringOrThrow(field: String): String {
    return getString(field) ?: throw IllegalStateException("Field $field was null.")
}

