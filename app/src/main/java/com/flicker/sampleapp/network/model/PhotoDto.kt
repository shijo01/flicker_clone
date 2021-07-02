package com.flicker.sampleapp.network.model

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("owner") val owner: String? = null,
    @SerializedName("secret") val secret: String? = null,
    @SerializedName("server") val server: String? = null,
    @SerializedName("farm") val farm: Int? = null,
    @SerializedName("ispublic") val isPublic: Int? = null,
    @SerializedName("isfriend") val isFriend: Int? = null,
    @SerializedName("isfamily") val isFamily: Int? = null,
)