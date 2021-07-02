package com.flicker.sampleapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: Long? = null,
    val title: String? = null,
    val owner: String? = null,
    val secret: String? = null,
    val server: String? = null,
    val farm: Int? = null,
    val isPublic: Int? = null,
    val isFriend: Int? = null,
    val isFamily: Int? = null,
    val photoUrl: String? = null
) : Parcelable