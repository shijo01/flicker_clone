package com.flicker.sampleapp.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CustomNetworkCall {
    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, apiCall: suspend () -> T): GenericNetworkResponse<T> {
        return withContext(dispatcher) {
            try {
                val result = apiCall.invoke()
                GenericNetworkResponse.Success(result)
            } catch (throwable: Exception) {
                // show toast message or alert which error message
                // call same api again apiCall.invoke() or safeApiCall()
                GenericNetworkResponse.GenericError(throwable)
            }
        }
    }
}