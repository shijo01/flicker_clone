package com.flicker.sampleapp.network

import com.flicker.sampleapp.network.model.PhotoDto
import com.flicker.sampleapp.network.responses.PhotoApiResponse
import com.flicker.sampleapp.network.responses.PhotoSearchResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface FlickerPhotoService {
    @GET("services/rest/")
    suspend fun search(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("text") text: String,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ) : PhotoApiResponse
}