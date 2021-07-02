package com.flicker.sampleapp.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.flicker.sampleapp.network.FlickerPhotoService
import com.flicker.sampleapp.network.model.PhotoDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePhotoMapper(): PhotoDtoMapper {
        return PhotoDtoMapper()
    }

    @Singleton
    @Provides
    fun providePhotoService(): FlickerPhotoService {


        val logging = HttpLoggingInterceptor()
        logging.level = (HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
        client.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(FlickerPhotoService::class.java)
    }

    @Singleton
    @Provides
    @Named("api_key")
    fun provideApiKey(): String {
        return "062a6c0c49e4de1d78497d13a7dbb360"
    }

    @Singleton
    @Provides
    @Named("method")
    fun provideMethod(): String {
        return "flickr.photos.search"
    }

    @Singleton
    @Provides
    @Named("format")
    fun provideFormat(): String {
        return "json"
    }

    @Singleton
    @Provides
    @Named("per_page")
    fun providePerPage(): Int {
        return 30
    }
}