package com.flicker.sampleapp.network

sealed class GenericNetworkResponse<out T> {
    data class Success<out T>(val value: T): GenericNetworkResponse<T>()
    data class GenericError(val error: Exception? = null): GenericNetworkResponse<Nothing>()
}