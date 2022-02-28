package com.shapes.watch.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.shapes.watch.data.repository.FirebaseCreateRepository
import com.shapes.watch.data.repository.FirebaseCreatorRepository
import com.shapes.watch.data.repository.FirebaseHomeRepository
import com.shapes.watch.domain.repository.CreateRepository
import com.shapes.watch.domain.repository.CreatorRepository
import com.shapes.watch.domain.repository.HomeRepository
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
    fun provideHomeRepository(instance: FirebaseFirestore): HomeRepository {
        return FirebaseHomeRepository(instance)
    }

    @Singleton
    @Provides
    fun provideCreatorRepository(instance: FirebaseFirestore): CreatorRepository {
        return FirebaseCreatorRepository(instance)
    }

    @Singleton
    @Provides
    fun provideCreateRepository(
        instance: FirebaseFirestore,
        storage: FirebaseStorage
    ): CreateRepository {
        return FirebaseCreateRepository(instance, storage)
    }
}
