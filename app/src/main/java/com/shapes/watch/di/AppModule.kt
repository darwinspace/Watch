package com.shapes.watch.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shapes.watch.data.repository.FirebaseCreatorRepository
import com.shapes.watch.data.repository.FirebaseVideoRepository
import com.shapes.watch.domain.repository.CreatorRepository
import com.shapes.watch.domain.repository.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFirebaseFirestoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseStorageInstance(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Singleton
    @Provides
    fun provideVideoRepository(
        firestore: FirebaseFirestore,
        storage: FirebaseStorage
    ): VideoRepository {
        return FirebaseVideoRepository(firestore, storage)
    }

    @Singleton
    @Provides
    fun provideCreatorRepository(instance: FirebaseFirestore): CreatorRepository {
        return FirebaseCreatorRepository(instance)
    }
}
