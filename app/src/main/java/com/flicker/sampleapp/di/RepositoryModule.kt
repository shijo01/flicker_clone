package com.flicker.sampleapp.di

import com.flicker.sampleapp.network.FlickerPhotoService
import com.flicker.sampleapp.network.model.PhotoDtoMapper
import com.flicker.sampleapp.repository.FlickerPhotoRepository
import com.flicker.sampleapp.repository.FlickerPhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providePhotoRepository(
        flickerPhotoService: FlickerPhotoService,
        photoDtoMapper: PhotoDtoMapper,
        @Named("api_key") apiKey: String,
        @Named("method") method: String,
        @Named("per_page") perPage: Int,
        @Named("format") format: String
    ): FlickerPhotoRepository {
        return FlickerPhotoRepositoryImpl(
            flickerPhotoService,
            photoDtoMapper,
            method,
            apiKey,
            format,
            1,
            perPage
        )
    }

}