package com.shapes.watch.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shapes.watch.data.repository.FirebaseCreateRepository
import com.shapes.watch.data.repository.FirebaseCreatorRepository
import com.shapes.watch.data.repository.FirebaseHomeRepository
import com.shapes.watch.domain.repository.CreateRepository
import com.shapes.watch.domain.repository.CreatorRepository
import com.shapes.watch.domain.repository.HomeRepository

object AppModule {
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    fun provideFirebaseStorageInstance(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    fun provideHomeRepository(): HomeRepository {
        return FirebaseHomeRepository()
    }

    fun provideCreatorRepository(): CreatorRepository {
        return FirebaseCreatorRepository()
    }

    fun provideCreateRepository(): CreateRepository {
        return FirebaseCreateRepository()
    }
}
