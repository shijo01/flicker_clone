package com.flicker.sampleapp.network.responses

import com.google.gson.annotations.SerializedName
import com.flicker.sampleapp.network.model.PhotoDto

data class PhotoSearchResponse(
    @SerializedName("total") val count: Int? = null,
    @SerializedName("perpage") val perPage: Int? = null,
    @SerializedName("pages") val pages: Int? = null,
    @SerializedName("page") val page: Int? = null,
    @SerializedName("photo") val photos: List<PhotoDto> = arrayListOf(),
)

data class PhotoApiResponse(
    @SerializedName("photos") val photoSearchResponse: PhotoSearchResponse
)