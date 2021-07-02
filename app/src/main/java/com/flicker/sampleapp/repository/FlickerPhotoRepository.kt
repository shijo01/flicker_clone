package com.flicker.sampleapp.repository

import com.flicker.sampleapp.domain.model.Photo
import com.flicker.sampleapp.network.GenericNetworkResponse


interface FlickerPhotoRepository {
    suspend fun search(text: String, page: Int): GenericNetworkResponse<List<Photo>>
}